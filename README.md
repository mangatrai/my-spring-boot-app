<<<<<<< HEAD
# my-spring-boot-app
sample spring data app for astra connectivity
=======
# My Spring Boot Application

This is a Spring Boot application built with version 3.2.1 that connects to the DataStax Astra database.

## Project Structure

```
my-spring-boot-app
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── MySpringBootAppApplication.java
│   │   ├── resources
│   │       ├── application.properties
│   │       └── static
│   │           └── index.html
│   └── test
│       ├── java
│       │   └── com
│       │       └── example
│       │           └── MySpringBootAppApplicationTests.java
├── mvnw
├── mvnw.cmd
└── pom.xml
```

## Setup Instructions

1. **Clone the repository:**
   ```
   git clone <repository-url>
   cd my-spring-boot-app
   ```

2. **Build the project:**
   ```
   ./mvnw clean install
   ```

3. **Configure DataStax Astra:**
   - Create a DataStax Astra database and obtain the connection details.
   - Update the `src/main/resources/application.properties` file with your Astra database credentials.

4. **Run the application:**
   ```
   ./mvnw spring-boot:run
   ```

5. **Access the application:**
   Open your web browser and navigate to `http://localhost:8080`.

## Usage

This application serves as a basic template for building Spring Boot applications with DataStax Astra integration. You can extend the functionality by adding more features and endpoints as needed.
>>>>>>> ea4f3b6 (first commit for the sample spring app)
