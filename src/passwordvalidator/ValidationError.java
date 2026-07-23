package passwordvalidator;

public class ValidationError {
    private final ErrorType type;
    private final String message;

    private ValidationError(ErrorType type, String message) {
        this.type = type;
        this.message = message;
    }

    public static ValidationError of(ErrorType type) {
        if (type == ErrorType.DISALLOWED_CHARACTERS) {
            throw new IllegalArgumentException("Use ofWithDynamicValue() for DISALLOWED_CHARACTERS");
        }
        return new ValidationError(type, type.getMessageTemplate());
    }

    public static ValidationError ofWithDynamicValue(ErrorType type, String dynamicValue) {
        String formattedMessage = String.format(type.getMessageTemplate(), dynamicValue);
        return new ValidationError(type, formattedMessage);
    }

    public ErrorType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ValidationError{" + "type=" + type + ", message='" + message + '\'' + '}';
    }
}