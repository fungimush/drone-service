package com.musala.droneservice.utils.request;

import com.musala.droneservice.utils.enums.Model;
import com.musala.droneservice.utils.enums.State;
import lombok.*;

import javax.validation.constraints.*;

@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateDroneRequest {


    @NotBlank(message = "is a required parameter")
    @Size(min = 1, max = 100, message = "size must be between 1 and 100 characters long")
    private String serialNumber;

    private Model model;

    @NotNull(message = "Can not be null")
    @Min(0)
    @Max(500)
    private double weightLimit ;

    private double batteryCapacityPercentage;
    private State state;
}
