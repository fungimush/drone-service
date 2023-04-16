package com.musala.droneservice.service;

import com.musala.droneservice.api.DispatchController;
import com.musala.droneservice.domain.Drone;
import com.musala.droneservice.repository.DispatchRepository;
import com.musala.droneservice.utils.enums.I18Code;
import com.musala.droneservice.utils.exceptions.ServiceException;
import com.musala.droneservice.utils.i18.MessageService;
import com.musala.droneservice.utils.request.CreateDroneRequest;
import com.musala.droneservice.utils.request.UpdateDroneRequest;
import com.musala.droneservice.utils.response.ApiResponse;
import com.musala.droneservice.utils.response.CreateDroneResponse;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.Locale;
import java.util.Optional;
@Service
public class DispatchServiceImpl implements DispatchService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DozerBeanMapper mapper;
    private final DispatchRepository dispatchRepository;
    private final MessageService messageService;

    public DispatchServiceImpl(DozerBeanMapper mapper, DispatchRepository dispatchRepository, MessageService messageService) {
        this.mapper = mapper;
        this.dispatchRepository = dispatchRepository;
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
        return null;
    }
}
