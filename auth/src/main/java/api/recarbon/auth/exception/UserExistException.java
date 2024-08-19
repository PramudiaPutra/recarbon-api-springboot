package api.recarbon.auth.exception;

import api.recarbon.auth.entity.UserInfo;

public class UserExistException extends Exception {
    private final UserInfo userInfo;
    private final Exception ex;

    private UserExistException(UserInfo userInfo, Exception ex) {
        this.userInfo = userInfo;
        this.ex = ex;
    }

    public static UserExistException create(UserInfo userInfo, Exception ex) {
        return new UserExistException(userInfo, ex);
    }

    @Override
    public String getMessage() {
        if (ex.getCause().toString().contains("user_email_key")) {
            return "User with Email: '" + userInfo.getEmail() + "' is already exist";
        } else if (ex.getCause().toString().contains("user_username_key")) {
            return "User with Username: '"+ userInfo.getUsername() + "' is already exist";
        } else {
            return ex.getCause().toString();
        }
    }

    @Override
    public synchronized Throwable getCause() {
        return ex.getCause();
    }
}
