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

Tests: `test/passwordvalidator/` (JUnit 5).

### Email Generator (`src/EmailGenerator.java`)

Utility for generating email addresses. _Work in progress._

## Project Structure

LearningProject/
├── src/
│ ├── EmailGenerator.java — email generation task (WIP)
│ └── passwordvalidator/ — password validation task
├── test/
│ └── passwordvalidator/ — JUnit 5 tests for password validator
├── .gitignore
└── README.md

## Requirements

- Java 17 or higher
- IntelliJ IDEA (recommended)
- JUnit 5 (for running tests)

## How to Run

Open the project in IntelliJ IDEA, navigate to the task you want to
run inside `src/`, right-click the corresponding `.java` file and
choose **Run**.

## How to Run Tests

Right-click the test class in `test/` and choose **Run**.

## Roadmap

More QA automation exercises will be added to this repository as new
tasks come up during the course.