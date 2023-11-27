package airbnb.network;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString

public enum Status {
    WAIT, BEFORE_STAY, STAY, AFTER_STAY, REFUSE;
}
