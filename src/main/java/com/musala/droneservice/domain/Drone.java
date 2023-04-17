package com.musala.droneservice.domain;

import com.musala.droneservice.utils.enums.Model;
import com.musala.droneservice.utils.enums.State;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "drone")

public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Size(min =1, max = 100, message = " Serial number can not be more than 100 characters ")
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private Model model;

//    @Min(0)
//    @Max(500)
    private double weightLimit;
    private double batteryCapacityPercentage;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private State state;
    private String dateCreated;

    private String dateLastUpdated;
    @PrePersist
    private void init() {

        this.dateCreated = LocalDateTime.now().toString();
        this.dateLastUpdated = LocalDateTime.now().toString();
    }
}

