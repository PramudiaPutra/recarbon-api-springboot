package api.recarbon.auth.payload;

import api.recarbon.auth.dto.UserInfoDto;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UserResponse {
    int responseCode;
    HttpStatus statusCode;
    String statusMessage;
    Object data;

    public UserResponse(HttpStatus statusCode, String statusMessage, Object data) {
        this.responseCode = statusCode.value();
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.data = data;
    }
}
