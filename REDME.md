# Password Validator

A simple console-based Java application that validates a password against a fixed
set of rules and reports every rule violation it finds, rather than stopping at the
first one.

## Description

The user is prompted to enter a password in the console. The application checks the
input against the validation rules below and prints either a confirmation that the
password is valid, or a list of every specific problem found with it.

## Validation Rules

1. Length must be between **6 and 20** characters (inclusive).
2. Only Latin letters are allowed: `a-z`, `A-Z`.
3. Digits are allowed: `0-9`.
4. Only the following special characters are allowed:
   ```
   . , ! # $ % & ' * + - / = ? ^ _ ` { | [ \ ] ~ " ( ) : ; < > @
   ```
5. Spaces are **not** allowed.
6. The password **must** contain at least one character from each of the following
   categories:
    - an uppercase letter
    - a lowercase letter
    - a digit
    - a special character (from the list above)

If the password is empty, the application reports this as a single, separate error
("This field is required") and does not run any of the other checks.

## Project Structure

All classes belong to the `passwordvalidator` package.

| File                     | Responsibility                                                                 |
|--------------------------|---------------------------------------------------------------------------------|
| `Main.java`              | Entry point. Reads the password from console input and prints the result.       |
| `PasswordValidator.java` | Contains all validation logic; returns a list of found errors.                  |
| `ErrorType.java`         | Enum listing every possible validation error, with its message template.        |
| `ValidationError.java`   | Wraps an `ErrorType` together with the final, formatted message text.           |
| `AllowedCharacters.java` | Stores the constant string of allowed special characters.                       |

Unit tests live in the parallel `test/passwordvalidator` source root, in
`PasswordValidatorTest.java`.

## How to Compile and Run

### From the command line

```bash
javac -d out src/passwordvalidator/*.java
java -cp out passwordvalidator.Main
```

### From IntelliJ IDEA

Open the project, right-click `Main.java` inside `src/passwordvalidator`, and choose
**Run 'Main.main()'**.

## Example Usage

**Valid password:**
```
Enter your password
Ab1!Cd2@
Password is valid
```

**Invalid password (multiple problems at once):**
```
Enter your password
ab kd 74@@
Password is invalid
- Password must not contain spaces
- Password contains disallowed characters: @
- Password must contain at least one uppercase letter
```

**Empty password (short-circuit case):**
```
Enter your password

Password is invalid
- This field is required
```

## Testing

The project includes a JUnit 5 test class (`PasswordValidatorTest`) covering:

- empty password (verifies the short-circuit behavior)
- password shorter than 6 characters
- password longer than 20 characters
- password containing a space
- password containing a disallowed character
- password missing an uppercase letter
- password missing a lowercase letter
- password missing a digit
- password missing a special character
- a fully valid password (expects zero errors)

Run all tests via **Run 'PasswordValidatorTest'** in IntelliJ IDEA.

## Possible Future Improvements

- Allow the user multiple attempts in a loop instead of exiting after one check.
- Make the validation rules (length range, allowed characters, mandatory
  categories) configurable instead of hard-coded.
- Add a graphical or web interface instead of console-only interaction.