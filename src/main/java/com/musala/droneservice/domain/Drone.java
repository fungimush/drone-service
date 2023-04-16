package com.musala.droneservice.domain;

import com.musala.droneservice.utils.enums.Model;
import com.musala.droneservice.utils.enums.State;
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
@Table(name = "drone")

public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private Model model;

    private double weightLimit;
    private double batteryCapacityPercentage;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private State state;
    private String dateCreated;
    @PrePersist
    private void init() {
        this.dateCreated = LocalDateTime.now().toString();
    }
}

