package api.recarbon.auth.payload;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private long expiredIn;
    private String email;
}
