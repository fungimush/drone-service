package com.musala.droneservice.utils.response;

import com.musala.droneservice.utils.enums.Model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@ToString
public class CreateDroneResponse {


    private String serialNumber;
    private Model model;
    private double weight ;
    private String dateCreated;

}
