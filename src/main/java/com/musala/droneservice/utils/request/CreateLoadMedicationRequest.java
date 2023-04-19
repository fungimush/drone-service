package com.musala.droneservice.utils.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateLoadMedicationRequest {

    @NotNull(message = "droneId can not be null")
    private Long droneId;

    @NotNull(message = "Medication ids can not be null")
    @NotEmpty(message = "Medication ids can not be empty")
    private List<Long> medicationIds;

}
