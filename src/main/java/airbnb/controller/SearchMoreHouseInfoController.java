package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.HouseDTO;

import java.io.IOException;

public class SearchMoreHouseInfoController {
    public Protocol printMoreInfo(int houseId) throws IOException, ClassNotFoundException {
        HouseDTO houseDTO = new HouseDTO(houseId);
        Protocol protocol = new Protocol(Protocol.TYPE_SELECT_HOUSE_VIEW_DETAIL, Protocol.CODE_SELECT_HOUSE_INFO_REQUEST, houseDTO);
        MyIOStream.oos.writeObject(protocol);
        // 대기 wait
        return (Protocol) MyIOStream.ois.readObject();
    }


}
