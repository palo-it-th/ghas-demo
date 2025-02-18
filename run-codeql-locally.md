# Running Custom CodeQL Queries Locally

## Prerequisites

### Install CodeQL

To install CodeQL on macOS using Homebrew, run:

```shell
brew install --cask codeql
```

## Creating a CodeQL Database

Create a CodeQL database for a Java project using the following command:

```shell
codeql database create ./db-demo --language=java
```

After running this command, the database will be created in the `db-demo` folder.

## Installing Query Packs for Custom Queries

Before running custom queries, install the required CodeQL query packs:

```shell
codeql pack install ./custom-queries
```

## Running Custom CodeQL Queries

To analyze the `db-demo` database using custom CodeQL queries from the [`custom-queries`](custom-queries) folder, run:

```shell
codeql database analyze ./db-demo ./custom-queries --format=sarif-latest --output=./results.sarif
```

## Install GitHub Built-In Java Queries

Before running GitHub's built-in Java queries, install the required CodeQL query packs:

```shell
codeql pack download codeql/java-queries
```

## Running Built-in Java Queries

To analyze the database using GitHub's built-in Java queries, use:

```shell
codeql database analyze ./db-demo codeql/java-queries --format=sarif-latest --output=./results.sarif
```

### Output and Viewing Results

- You can replace `./results.sarif` with your preferred output file path.
- To view SARIF results, install the **SARIF Viewer** extension in Visual Studio Code.
- Additionally, install the **CodeQL** extension in Visual Studio Code for a better query development experience.

## References

- [Getting Started with CodeQL for VS Code](https://docs.github.com/en/code-security/codeql-for-vs-code/getting-started-with-codeql-for-vs-code/installing-codeql-for-vs-code)
- [Analyze Vulnerabilities in Your Code with CodeQL Locally](https://luisrangelc.medium.com/analyze-vulnerabilities-in-your-code-with-codeql-locally-3e5d31f8ed2)
