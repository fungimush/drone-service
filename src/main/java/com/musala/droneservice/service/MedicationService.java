package com.musala.droneservice.service;

import com.musala.droneservice.api.dtos.MedicationDto;
import com.musala.droneservice.utils.exceptions.ServiceException;
import com.musala.droneservice.utils.request.*;
import com.musala.droneservice.utils.response.ApiResponse;
import com.musala.droneservice.utils.response.MedicationResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Locale;

public interface MedicationService {

    ApiResponse<?> create(MedicationDto medicationDto, Locale locale) throws Exception;

    ApiResponse<?> edit(Long id, UpdateMedicationRequest updateMedicationRequest, Locale locale) throws ServiceException;

    List<MedicationResponse> getAllMedications();

}
