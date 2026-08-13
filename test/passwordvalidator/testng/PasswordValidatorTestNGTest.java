package passwordvalidator.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import passwordvalidator.ErrorType;
import passwordvalidator.PasswordValidator;
import passwordvalidator.ValidationError;

import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * TestNG test suite for PasswordValidator.
 * Covers positive, negative and boundary scenarios, demonstrates use of DataProvider, groups,
 * @BeforeMethod / @AfterMethod lifecycle hooks,
 * assertions, collections and a custom helper class.
 */
public class PasswordValidatorTestNGTest {

    private long testStartTime;
    private String currentTestName;

    // Lifecycle hooks

    @BeforeMethod
    public void beforeEachTest(java.lang.reflect.Method method) {
        currentTestName = method.getName();
        testStartTime = System.currentTimeMillis();
        System.out.println("▶ Starting test: " + currentTestName);
    }

    @AfterMethod
    public void afterEachTest() {
        long duration = System.currentTimeMillis() - testStartTime;
        System.out.println("✓ Finished test: " + currentTestName + " (took " + duration + " ms)");
    }

    // Data providers

    @DataProvider(name = "validPasswords")
    public Object[][] validPasswordsProvider() {
        return new Object[][]{
                {"Ab1!Cd2@"},
                {"Xyz9#Qwer"},
                {"Test1$Password"},
                {"Hello2&World"}
        };
    }

    @DataProvider(name = "invalidPasswordsWithExpectedError")
    public Object[][] invalidPasswordsProvider() {
        return new Object[][]{
                {"ab1!cd", ErrorType.MISSING_UPPERCASE},
                {"AB1!CD", ErrorType.MISSING_LOWERCASE},
                {"Ab!Cde", ErrorType.MISSING_DIGIT},
                {"Ab1Cde", ErrorType.MISSING_SPECIAL_CHARACTER}
        };
    }

    // Positive scenarios

    @Test(dataProvider = "validPasswords", groups = {"smoke", "positive"})
    public void validPasswordShouldReturnNoErrors(String password) {
        List<ValidationError> errors = PasswordValidator.validatePassword(password);

        assertNotNull(errors, "Errors list should never be null");
        assertEquals(errors.size(), 0, "Valid password should produce zero errors");
    }

    @Test(groups = {"boundary", "positive"})
    public void passwordOfExactlySixCharactersShouldBeValid() {
        String password = "Ab1!Cd";
        List<ValidationError> errors = PasswordValidator.validatePassword(password);

        assertEquals(errors.size(), 0, "Password of exactly 6 characters must be valid");
    }

    @Test(groups = {"boundary", "positive"})
    public void passwordOfExactlyTwentyCharactersShouldBeValid() {
        String password = "Ab1!" + PasswordTestHelper.generateStringOfLength('x', 15) + "Y";
        assertEquals(password.length(), 20, "Sanity check: test password must be exactly 20 chars");

        List<ValidationError> errors = PasswordValidator.validatePassword(password);
        assertEquals(errors.size(), 0, "Password of exactly 20 characters must be valid");
    }

    // Negative scenarios — single-issue passwords via DataProvider

    @Test(dataProvider = "invalidPasswordsWithExpectedError", groups = {"regression", "negative"})
    public void invalidPasswordShouldContainExpectedError(String password, ErrorType expectedError) {
        List<ValidationError> errors = PasswordValidator.validatePassword(password);

        assertTrue(
                PasswordTestHelper.containsErrorType(errors, expectedError),
                "Errors should contain " + expectedError + " for password: " + password
        );
    }

    // Negative scenarios — specific edge cases

    @Test(groups = {"smoke", "negative"})
    public void emptyPasswordShouldReturnOnlyEmptyPasswordError() {
        List<ValidationError> errors = PasswordValidator.validatePassword("");

        assertEquals(errors.size(), 1, "Empty password should short-circuit with exactly one error");
        assertEquals(errors.get(0).getType(), ErrorType.EMPTY_PASSWORD);
    }

    @Test(groups = {"boundary", "negative"})
    public void tooShortPasswordShouldReturnLengthTooShortError() {
        String password = "Ab1!x";
        List<ValidationError> errors = PasswordValidator.validatePassword(password);

        assertTrue(
                PasswordTestHelper.containsErrorType(errors, ErrorType.LENGTH_TOO_SHORT),
                "Password shorter than 6 must include LENGTH_TOO_SHORT error"
        );
    }

    @Test(groups = {"boundary", "negative"})
    public void tooLongPasswordShouldReturnLengthTooLongError() {
        String password = "Ab1!" + PasswordTestHelper.generateStringOfLength('x', 20);
        List<ValidationError> errors = PasswordValidator.validatePassword(password);

        assertTrue(
                PasswordTestHelper.containsErrorType(errors, ErrorType.LENGTH_TOO_LONG),
                "Password longer than 20 must include LENGTH_TOO_LONG error"
        );
    }

    @Test(groups = {"regression", "negative"})
    public void passwordWithSpaceShouldReportSpaceAndDisallowedCharacter() {
        String password = "Aa1! Bb2@";
        List<ValidationError> errors = PasswordValidator.validatePassword(password);

        assertTrue(
                PasswordTestHelper.containsErrorType(errors, ErrorType.CONTAINS_SPACE),
                "Password with space must include CONTAINS_SPACE error"
        );
        assertTrue(
                PasswordTestHelper.containsErrorType(errors, ErrorType.DISALLOWED_CHARACTERS),
                "Space is not in allowed characters, so DISALLOWED_CHARACTERS is also expected"
        );
    }

    @Test(groups = {"regression", "negative"})
    public void passwordWithDisallowedCharacterShouldReportDisallowedError() {
        String password = "Aa1!€x";
        List<ValidationError> errors = PasswordValidator.validatePassword(password);

        assertTrue(
                PasswordTestHelper.containsErrorType(errors, ErrorType.DISALLOWED_CHARACTERS),
                "Password containing € must report DISALLOWED_CHARACTERS"
        );
    }

    // Collection-based checks — using the helper

    @Test(groups = {"regression"})
    public void errorsListShouldNotContainDuplicateTypes() {
        String password = "ab kd 74@@";
        List<ValidationError> errors = PasswordValidator.validatePassword(password);

        Set<ErrorType> uniqueTypes = PasswordTestHelper.extractUniqueErrorTypes(errors);

        assertEquals(
                uniqueTypes.size(),
                errors.size(),
                "Every ErrorType should appear at most once in the errors list"
        );
    }

    @Test(groups = {"regression", "positive"})
    public void validPasswordShouldNotContainAnyKnownErrorType() {
        List<ValidationError> errors = PasswordValidator.validatePassword("Ab1!Cd2@");

        for (ErrorType type : ErrorType.values()) {
            assertFalse(
                    PasswordTestHelper.containsErrorType(errors, type),
                    "Valid password should not produce error of type: " + type
            );
        }
    }
}