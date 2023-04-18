package com.musala.droneservice.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "medication")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double weight;
    private String code;
    private String imageName;
    private String dateCreated;

    private String dateLastUpdated;
    @PrePersist
    private void init() {

        this.dateCreated = LocalDateTime.now().toString();
        this.dateLastUpdated = LocalDateTime.now().toString();
    }

}
