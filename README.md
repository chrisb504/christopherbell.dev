# christopherbell.dev

## Quickstart

```bash
npm install
npm run-script build
mvn clean package
java -jar /target/{jar-name-here}
```

## Setup

### Requirements

1. Java 21
2. Maven
3. Postgresql
4. NPM

### Interesting Tools

1. pgAdmin 4 - for Postgresql

### Building the CSS

The CSS is built using maven. When running the package command, the css should be compiled and minified.

The output file is located at ./src/main/resources/static/css/main.min.css

### Building the Javascript

1. Go to the home directory of the project
1. Run the below command.

```bash
npm run-script build
```
The output file is located at ./src/main/resources/static/js/main.js

### Building the Project

You should think about this as two steps. There is a frontend and there is a backend.
We need to compile everything for the frontend first, then we need to package those frontend
components into the jar file where the backend resources will live. Our final product is a
single package with all of our components for the frontend and the api.

```npm run-script build```


```npm run-script build```

This command will compile our frontend components.

```mvn clean package```

This command will compile our backend components.

```npm run-script build;mvn clean package```

You could do this and combine these steps into one.

```bash
npm install
mvn clean package
```

### Running the Project

Set SPRING_ACTIVE_PROFILE to "local" in your environment variables

1. Go to the home directory of the project
1. Run the below command.

```bash
java -jar ./target/output.jar
```
