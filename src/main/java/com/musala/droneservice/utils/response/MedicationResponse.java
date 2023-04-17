package com.musala.droneservice.utils.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class MedicationResponse {
    private Long id;
    private String name;
    private double weight;
    private String code;
    //private Image image;
    private String dateCreated;

    private String dateLastUpdated;
}
