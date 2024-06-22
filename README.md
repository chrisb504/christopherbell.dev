# 


## Quickstart
```bash
npm install
npm run-script build
mvn clean package
java -jar /target/{jar-name-here}
```


## Setup

### Requirements

1. NPM
1. Java 14
1. Maven

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

1. Go to the home directory of the project
1. Run the below commands.

```bash
npm install
mvn clean package
```

### Running the Project

1. Go to the home directory of the project
1. Run the below command.

```bash
java -jar ./target/output.jar
```






