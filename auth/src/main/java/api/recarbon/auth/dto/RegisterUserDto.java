package api.recarbon.auth.dto;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String email;
    private String password;
    private String username;
    private String firstName;
    private String lastName;
    private String role;
}
