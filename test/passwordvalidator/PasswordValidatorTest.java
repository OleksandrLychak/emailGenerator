package passwordvalidator;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordValidatorTest {

    @Test
    void emptyPasswordShouldReturnOnlyEmptyPasswordError() {
        List<ValidationError> errors = PasswordValidator.validatePassword("");

        assertEquals(1, errors.size());
        assertEquals(ErrorType.EMPTY_PASSWORD, errors.get(0).getType());
    }

    @Test
    void tooShortPasswordShouldReturnLengthTooShortError() {
        List<ValidationError> errors = PasswordValidator.validatePassword("Ab1!x");

        assertEquals(1, errors.size());
        assertEquals(ErrorType.LENGTH_TOO_SHORT, errors.get(0).getType());
    }

    @Test
    void tooLongPasswordShouldReturnLengthTooLongError() {
        List<ValidationError> errors = PasswordValidator.validatePassword("Aa1!Aa1!Aa1!Aa1!Aa1!Aa1!");

        assertEquals(1, errors.size());
        assertEquals(ErrorType.LENGTH_TOO_LONG, errors.get(0).getType());
    }

    @Test
    void passwordWithSpaceShouldReturnSpaceAndDisallowedCharacterErrors() {
        List<ValidationError> errors = PasswordValidator.validatePassword("Aa1! Bb2@");

        assertEquals(2, errors.size());
        assertEquals(ErrorType.CONTAINS_SPACE, errors.get(0).getType());
        assertEquals(ErrorType.DISALLOWED_CHARACTERS, errors.get(1).getType());
    }

    @Test
    void passwordWithDisallowedCharacterShouldReturnDisallowedCharactersError() {
        List<ValidationError> errors = PasswordValidator.validatePassword("Aa1!€x");

        assertEquals(1, errors.size());
        assertEquals(ErrorType.DISALLOWED_CHARACTERS, errors.get(0).getType());
    }

    @Test
    void passwordWithoutUpperCaseShouldReturnMissingUpperCaseError() {
        List<ValidationError> errors = PasswordValidator.validatePassword("ab1!cd");

        assertEquals(1, errors.size());
        assertEquals(ErrorType.MISSING_UPPERCASE, errors.get(0).getType());
    }

    @Test
    void passwordWithoutLowerCaseShouldReturnMissingLowerCaseError() {
        List<ValidationError> errors = PasswordValidator.validatePassword("AB1!CD");

        assertEquals(1, errors.size());
        assertEquals(ErrorType.MISSING_LOWERCASE, errors.get(0).getType());
    }

    @Test
    void passwordWithoutDigitShouldReturnMissingDigitError() {
        List<ValidationError> errors = PasswordValidator.validatePassword("Ab!Cde");

        assertEquals(1, errors.size());
        assertEquals(ErrorType.MISSING_DIGIT, errors.get(0).getType());
    }

    @Test
    void passwordWithoutSpecialCharacterShouldReturnMissingSpecialCharacterError() {
        List<ValidationError> errors = PasswordValidator.validatePassword("Ab1Cde");

        assertEquals(1, errors.size());
        assertEquals(ErrorType.MISSING_SPECIAL_CHARACTER, errors.get(0).getType());
    }

    @Test
    void fullyValidPasswordShouldReturnNoErrors() {
        List<ValidationError> errors = PasswordValidator.validatePassword("Ab1!Cd2@");

        assertEquals(0, errors.size());
    }
}