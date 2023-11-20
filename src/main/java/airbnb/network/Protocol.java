package airbnb.network;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Protocol implements Serializable {
    //타입
    public static final int TYPE_UNDEFINED = 0; //타입이 지정되어 있지 않을 때
    public static final int TYPE1_LOGIN_REQUEST = 1; // 로그인 요청
    public static final int TYPE2_LOGIN_RESPONSE = 2; // 로그인 응답

    //코드
    public static final int CODE_UNDEFINED = 0;
    public static final int CODE_TYPE1_LOGIN = 1;
    public static final int CODE_TYPE2_LOGIN = 2;


    //오류 코드
    public static final int CODE_ERROR = -1;


    private int protocolType;
    private int protocolCode;
    private Object object;

    // 생성자
    public Protocol() {
        this.protocolType = TYPE_UNDEFINED;
        this.protocolCode = CODE_UNDEFINED;
    }

    public Protocol(int protocolType) {
        this.protocolType = protocolType;
        this.protocolCode = CODE_UNDEFINED;
    }

    public Protocol(int protocolType, int protocolCode) {
        this.protocolType = protocolType;
        this.protocolCode = protocolCode;
    }

    public Protocol(int protocolType, int protocolCode, Object object) {
        this.protocolType = protocolType;
        this.protocolCode = protocolCode;
        this.object = object;
    }
}