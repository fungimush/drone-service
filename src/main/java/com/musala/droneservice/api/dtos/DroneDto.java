package com.musala.droneservice.api.dtos;

import com.musala.droneservice.utils.enums.Model;
import com.musala.droneservice.utils.enums.State;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class DroneDto {
    private Long id;
    private String serialNumber;
    private Model model;
    private double weightLimit;
    private double batteryCapacityPercentage;
    private State state;
    private String dateCreated;
}
