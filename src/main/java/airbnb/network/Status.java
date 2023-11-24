package airbnb.network;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString

public enum Status {
    WAIT, NOT_APPROVED, BEFORE_STAY, STAY, AFTER_STAY;
}
