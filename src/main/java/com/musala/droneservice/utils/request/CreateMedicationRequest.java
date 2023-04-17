package com.musala.droneservice.utils.request;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter
@Getter
@ToString
public class CreateMedicationRequest {

    private String name;
    private double weight;
    private String code;
    //private Image image;

}
