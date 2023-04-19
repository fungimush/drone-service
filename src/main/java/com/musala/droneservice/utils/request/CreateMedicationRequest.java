package com.musala.droneservice.utils.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateMedicationRequest {

    private String name;
    private double weight;
    private String code;
    private String imageName;
    private MultipartFile multipartFile;

}
