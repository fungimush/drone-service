package com.musala.droneservice.repository;

import com.musala.droneservice.domain.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    Optional<Medication> findById(Long id);

    Optional<Medication> findMedicationByCode(String code);
}
