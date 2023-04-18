package com.musala.droneservice.utils.response;

import com.musala.droneservice.utils.enums.Model;
import com.musala.droneservice.utils.enums.State;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Getter
@Setter
@ToString
public class CreateDroneResponse {
    private String serialNumber;
    private Model model;
    private double weightLimit ;
    private double batteryCapacityPercentage;
    private State state;
    private String dateCreated;

}






