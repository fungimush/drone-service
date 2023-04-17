package com.musala.droneservice.service;

import com.musala.droneservice.utils.exceptions.ServiceException;
import com.musala.droneservice.utils.request.*;
import com.musala.droneservice.utils.response.ApiResponse;
import com.musala.droneservice.utils.response.MedicationResponse;

import java.util.List;
import java.util.Locale;

public interface MedicationService {

    ApiResponse<?> create(CreateMedicationRequest createMedicationRequest, Locale locale) throws Exception;

    ApiResponse<?> edit(Long id, UpdateMedicationRequest updateMedicationRequest, Locale locale) throws ServiceException;

    List<MedicationResponse> getAllMedications();


}
