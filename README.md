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
            ├── secure-connect-bundle
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

2. **Configure DataStax Astra:**
   - Create a DataStax Astra database and obtain the connection details.
   - Update the `src/main/resources/application.yaml` file with your Astra database credentials.
   - App will Create tableschema and index at startup. If you want to create yourself please refer to  [cql file](sample/auditing_events.cql)

   ## Application yaml Instructions

      **Create the application.yaml template:**
         ```
         cp sample/application-template.yaml src/main/resources/application.yaml
         ```

      **Update the configuration:**
         - Open the `src/main/resources/application.yaml` file.
         - Update the `keyspace`, `username`, `password`, and `secure-connect-bundle` location with your DataStax Astra credentials.

3. **Build the project:**
   ```
   mvn clean install
   ```
   OR
   ```
   mvn clean package
   ```

4. **Run the application:**

   You can run the application using Maven or directly using the JAR file. You can also pass system properties to control the behavior of the application.

   ### Using Maven:
   ```
   mvn spring-boot:run -Dspring-boot.run.arguments="--generateTestData=true --instanceName=MyInstance --numTestRecords=1000"
   ```

   ### Using JAR file:
   ```
   java -DgenerateTestData=true -DinstanceName=MyInstance -DnumTestRecords=1000 -jar target/my-spring-boot-app-1.0.0.jar
   ```

   ### System Properties:
   - `generateTestData`: Set to `true` to generate test data on startup. Default is `false`.
   - `instanceName`: The name of the instance. prefix for eventIdentifier. Useful when running app from different machines. Default is `TEST`.
   - `numTestRecords`: The number of test records to generate. Default is `10000`.

5. **Access the application:**
   Open your web browser and navigate to `http://localhost:8080`.

## Usage

This application serves as a basic template for building Spring Boot applications with DataStax Astra integration. You can extend the functionality by adding more features and endpoints as needed.

### Postman Collection

For your convenience, you can also use the Postman collection provided in this project. The collection includes all the API endpoints with sample requests and responses. You can import the collection into Postman using the following file:

[sample/my-spring-boot-app.postman_collection.json](sample/my-spring-boot-app.postman_collection.json)

## Example `curl` Commands

Below are some example `curl` commands that you can use to interact with the API endpoints of this project.

### POST Request

To create a new entity:

```sh
curl -X POST http://localhost:8080/entities -H "Content-Type: application/json" -d '{
  "id": "a543e321-e89b-12d3-b456-526614174114",
  "event_identifier": "MXND12JFD",
  "event_timestamp": "1730463300000",
  "event_state": "VALIDATED",
  "event_name": "com.sephora.happpening.reservation.created",
  "event_payload_url": "https://sepeus1lowerhasm01.blob.core.windows.net/auditing-cloud-event/com.sephora.happpening.reservation.created_2024-11-01T12%3A15Z"
}'
```

### PUT Request

To update an entity using `eventIdentifier` and `eventState`:

```sh
curl -X PUT "http://localhost:8080/entities/MXND12JFD/VALIDATED" \
     -H "Content-Type: application/json" \
     -d '{
           "event_identifier": "MXND12JFD",
           "event_state": "VALIDATED",
           "event_timestamp": "1730463300000",
           "event_name": "com.sephora.happpening.reservation.updated",
           "event_payload_url": "https://sepeus1lowerhasm01.blob.core.windows.net/auditing-cloud-event/com.sephora.happpening.reservation.updated_2024-11-01T12%3A15Z",
           "id": "a543e321-e89b-12d3-b456-526614174114"
         }'
```

### GET Requests

To get all entities:

```sh
curl -X GET http://localhost:8080/entities
```

To get entities by `eventIdentifier`:

```sh
curl -X GET http://localhost:8080/entities/MXND12JFD
```

To get entities by `eventIdentifier` and `eventState`:

```sh
curl -X GET http://localhost:8080/entities/MXND12JFD/VALIDATED
```

To filter entities by `eventIdentifier`:

```sh
curl -X GET "http://localhost:8080/entities/filter?eventIdentifier=MXND12JFD"
```

To filter entities by `eventIdentifier` and `eventState`:

```sh
curl -X GET "http://localhost:8080/entities/filter?eventIdentifier=MXND12JFD&eventState=VALIDATED"
```

To filter entities by `eventState`:

```sh
curl -X GET "http://localhost:8080/entities/filter?eventState=VALIDATED"
```

To filter entities by `eventName`:

```sh
curl -X GET "http://localhost:8080/entities/filter?eventName=com.sephora.happpening.reservation.created"
```

### DELETE Requests

To delete an entity using `eventIdentifier`:

```sh
curl -X DELETE "http://localhost:8080/entities/MXND12JFD" \
     -H "Content-Type: application/json"
```

To delete an entity using `eventIdentifier` and `eventState`:

```sh
curl -X DELETE "http://localhost:8080/entities/MXND12JFD/VALIDATED" \
     -H "Content-Type: application/json"
```
## JMeter Test Cases

This section provides instructions for running JMeter test cases for this application.

### Summary

The JMeter test cases are designed to test the performance and functionality of the API endpoints. The test plan is available in the [testapi.jmx](jmeter/testapi.jmx) file.

### Running the Test from Command Line

To run the JMeter test plan from the command line, use the following command:

```sh
jmeter -n -t testapi.jmx -l results.jtl -Jprefix=MyCustomPrefix -Jprotocol=https -Jserver=localhost -Jport=8443 -JeventIdentifierFileName=eventIdentifier.csv
```

#### Explanation of Flags/Variables

- `-n`: Run JMeter in non-GUI mode.
- `-t testapi.jmx`: The path to the JMeter test plan file.
- `-l results.jtl`: The path to the results file where the test results will be saved.
- `-Jprefix=MyCustomPrefix`: The prefix for the event identifier.
- `-Jprotocol=https`: The protocol to use (https or http).
- `-Jserver=localhost`: The server to test against (localhost).
- `-Jport=8443`: The port to test against (8443 or 8080).
- `-JeventIdentifierFileName=eventIdentifier.csv`: The name of the CSV file to use for event identifiers.

### Generating a Dashboard

After running the test, you can generate a dashboard to visualize the test results using the following command:

```sh
jmeter -g results.jtl -o dashboard
```

#### Explanation of Variables

- `-g results.jtl`: The path to the results file generated by the test.
- `-o dashboard`: The path to the output directory where the dashboard will be generated.

The dashboard will provide detailed insights into the performance and functionality of the API endpoints tested by the JMeter test plan.