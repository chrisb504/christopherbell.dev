# Website Module

Spring Boot application that serves the site and bundles the frontend JavaScript.

## Frontend tooling
```bash
npm install
npm run lint
npm run build
```
The bundled assets are emitted to `src/main/resources/static/js`.

## Running the application
From the repository root:
```bash
./gradlew :website:bootRun
```

## Building
```bash
./gradlew :website:build
```
