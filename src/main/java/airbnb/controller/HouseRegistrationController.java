package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.AmenitiesDTO;
import airbnb.persistence.dto.HouseDTO;
import airbnb.persistence.dto.RequestHouseDTO;

import java.io.IOException;
import java.util.List;

public class HouseRegistrationController {
    public Protocol houseRegisterRequest(HouseDTO houseDTO, List<AmenitiesDTO> amenitiesDTOList) throws IOException, ClassNotFoundException {
        RequestHouseDTO requestHouseDTO = new RequestHouseDTO(houseDTO, amenitiesDTOList);
        Protocol protocol = new Protocol(Protocol.TYPE_HOUSE_REGISTRATION, Protocol.CODE_SEND_REGISTRATION_HOUSE_INFO, requestHouseDTO);
        MyIOStream.oos.writeObject(protocol);
        // wait
        return (Protocol) MyIOStream.ois.readObject();
    }
}
