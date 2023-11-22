package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.ModifyBirthdayDTO;
import airbnb.persistence.dto.ModifyPhoneNumberDTO;
import airbnb.persistence.dto.ModifyUserNameDTO;

import java.io.IOException;

public class ModifyUserInfoController {
    public Protocol modifyNameRequest(int userId, String newName) throws IOException, ClassNotFoundException {
        ModifyUserNameDTO modifyUserNameDTO = new ModifyUserNameDTO(userId, newName);
        Protocol protocol = new Protocol(Protocol.TYPE_PERSONAL_INFO_EDIT, Protocol.CODE_SEND_MODIFY_NAME_INFO, modifyUserNameDTO);
        MyIOStream.oos.writeObject(protocol);
        // 대기 wait
        return (Protocol) MyIOStream.ois.readObject();
    }

    public Protocol modifyPhoneNumberRequest(int userId, String newPhoneNumber) throws IOException, ClassNotFoundException {
        ModifyPhoneNumberDTO modifyPhoneNumberDTO = new ModifyPhoneNumberDTO(userId, newPhoneNumber);
        Protocol protocol = new Protocol(Protocol.TYPE_PERSONAL_INFO_EDIT, Protocol.CODE_SEND_MODIFY_PHONENUMBER_INFO, modifyPhoneNumberDTO);
        MyIOStream.oos.writeObject(protocol);
        // 대기 wait
        return (Protocol) MyIOStream.ois.readObject();
    }

    public Protocol modifyBirthDayRequest(int userId, String newBirthDay) throws IOException, ClassNotFoundException {
        ModifyBirthdayDTO modifyBirthdayDTO = new ModifyBirthdayDTO(userId, newBirthDay);
        Protocol protocol = new Protocol(Protocol.TYPE_PERSONAL_INFO_EDIT, Protocol.CODE_SEND_MODIFY_BIRTHDAY_INFO, modifyBirthdayDTO);
        MyIOStream.oos.writeObject(protocol);
        // 대기 wait
        return (Protocol) MyIOStream.ois.readObject();
    }
}
