package com.musala.droneservice.utils.response;

import com.musala.droneservice.utils.enums.Model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class CreateDroneResponse {
    private String serialNumber;
    private Model model;
    private double weightLimit ;
    private String dateCreated;

}
