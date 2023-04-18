package com.musala.droneservice.utils.response;

import com.musala.droneservice.domain.Medication;
import com.musala.droneservice.utils.enums.Model;
import com.musala.droneservice.utils.enums.State;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Data
@Getter
@Setter
@ToString
public class CreateLoadedDroneResponse  {
    private String serialNumber;
    private Model model;
    private double weightLimit ;
    private State state;
    private String dateCreated;
    private double batteryCapacityPercentage;
    private List<Medication> medication;

}
