package airbnb.persistence.dto;

import airbnb.network.RoleType;
import lombok.*;


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class InsertUserDTO {
    private int userId;
    private String userName, userPhoneNumber, loginId, loginPwd, userBirthday;
    private RoleType role;

}
