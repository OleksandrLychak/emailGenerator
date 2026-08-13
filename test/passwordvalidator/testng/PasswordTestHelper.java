package passwordvalidator.testng;

import passwordvalidator.ErrorType;
import passwordvalidator.ValidationError;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Helper class with utility methods for TestNG tests
 * Provides reusable logic for asserting and inspecting validation results
 */
public class PasswordTestHelper {

    /**
     * Checks whether the given list of validation errors contains an error with the specified type
     *
     * @param errors    list returned by PasswordValidator.validatePassword()
     * @param errorType type to search for
     * @return true if the error type is present in the list
     */
    public static boolean containsErrorType(List<ValidationError> errors, ErrorType errorType) {
        for (ValidationError error : errors) {
            if (error.getType() == errorType) {
                return true;
            }
        }
        return false;
    }

    /**
     * Extracts a set of unique error types from the given list of errors
     * Uses Set because the same error type should never appear twice
     *
     * @param errors list returned by PasswordValidator.validatePassword()
     * @return set of unique error types
     */
    public static Set<ErrorType> extractUniqueErrorTypes(List<ValidationError> errors) {
        return errors.stream()
                .map(ValidationError::getType)
                .collect(Collectors.toSet());
    }

    /**
     * Generates a repeated character string of the specified length
     * Useful for building test passwords of exact lengths (short, long, boundary)
     *
     * @param character character to repeat
     * @param length    desired length
     * @return string of the given length filled with the character
     */
    public static String generateStringOfLength(char character, int length) {
        return String.valueOf(character).repeat(length);
    }
}