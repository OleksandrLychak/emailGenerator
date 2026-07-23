package passwordvalidator;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PasswordValidator {

    private static ValidationError checkLength(String password) {
        int length = password.length();

        if (length == 0) {
            return ValidationError.of(ErrorType.EMPTY_PASSWORD);
        }
        if (length < 6) {
            return ValidationError.of(ErrorType.LENGTH_TOO_SHORT);
        }
        if (length > 20) {
            return ValidationError.of(ErrorType.LENGTH_TOO_LONG);
        }
        return null;
    }

    private static ValidationError checkSpace(String password) {
        if (password.contains(" ")) {
            return ValidationError.of(ErrorType.CONTAINS_SPACE);
        }
        return null;
    }

    private static ValidationError checkDisallowedCharacters(String password) {
        Set<Character> disallowed = new LinkedHashSet<>();

        for (char c : password.toCharArray()) {
            boolean isAllowed = (c >= 'a' && c <= 'z')
                    || (c >= 'A' && c <= 'Z')
                    || (c >= '0' && c <= '9')
                    || AllowedCharacters.SPECIAL_CHARACTERS.indexOf(c) >= 0;

            if (!isAllowed) {
                disallowed.add(c);
            }
        }

        if (disallowed.isEmpty()) {
            return null;
        }

        String joined = disallowed.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        return ValidationError.ofWithDynamicValue(ErrorType.DISALLOWED_CHARACTERS, joined);
    }

    private static ValidationError checkUpperCase(String password) {
        for (char c : password.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                return null;
            }
        }
        return ValidationError.of(ErrorType.MISSING_UPPERCASE);
    }

    private static ValidationError checkLowerCase(String password) {
        for (char c : password.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                return null;
            }
        }
        return ValidationError.of(ErrorType.MISSING_LOWERCASE);
    }

    private static ValidationError checkDigit(String password) {
        for (char c : password.toCharArray()) {
            if (c >= '0' && c <= '9') {
                return null;
            }
        }
        return ValidationError.of(ErrorType.MISSING_DIGIT);
    }

    private static ValidationError checkSpecialCharacter(String password) {
        for (char c : password.toCharArray()) {
            if (AllowedCharacters.SPECIAL_CHARACTERS.indexOf(c) >= 0) {
                return null;
            }
        }
        return ValidationError.of(ErrorType.MISSING_SPECIAL_CHARACTER);
    }

    public static List<ValidationError> validatePassword(String password) {
        List<ValidationError> errors = new ArrayList<>();

        ValidationError lengthError = checkLength(password);
        if (lengthError != null) {
            errors.add(lengthError);
            if (lengthError.getType() == ErrorType.EMPTY_PASSWORD) {
                return errors;
            }
        }

        ValidationError spaceError = checkSpace(password);
        if (spaceError != null) {
            errors.add(spaceError);
        }

        ValidationError disallowedCharactersError = checkDisallowedCharacters(password);
        if (disallowedCharactersError != null) {
            errors.add(disallowedCharactersError);
        }

        ValidationError uppercaseError = checkUpperCase(password);
        if (uppercaseError != null) {
            errors.add(uppercaseError);
        }

        ValidationError lowerCaseError = checkLowerCase(password);
        if (lowerCaseError != null) {
            errors.add(lowerCaseError);
        }

        ValidationError digitError = checkDigit(password);
        if (digitError != null) {
            errors.add(digitError);
        }

        ValidationError specialCharacterError = checkSpecialCharacter(password);
        if (specialCharacterError != null) {
            errors.add(specialCharacterError);
        }

        return errors;
    }
}
