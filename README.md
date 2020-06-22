# Azurras

## Purpose

Azurras is a website created by Christopher Bell with the purpose of holding any general needs and content. The site has many sections including previous or ongoing projects, a blog on any events or new finds, and my resume.

## Setup

### Requirements

1. NPM
1. Java 11
1. Maven

### Building the SASS

1. Go to the home directory of the project.
1. Run the below command.

```bash
gulp sass
```

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
node install
mvn clean package
```

### Running the Project

1. Go to the home directory of the project
1. Run the below command.

```bash
java -jar ./target/output.jar
```






