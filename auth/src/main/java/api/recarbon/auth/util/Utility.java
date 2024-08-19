package api.recarbon.auth.util;

import org.apache.commons.validator.routines.EmailValidator;

public class Utility {

    public static boolean validateEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    public static void main(String[] args) {
        EmailValidator ev = EmailValidator.getInstance();
        System.out.println(ev.isValid("1234512313@g.co.uk"));
    }

}
