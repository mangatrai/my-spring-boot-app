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

   ## Appliation yaml Instructions

      **Create the application.yaml template:**
         ```
         cp sample/application-template.yaml src/main/resources/application.yaml
         ```

      **Update the configuration:**
         - Open the `src/main/resources/application.yaml` file.
         - Update the `keyspace`,`username`, `password`, and `secure-connect-bundle` location with your DataStax Astra credentials.

3. **Build the project:**
   ```
   mvn clean install
   ```

4. **Run the application:**
   ```
   mvn spring-boot:run
   ```

5. **Access the application:**
   Open your web browser and navigate to `http://localhost:8080`.

## Usage

This application serves as a basic template for building Spring Boot applications with DataStax Astra integration. You can extend the functionality by adding more features and endpoints as needed.

## Example `curl` Commands

### POST Request

To create a new entity:

```sh
curl -X POST http://localhost:8080/entities -H "Content-Type: application/json" -d '{
  "id": "a543e321-e89b-12d3-b456-526614174114",
  "event_identifier": "MXND12JFD",
  "event_timestamp": "1730463300000",
  "event_state": "VALIDATED",
  "event_name": "com.sephora.happpening.reservation.created",
  "event_payload_url": "https://sepeus1lowerhasm01.blob.core.windows.net/auditing-cloud-event/com.sephora.happpening.reservation.created_2024-11-01T12%3A15Z",
  "rid": "ZadCAJs9qXYDAAAAAAAAAA==",
  "self": "dbs/ZadCAA==/colls/ZadCAJs9qXY=/docs/ZadCAJs9qXYDAAAAAAAAAA==/",
  "etag": "\"fd018972-0000-0100-0000-670e52a30000\"",
  "attachments": "attachments/",
  "ts": 1728991907
}'
```

### GET Requests

To get all entities:

```sh
curl -X GET http://localhost:8080/entities
```

To get an entity by ID:

```sh
curl -X GET http://localhost:8080/entities/a543e321-e89b-12d3-b456-526614174114
```

To filter entities by `eventIdentifier`:

```sh
curl -X GET "http://localhost:8080/entities/filter?eventIdentifier=MXND12JFD"
```
