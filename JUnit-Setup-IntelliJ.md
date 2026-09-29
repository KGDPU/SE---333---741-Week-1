# Getting Started with JUnit in IntelliJ IDEA

## Learning Outcomes

By the end of this tutorial, you should be able to:

- Create a Maven-based Java project in IntelliJ IDEA.
- Add JUnit Jupiter to a Maven project.
- Create a JUnit test class.
- Structure a test using Arrange-Act-Assert.
- Run tests from IntelliJ IDEA and Maven.
- Troubleshoot common Maven and JUnit setup problems.

## Background

[JUnit](https://junit.org/) is a widely used testing framework for Java. This tutorial uses **JUnit Jupiter**, the programming and extension model associated with modern JUnit.

Maven manages the project's build lifecycle and external libraries. Its `pom.xml` file declares dependencies such as JUnit and ensures that they are available to both IntelliJ IDEA and the command line.

## Prerequisites

Before starting, make sure you have:

- [IntelliJ IDEA](https://www.jetbrains.com/idea/) installed
- JDK 17 or later installed
- Maven available through IntelliJ IDEA or installed separately

Students can apply for a free JetBrains educational license using their student email address. IntelliJ IDEA Community Edition is also sufficient for this tutorial.

## Step 1: Create a Maven Project

1. Open IntelliJ IDEA.
2. Select **New Project**.
3. Enter a name for the project, such as `rectangle-junit`.
4. Select **Java** as the language.
5. Select **Maven** as the build system.
6. Select an installed JDK, preferably JDK 17 or later.
7. Click **Create**.

A typical project structure looks like this:

```text
rectangle-junit/
|-- pom.xml
`-- src/
    |-- main/java/com/example/Rectangle.java
    `-- test/java/com/example/RectangleTest.java
```

Production code belongs under `src/main/java`, while test code belongs under `src/test/java`.

## Step 2: Create the `Rectangle` Class

If IntelliJ generated a `Main.java` file, you can delete it or rename it using **Refactor > Rename**.

Create `src/main/java/com/example/Rectangle.java`:

```java
package com.example;

public class Rectangle {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getArea() {
        return length * width;
    }
}
```

## Step 3: Generate a Test Class in IntelliJ IDEA

1. Open `Rectangle.java`.
2. Right-click inside the editor and select **Generate**.
3. Select **Test**.
4. Choose **JUnit 5** as the testing library.
5. Name the class `RectangleTest`.
6. Select `getArea()` as the method to test.
7. Confirm that IntelliJ places the class under `src/test/java`.

The generated class may initially show errors because JUnit has not yet been declared as a Maven dependency.

## Step 4: Understand `pom.xml`

Maven's `pom.xml` file describes the project and its build configuration. Maven can use it to:

- Compile source code
- Download and manage dependencies
- Run tests
- Package the project as a JAR or another artifact

Maven also manages **transitive dependencies**. When a library depends on other libraries, Maven retrieves the required compatible versions automatically.

## Step 5: Add JUnit to `pom.xml`

Open `pom.xml` and configure the project as follows. This tutorial intentionally uses JUnit Jupiter 5.10.3.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>rectangle-junit</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.release>17</maven.compiler.release>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.10.3</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.5</version>
            </plugin>
        </plugins>
    </build>
</project>
```

The `test` scope makes JUnit available when compiling and running tests without including it in the application's production artifact. The `junit-jupiter` dependency includes both the API used to write tests and the engine required to run them.

After saving `pom.xml`, reload the Maven project:

- Click the **Load Maven Changes** notification, or
- Open the **Maven** tool window and click **Reload All Maven Projects**.

## Step 6: Write a Test Using Arrange-Act-Assert

Open or create `src/test/java/com/example/RectangleTest.java`:

```java
package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleTest {

    @Test
    void getAreaReturnsLengthTimesWidth() {
        // Arrange
        Rectangle rectangle = new Rectangle(2.0, 6.0);
        double expected = 12.0;

        // Act
        double actual = rectangle.getArea();

        // Assert
        assertEquals(expected, actual, 0.0001);
    }
}
```

### Arrange-Act-Assert

1. **Arrange:** Create the objects and inputs needed by the test.
2. **Act:** Call the method or behavior being tested.
3. **Assert:** Compare the actual result with the expected result.

The `@Test` annotation tells JUnit that the method is a test. Test names should describe the expected behavior. A `test` prefix is allowed, but JUnit 5 does not require it.

## Step 7: Run the Test

### From IntelliJ IDEA

Click the green run icon beside the test method or test class, and then select **Run**.

### From the Project Root

Open a terminal in the directory containing `pom.xml` and run:

```bash
mvn test
```

Maven compiles the production code, compiles the tests, and runs the test suite. A successful run should report:

```text
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## Troubleshooting

### JUnit imports are red or unresolved

1. Save `pom.xml`.
2. Reload the Maven project.
3. Confirm that the dependency appears under **External Libraries**.
4. Run:

   ```bash
   mvn clean test
   ```

### Maven uses the wrong Java version

Check the versions Maven is using:

```bash
mvn -v
```

Then confirm that the project SDK is configured under **File > Project Structure > Project**.

### Maven reports that no tests were found

Confirm that:

- The test class is under `src/test/java`.
- The test method has the `@Test` annotation.
- The test imports `org.junit.jupiter.api.Test`.
- The test class follows a conventional name such as `RectangleTest`.
- The `junit-jupiter` dependency and Maven Surefire plugin are present in `pom.xml`.

### The `target` directory contains stale output

Run:

```bash
mvn clean test
```

The `clean` phase removes the `target` directory. The `test` phase then rebuilds the project and runs the tests.

## Maven Module Build Order

In a multi-module project, Maven determines a valid build order from the dependencies between modules. If `ModuleA` depends on `ModuleB`, and `ModuleB` depends on `ModuleC`, Maven builds them in this order:

```text
ModuleC -> ModuleB -> ModuleA
```

This ensures that a module is not built before the modules it depends on.

## Completion Checklist

- [ ] IntelliJ IDEA recognizes the project as a Maven project.
- [ ] `Rectangle.java` is under `src/main/java`.
- [ ] `RectangleTest.java` is under `src/test/java`.
- [ ] JUnit Jupiter is declared in `pom.xml` with `test` scope.
- [ ] IntelliJ IDEA can run the test.
- [ ] `mvn test` reports `BUILD SUCCESS`.
