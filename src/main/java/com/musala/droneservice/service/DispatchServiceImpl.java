package com.musala.droneservice.service;

import com.musala.droneservice.domain.Drone;
import com.musala.droneservice.domain.Medication;
import com.musala.droneservice.repository.DispatchRepository;
import com.musala.droneservice.repository.MedicationRepository;
import com.musala.droneservice.utils.enums.I18Code;
import com.musala.droneservice.utils.enums.State;
import com.musala.droneservice.utils.exceptions.ServiceException;
import com.musala.droneservice.utils.i18.MessageService;
import com.musala.droneservice.utils.request.CreateDroneRequest;
import com.musala.droneservice.utils.request.CreateLoadMedicationRequest;
import com.musala.droneservice.utils.request.UpdateDroneRequest;
import com.musala.droneservice.utils.response.*;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class DispatchServiceImpl implements DispatchService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DozerBeanMapper mapper;
    private final DispatchRepository dispatchRepository;

    private final MedicationRepository medicationRepository;
    private final MessageService messageService;

    public DispatchServiceImpl(DozerBeanMapper mapper, DispatchRepository dispatchRepository, MedicationRepository medicationRepository, MessageService messageService) {
        this.mapper = mapper;
        this.dispatchRepository = dispatchRepository;
        this.medicationRepository = medicationRepository;
        this.messageService = messageService;
    }

    @Override
    public ApiResponse<?> create(CreateDroneRequest createDroneRequest, Locale locale) throws Exception {
        Optional<Drone> droneWithSerialNumber = dispatchRepository.findDroneBySerialNumber(createDroneRequest.getSerialNumber());
        if (droneWithSerialNumber.isPresent())
            throw new ServiceException(
                    messageService.getMessage(I18Code.MESSAGE_RECORD_ALREADY_EXIST.getCode(), new String[]{Drone.class.getSimpleName()}, locale),
                    HttpStatus.CONFLICT.value()
            );

        Drone newDrone = mapper.map(createDroneRequest, Drone.class);
        Drone savedDrone = dispatchRepository.save(newDrone);
        logger.info(">>> Drone with Serial Number::{} registered successfully", savedDrone.getSerialNumber());
        return new ApiResponse
                .ApiResponseBuilder<>(messageService.getMessage(I18Code.MESSAGE_RECORD_CREATED_SUCCESSFULLY.getCode(), new String[]{Drone.class.getSimpleName()}, locale))
                .setData(mapper.map(savedDrone, CreateDroneResponse.class))
                .build();

    }

    @Override
    public ApiResponse<?> edit(Long id, UpdateDroneRequest updateDroneRequest, Locale locale) throws ServiceException {
        Drone drone = dispatchRepository.findById(id).
                orElseThrow(() -> new ServiceException(
                            messageService.getMessage(I18Code.MESSAGE_RECORD_NOT_FOUND.getCode(), new String[]{Drone.class.getSimpleName()}, locale),
                            HttpStatus.NOT_FOUND.value())
                    );

            if(updateDroneRequest.getSerialNumber()!=null) {
                if (updateDroneRequest.getSerialNumber().length() > 100) {
                    throw new ServiceException(
                            messageService.getMessage(I18Code.MESSAGE_RECORD_INVALID_SERIAL_NUMBER.getCode(), new String[]{"Serial number can not have more than 100 characters"}, locale),
                            HttpStatus.CONFLICT.value()
                    );
                }
                drone.setSerialNumber(updateDroneRequest.getSerialNumber());
            }

            if(updateDroneRequest.getWeightLimit()!=0) {
                if (updateDroneRequest.getWeightLimit() > 500) {
                    throw new ServiceException(
                            messageService.getMessage(I18Code.MESSAGE_RECORD_INVALID_WEIGHT.getCode(), new String[]{"Weight can not be more than 500gr"}, locale),
                            HttpStatus.CONFLICT.value()
                    );
                }
                drone.setWeightLimit(updateDroneRequest.getWeightLimit());
            }

        if(updateDroneRequest.getBatteryCapacityPercentage()!=0) {
            drone.setBatteryCapacityPercentage(updateDroneRequest.getBatteryCapacityPercentage());
        }
        if(updateDroneRequest.getModel()!=null) {
            drone.setModel(updateDroneRequest.getModel());
        }
        if(updateDroneRequest.getState()!=null) {
            drone.setState(updateDroneRequest.getState());
        }

        drone.setDateLastUpdated(LocalDateTime.now().toString());

        logger.info(">>> Drone info::{} ", drone);

            Drone savedDrone = dispatchRepository.save(drone);
            return new ApiResponse
                    .ApiResponseBuilder<>(messageService.getMessage(I18Code.MESSAGE_RECORD_UPDATED_SUCCESSFULLY.getCode(), new String[]{Drone.class.getSimpleName()}, locale))
                    .setData(mapper.map(savedDrone, UpdateDroneResponse.class))
                    .build();
        }



    @Override
    public List<DroneResponse> getAllDrones() {
        List<Drone> drones = dispatchRepository.findAll();
        List<DroneResponse> droneResponses = new ArrayList<>();
        for (Drone drone : drones) {
            DroneResponse spResponse = mapper.map(drone, DroneResponse.class);
            droneResponses.add(spResponse);
        }
        return droneResponses;
    }

    @Override
    public ApiResponse<?> loadMedication(CreateLoadMedicationRequest createLoadMedicationRequest, Locale locale) throws Exception {
        Optional<Drone> droneWithId = dispatchRepository.findById(createLoadMedicationRequest.getDroneId());
        if (!droneWithId.isPresent())
            throw new ServiceException(
                    messageService.getMessage(I18Code.MESSAGE_RECORD_NOT_FOUND.getCode(), new String[]{Drone.class.getSimpleName()}, locale),
                    HttpStatus.CONFLICT.value(
                    ));

        //TO DO
        ///Check if it is possible to load a loading  or loaded drone
        Drone drone = dispatchRepository.findById(createLoadMedicationRequest.getDroneId()).get();
        if(!((drone.getState().equals(State.IDLE))|(drone.getState().equals(State.LOADED)))){
            throw new ServiceException(
                    messageService.getMessage(I18Code.MESSAGE_RECORD_INVALID_STATE.getCode(), new String[]{"Drone in"}, locale),
                    HttpStatus.CONFLICT.value(
                    ));
        }


        List<Medication> moreMedication = droneWithId.get().getMedication();
            for (Long medicationId : createLoadMedicationRequest.getMedicationIds()) {
                Medication medication = medicationRepository.findById(medicationId).
                        orElseThrow(() -> new ServiceException(
                                messageService.getMessage(I18Code.MESSAGE_RECORD_NOT_FOUND.getCode(), new String[]{Medication.class.getSimpleName()}, locale),
                                HttpStatus.NOT_FOUND.value())
                        );
                moreMedication.add(medication);
            }

            droneWithId.get().setMedication(moreMedication);
            droneWithId.get().setState(State.LOADED);
            dispatchRepository.save(droneWithId.get());
            logger.info(">>> Drone with Serial Number::{}", droneWithId);
           CreateLoadMedicationResponse createLoadMedicationResponse=new CreateLoadMedicationResponse();
            createLoadMedicationResponse.setSerialNumber(droneWithId.get().getSerialNumber());
            createLoadMedicationResponse.setMedication(droneWithId.get().getMedication());
            createLoadMedicationResponse.setDateCreated(LocalDateTime.now().toString());
            createLoadMedicationResponse.setId(droneWithId.get().getId());
            createLoadMedicationResponse.setState(State.LOADED);

        return new ApiResponse
                .ApiResponseBuilder<>(messageService.getMessage(I18Code.MESSAGE_RECORD_LOADED_SUCCESSFULLY.getCode(), new String[]{Drone.class.getSimpleName()}, locale))
                .setData(createLoadMedicationResponse)
                .build();
    }


    @Override
    public ApiResponse<?> findLoadedDroneBySerialNumber(String serialNumber, Locale locale) throws ServiceException {
        Assert.notNull(serialNumber, "Serial Number can not be null");
        Drone drone = dispatchRepository.findDroneBySerialNumber(serialNumber).
                orElseThrow(() -> new ServiceException(
                        messageService.getMessage(I18Code.MESSAGE_RECORD_NOT_FOUND.getCode(), new String[]{Drone.class.getSimpleName()}, locale),
                        HttpStatus.NOT_FOUND.value())
                );
        if(drone.getState()==State.IDLE){
            throw new ServiceException(
                    messageService.getMessage(I18Code.MESSAGE_RECORD_NOT_LOADED.getCode(), new String[]{Drone.class.getSimpleName()}, locale),
                    HttpStatus.CONFLICT.value(
                    ));
        }

        return new ApiResponse
                .ApiResponseBuilder<>(messageService.getMessage(I18Code.MESSAGE_RECORDS_FETCHED_SUCCESSFULLY.getCode(), new String[]{"Loaded medication"}, locale))
                .setData(mapper.map(drone, CreateLoadedDroneResponse.class))
                .build();
    }

    @Override
    public List<DroneResponse> findAvailableDrones() {
        List<Drone> drones = dispatchRepository.findDroneByState(State.IDLE);
        List<DroneResponse> droneResponses = new ArrayList<>();
        for (Drone drone : drones) {
            DroneResponse spResponse = mapper.map(drone, DroneResponse.class);
            droneResponses.add(spResponse);
        }
        return droneResponses;
    }

    @Override
    public ApiResponse<?> findDroneBatteryLevel(String serialNumber, Locale locale) throws ServiceException {
        Assert.notNull(serialNumber, "Serial Number can not be null");
        Drone drone = dispatchRepository.findDroneBySerialNumber(serialNumber).
                orElseThrow(() -> new ServiceException(
                        messageService.getMessage(I18Code.MESSAGE_RECORD_NOT_FOUND.getCode(), new String[]{Drone.class.getSimpleName()}, locale),
                        HttpStatus.NOT_FOUND.value())
                );
        return new ApiResponse
                .ApiResponseBuilder<>(messageService.getMessage(I18Code.MESSAGE_RECORD_FETCHED_SUCCESSFULLY.getCode(), new String[]{"Battery Level"}, locale))
                .setData(mapper.map(drone, CreateBatteryLevelResponse.class))
                .build();
    }

}



