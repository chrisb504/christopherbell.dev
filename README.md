# christopherbell.dev

## Modules
- `website` – Spring Boot application and vanilla JavaScript frontend. Contains all Node tooling.
- `cbell-lib` – Reusable Java library shared across applications.

## Requirements
- Java 21
- Node.js 18+
- npm 9+
- PostgreSQL (optional for local development)

## Getting Started

### Build frontend assets
```bash
cd website
npm install
npm run build
```

### Run the application
```bash
./gradlew :website:bootRun
```

### Build all modules
```bash
./gradlew build
```

Each module also has its own README with additional details.
