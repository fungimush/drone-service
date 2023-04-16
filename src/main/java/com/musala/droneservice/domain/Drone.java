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
    private Model model;
    private double weight ;
    private double batteryCapacity;
    private State state;
    private String dateCreated;
    @PrePersist
    private void init() {
        this.dateCreated = LocalDateTime.now().toString();
    }
}

