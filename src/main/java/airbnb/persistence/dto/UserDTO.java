package airbnb.persistence.dto;

import airbnb.network.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserDTO implements Serializable {
    private int userId;
    private String userName, userPhone, loginId;
    private RoleType role;
    private String loginPwd, userBirthday;

    public UserDTO() {
    }

    public UserDTO(String userName, String userPhone, String loginId, RoleType role, String loginPwd, String userBirthday) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.loginId = loginId;
        this.role = role;
        this.loginPwd = loginPwd;
        this.userBirthday = userBirthday;
    }

    public UserDTO(int userId, String userName, String userPhone, String userBirthday) {
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userBirthday = userBirthday;
    }
}