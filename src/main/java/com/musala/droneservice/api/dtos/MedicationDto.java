package com.musala.droneservice.api.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import javax.persistence.Transient;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(NON_NULL)
public class MedicationDto {


    private String name;
    private double weight;
    private String code;
    private String imageName;

    @Transient
    public String getImagePath() {
        if (imageName == null || code == null) return null;

        return "/MedicationImages/" + code + "/" + imageName;
    }

}
