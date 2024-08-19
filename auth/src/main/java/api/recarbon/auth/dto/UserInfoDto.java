package api.recarbon.auth.dto;

import api.recarbon.auth.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public UserInfoDto(UserInfo userInfo) {
        this.username = userInfo.getUsername();
        this.firstName = userInfo.getFirstName();
        this.lastName = userInfo.getLastName();
        this.email = userInfo.getEmail();
    }
}
