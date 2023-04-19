package com.musala.droneservice.utils.request;

import com.musala.droneservice.utils.enums.Model;
import com.musala.droneservice.utils.enums.State;
import lombok.*;

import javax.validation.constraints.*;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDroneRequest {

    @Size(min = 1, max = 100, message = "size must be between 1 and 100 characters long")
    private String serialNumber;
    private Model model;

    @Min(0)
    @Max(500)
    private double weightLimit ;
    private double batteryCapacityPercentage;
    private State state;
}

