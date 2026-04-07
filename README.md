# Adder Spring Backend

A simple Spring Boot REST API used by the React frontend.

If you are also using the frontend, look for `adder-react-frontend` in this repository.

## Prerequisites

- Java `22` or newer (from `pom.xml` -> `java.version`)
- Maven Wrapper (already included: `./mvnw`)

## Project Commands (Maven)

Run all commands from the project root:

```bash
cd /Users/solangekarsenty/WebstormProjects/React-Spring/06/adder-spring-backend
```

Install dependencies and run tests:

```bash
./mvnw test
```

Build the jar:

```bash
./mvnw clean package
```

Run the app in dev mode:

```bash
./mvnw spring-boot:run
```

Run the packaged jar:

```bash
java -jar target/adder-spring-backend-0.0.1-SNAPSHOT.jar
```

The API starts on `http://localhost:8080` by default.

## API Endpoints

Base path: `/api`

### 1) Health/Hello Endpoint

- Method: `GET`
- URL: `http://localhost:8080/api`
- Response: `Hello World!`

Example:

```bash
curl http://localhost:8080/api
```

### 2) Add with Path Variables

- Method: `GET`
- URL pattern: `http://localhost:8080/api/add/a/{a}/b/{b}`
- Example response: `{"result":8,"message":null}`

Example:

```bash
curl http://localhost:8080/api/add/a/3/b/5
```

### 3) Add with JSON Body

- Method: `POST`
- URL: `http://localhost:8080/api/add`
- Body:

```json
{
  "operand1": 10,
  "operand2": 7
}
```

- Example response: `{"result":17,"message":"Addition successful!"}`

Example:

```bash
curl -X POST http://localhost:8080/api/add \
  -H "Content-Type: application/json" \
  -d '{"operand1":10,"operand2":7}'
```

## Notes

- Invalid path types (for example `/api/add/a/x/b/5`) return `400 Bad Request`.
- Unexpected server errors return `500 Internal Server Error`.
