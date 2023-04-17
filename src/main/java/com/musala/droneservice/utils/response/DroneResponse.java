package com.musala.droneservice.utils.response;

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
public class DroneResponse {
    private Long id;
    private String serialNumber;
    private Model model;
    private double weightLimit;
    private double batteryCapacityPercentage;
    private State state;
    private String dateCreated;
    private String dateLastUpdated;
}
