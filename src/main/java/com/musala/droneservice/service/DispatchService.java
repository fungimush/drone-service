package com.musala.droneservice.service;


import com.musala.droneservice.utils.exceptions.ServiceException;
import com.musala.droneservice.utils.request.CreateDroneRequest;
import com.musala.droneservice.utils.request.UpdateDroneRequest;
import com.musala.droneservice.utils.response.ApiResponse;
import com.musala.droneservice.utils.response.DroneResponse;

import java.util.List;
import java.util.Locale;

public interface DispatchService {

    ApiResponse<?> create(CreateDroneRequest createDroneRequest, Locale locale) throws Exception;

    ApiResponse<?> edit(Long id, UpdateDroneRequest updateDroneRequest, Locale locale) throws ServiceException;

    List<DroneResponse> getAllDrones();

}
