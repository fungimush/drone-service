package com.musala.droneservice.domain;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Column(nullable = true, length = 64)
    private String imageName;
    private String dateCreated;
    private String dateLastUpdated;

    @PrePersist
    public void init() {
        dateCreated = LocalDateTime.now().toString();
    }

    @PreUpdate
    public void update() {
        dateLastUpdated = LocalDateTime.now().toString();
    }
}



