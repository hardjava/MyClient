package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.FeePolicyDTO;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;

public class SetCostPolicyConroller {
    public Protocol sendHouseListRequest(UserDTO userDTO) throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_SET_COST_POLICY, Protocol.CODE_REQUEST_APPROVED_NOT_SET_FEE_POLICY_HOUSE_LIST, userDTO);
        MyIOStream.oos.writeObject(protocol);
        //
        return (Protocol) MyIOStream.ois.readObject();
    }

    public Protocol costSettingRequest(FeePolicyDTO feePolicyDTO) throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_SET_COST_POLICY, Protocol.CODE_SEND_WEEKEND_WEEKDAYS_COST_POLICY, feePolicyDTO);
        MyIOStream.oos.writeObject(protocol);
        //
        return (Protocol) MyIOStream.ois.readObject();
    }
}
