# Maven Basic

## Learning Outcomes

- Understand Maven's purpose and structure.
- Create a Maven project using `mvn archetype`.
- Work with the `pom.xml` file to manage dependencies and plugins.
- Run unit tests using Maven.
- Generate build and test reports.
- Apply Maven commands to clean, compile, package, and install projects.

## Background

Maven is a build automation and project management tool.

- **Build system:** A tool that compiles source code into binaries (machine code), going from a human-readable format to machine code.
- **Maven:** A build lifecycle manager, dependency manager, and project management framework.

```java
public class Welcome {
    public static void main(String[] args) {
        System.out.println("Welcome to Software Testing!");
    }
}
```

To compile the code above, we can use either:

```bash
javac Welcome.java
```

or:

```bash
mvn compile
```

This will produce the machine-code file `Welcome.class`, which is meant to be run by the JVM.

In Maven projects, you will often see different outputs:

- `.class` - stored in the `target/classes` folder
- `.jar` - a packaged collection of files and resources that can be distributed
- `.war` - a web application archive
- `.exe` - a native binary

## Step 1: Set Up Maven

**Goal:** Install Maven and verify that it works.

1. [Install Maven](https://maven.apache.org/install.html).
2. Run:

   ```bash
   mvn -v
   ```

## Step 2: Create Your First Project

**Goal:** Generate a simple starter project.

**Resource:** [Introduction to Archetypes - Maven](https://maven.apache.org/guides/introduction/introduction-to-archetypes.html)

1. Use an archetype:

   ```bash
   mvn archetype:generate \
     -DgroupId=com.example \
     -DartifactId=maven-kata \
     -DarchetypeArtifactId=maven-archetype-quickstart \
     -DinteractiveMode=false
   ```

   - **Archetype (plugin)**
     - Think of it as a template for creating a new project.
     - When we run `generate`, Maven builds a starter project from this template.
   - **`-DgroupId`**
     - A unique ID for your project.
     - Usually written in reverse-domain format.
     - Example: company `example.org` becomes `org.example`.
     - It helps identify who owns the project.
   - **`-DartifactId`**
     - The name of your project.
     - It also becomes the folder name and, later, the JAR filename.
   - **`-DarchetypeArtifactId`**
     - The specific template you want to use.
     - For beginners, we often use `maven-archetype-quickstart`.

2. Explore the generated structure:

   ```text
   maven-kata/
   |-- pom.xml
   `-- src/
       |-- main/java/com/example/App.java
       `-- test/java/com/example/AppTest.java
   ```

## Step 3: Build Lifecycle Basics

**Goal:** Run basic Maven commands from the command line.

**Resource:** [Introduction to the Build Lifecycle - Maven](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)

1. Run:

   ```bash
   mvn compile
   mvn test
   mvn package
   ```

   `mvn test` runs the compiled test classes.

2. Observe that the packaged `.jar` file is stored in the `target` folder.

## Step 4: Add a Dependency

**Goal:** Use Maven Central to add libraries.

1. Open `pom.xml` and add the following code inside the `<dependencies>` section:

   ```xml
   <dependencies>
   <!-- Source: https://mvnrepository.com/artifact/org.apache.commons/commons-collections4 -->
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-collections4</artifactId>
        <version>4.6.0</version>
        <scope>compile</scope>
    </dependency>
   </dependencies>
   ```

2. Install the dependencies using Maven. In the terminal, run:

   ```bash
   mvn install
   ```

   This downloads the library from Maven Central and places it in your local cache, in the `.m2/repository` folder.

3. Modify `App.java` to use Commons Collections:

   ```java
   package org.example;

   import org.apache.commons.collections4.Bag;
   import org.apache.commons.collections4.bag.HashBag;

   public class App {
       public static void main(String[] args) {
           Bag<String> bag = new HashBag<>();
           bag.add("A");
           bag.add("A");
           int c = bag.getCount("A"); // 2
           System.out.println("Hello World!");
       }
   }
   ```

## Step 7: Install and Reuse

**Goal:** Understand how Maven lets you reuse artifacts across projects.

1. Install your project in the local repository:

   ```bash
   mvn install
   ```

   This places your project's JAR file in your local Maven cache at:

   ```text
   .m2/repository/com/example/maven-kata/1.0-SNAPSHOT/
   ```

2. Create a new Maven project.

   - Create a fresh project using an archetype, as before.
   - Open its `pom.xml` file.

3. Add your first project as a dependency. Inside the new project's `pom.xml`, add:

   ```xml
   <dependency>
     <groupId>com.example</groupId>
     <artifactId>maven-kata</artifactId>
     <version>1.0-SNAPSHOT</version>
   </dependency>
   ```

4. Build the new project:

   ```bash
   mvn compile
   ```
