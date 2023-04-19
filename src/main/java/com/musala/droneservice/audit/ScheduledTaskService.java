package com.musala.droneservice.audit;

import com.musala.droneservice.domain.Drone;
import com.musala.droneservice.repository.DispatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTaskService {
    private final DispatchRepository dispatchRepository;

    public ScheduledTaskService(DispatchRepository dispatchRepository) {
        this.dispatchRepository = dispatchRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(fixedRate = 40000)
    public void execute() {

        List<Drone> drones = dispatchRepository.findAll();
        for (Drone drone : drones) {
            logger.info(">>> The battery level for drone with serialNumber:: {}, is::  {}%,  State::  {}",
                    drone.getSerialNumber(), drone.getBatteryCapacityPercentage(), drone.getState());
        }
    }
}