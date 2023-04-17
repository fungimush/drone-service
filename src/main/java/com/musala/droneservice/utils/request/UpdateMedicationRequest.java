package com.musala.droneservice.utils.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter@ToString
public class UpdateMedicationRequest {

    private String name;
    private double weight;
    private String code;
    //private Image image;
}
