package com.musala.droneservice.utils.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class CreateBatteryLevelResponse {

    private String serialNumber;
    private double batteryCapacityPercentage;
    private String dateCreated;
}
