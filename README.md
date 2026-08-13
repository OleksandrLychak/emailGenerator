# Learning Project — QA Automation

Навчальний репозиторій із практичними завданнями під час навчання
на QA Automation Engineer. Містить кілька невеликих Java-програм,
кожна — окреме навчальне завдання.

## Contents

### Password Validator (`src/passwordvalidator/`)

Console application that validates a password against a fixed set of
rules and reports every rule violation it finds, rather than stopping
at the first one.

**Rules covered:**

- length between 6 and 20 characters
- allowed characters: Latin letters, digits, specific special characters
- no spaces
- must contain at least one uppercase letter, lowercase letter,
  digit and special character

**Tests:**

- JUnit 5: `test/passwordvalidator/PasswordValidatorTest.java`
- TestNG: `test/passwordvalidator/testng/` (see below for how to run)

### Email Generator (`src/emailgenerator/`)

Utility for generating email addresses. _Work in progress._

## Project Structure

```
LearningProject/
├── src/
│   ├── emailgenerator/      — email generation task (WIP)
│   └── passwordvalidator/   — password validation task
├── test/
│   └── passwordvalidator/
│       ├── PasswordValidatorTest.java   — JUnit 5 tests
│       └── testng/                      — TestNG test suite
│           ├── PasswordValidatorTestNGTest.java
│           ├── PasswordTestHelper.java
│           └── testng.xml
├── .gitignore
└── README.md
```

## Requirements

- Java 17 or higher
- IntelliJ IDEA (recommended)
- JUnit 5 (for JUnit tests)
- TestNG 7.10.2 (for TestNG tests, JARs in `lib/`)

## How to Run

Open the project in IntelliJ IDEA, navigate to the task you want to
run inside `src/`, right-click the corresponding `.java` file and
choose **Run**.

## How to Run Tests

### JUnit 5

Right-click `PasswordValidatorTest` in `test/passwordvalidator/` and
choose **Run**.

### TestNG

**From IntelliJ IDEA:** right-click `testng.xml` and choose
**Run 'testng.xml'**, or right-click `PasswordValidatorTestNGTest`
and choose **Run**.

**From the command line:**

```bash
# Compile everything
javac -cp "src:lib/*" -d out src/passwordvalidator/*.java test/passwordvalidator/testng/*.java

# Run the whole TestNG suite
java -cp "out:lib/*" org.testng.TestNG test/passwordvalidator/testng/testng.xml
```

The suite is organised into three runs — All tests, Smoke tests only,
and Negative scenarios — and uses five groups: `smoke`, `regression`,
`positive`, `negative`, `boundary`.

## Roadmap

More QA automation exercises will be added to this repository as new
tasks come up during the course.
