package airbnb.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class RequestHouseDTO implements Serializable {
    private HouseDTO houseDTO;
    private List<AmenitiesDTO> amenitiesDTOList;  // 기본, 안전, 접근성
}