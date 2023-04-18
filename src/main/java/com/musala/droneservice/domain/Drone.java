package com.musala.droneservice.domain;

import com.musala.droneservice.utils.enums.Model;
import com.musala.droneservice.utils.enums.State;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

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

    @Size(min =1, max = 100, message = " Serial number can not be more than 100 characters ")
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private Model model;


    @Min(value = 0, message="Weight can not be below 0")
    @Max(value = 500, message="Weight can not be above 500gr")
    private double weightLimit;
    private double batteryCapacityPercentage;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private State state;
    private String dateCreated;
    private String dateLastUpdated;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "drone_medication",
            joinColumns = @JoinColumn(name = "drone_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id"),
            indexes = {
                    @Index(name = "idx_drone_service_drone_id", columnList = "drone_id"),
                    @Index(name = "idx_drone_service_medication_id", columnList = "medication_id")
            }
    )

    private List<Medication> medication;
    @PrePersist
    private void init() {

        this.dateCreated = LocalDateTime.now().toString();
        this.dateLastUpdated = LocalDateTime.now().toString();
    }
}

