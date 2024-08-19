package api.recarbon.auth.exception;

public class UserNotFoundException extends Exception {
    private final String identifier;

    private UserNotFoundException(String identifier) {
        this.identifier = identifier;
    }

    public static UserNotFoundException create(String identifier) {
        return new UserNotFoundException(identifier);
    }

    @Override
    public String getMessage() {
        return "User with email/username: '" + identifier + "' not found";
    }
}
