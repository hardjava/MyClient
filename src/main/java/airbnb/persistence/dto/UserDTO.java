package airbnb.persistence.dto;

import airbnb.network.RoleType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserDTO implements Serializable {
    private int userId;
    private String userName, userBirthday, userPhoneNumber, loginId, loginPwd;
    private RoleType role;
}