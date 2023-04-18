package com.musala.droneservice.api.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
@ToString
public class MedicationDto {

    private Long id;
    private String name;
    private double weight;
    private String code;
    private String imageName;
    private String dateCreated;
    private MultipartFile multipartFile;
}
