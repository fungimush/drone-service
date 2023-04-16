package com.musala.droneservice.repository;

import com.musala.droneservice.domain.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DispatchRepository extends JpaRepository <Drone, Long> {

    Optional<Drone> findById(Long id);

    Optional<Drone> findDroneBySerialNumber (String serialNumber);

}
