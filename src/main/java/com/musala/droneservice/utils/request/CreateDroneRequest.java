package com.musala.droneservice.utils.request;

import com.musala.droneservice.utils.enums.Model;
import com.musala.droneservice.utils.enums.State;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter
@Getter
@ToString
public class CreateDroneRequest {

    private String serialNumber;
    private Model model;
    private double weight ;
    private double batteryCapacity;
    private State state;
}
