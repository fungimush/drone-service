package com.musala.droneservice.utils.response;

import com.musala.droneservice.domain.Medication;
import com.musala.droneservice.utils.enums.State;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Data
@Setter
@Getter
@ToString
public class CreateLoadMedicationResponse {

    private Long id;
    private String dateCreated;
    private String serialNumber;
    private State state;
    private List<Medication> medication;

}
