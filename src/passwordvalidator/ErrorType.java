package passwordvalidator;

public enum ErrorType {
    EMPTY_PASSWORD("Password cannot be empty"),
    LENGTH_TOO_SHORT("Password is too short"),
    LENGTH_TOO_LONG("Password is too long"),
    CONTAINS_SPACE("Password must not contain spaces"),
    DISALLOWED_CHARACTERS("Password contains disallowed characters: %s"),
    MISSING_UPPERCASE("Password must contain at least one uppercase letter"),
    MISSING_LOWERCASE("Password must contain at least one lowercase letter"),
    MISSING_DIGIT("Password must contain at least one digit"),
    MISSING_SPECIAL_CHARACTER("Password must contain at least one special character");

    private final String messageTemplate;

    ErrorType(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }

}

