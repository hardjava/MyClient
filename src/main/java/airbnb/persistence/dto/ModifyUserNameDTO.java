package airbnb.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class ModifyUserNameDTO implements Serializable {
    private int userId;
    private String userName;
}
