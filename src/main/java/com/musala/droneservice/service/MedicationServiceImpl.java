package com.musala.droneservice.service;

import com.musala.droneservice.domain.Medication;
import com.musala.droneservice.repository.MedicationRepository;
import com.musala.droneservice.utils.enums.I18Code;
import com.musala.droneservice.utils.exceptions.ServiceException;
import com.musala.droneservice.utils.i18.MessageService;
import com.musala.droneservice.utils.request.CreateMedicationRequest;
import com.musala.droneservice.utils.request.UpdateMedicationRequest;
import com.musala.droneservice.utils.response.*;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class MedicationServiceImpl implements MedicationService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DozerBeanMapper mapper;
    private final MedicationRepository medicationRepository;
    private final MessageService messageService;


    public MedicationServiceImpl(DozerBeanMapper mapper, MedicationRepository medicationRepository, MessageService messageService) {
        this.mapper = mapper;
        this.medicationRepository = medicationRepository;
        this.messageService = messageService;
    }

    @Override
    public ApiResponse<?> create(CreateMedicationRequest createMedicationRequest, Locale locale) throws Exception {

        Optional<Medication> medicationWithCode = medicationRepository.findMedicationByCode(createMedicationRequest.getCode());
            if (medicationWithCode.isPresent())
                throw new ServiceException(
                        messageService.getMessage(I18Code.MESSAGE_RECORD_ALREADY_EXIST.getCode(), new String[]{Medication.class.getSimpleName()}, locale),
                        HttpStatus.CONFLICT.value()
                );

            Medication newMedication = mapper.map(createMedicationRequest, Medication.class);
            Medication savedMedication = medicationRepository.save(newMedication);
            logger.info(">>> Medication with code::{} registered successfully", savedMedication.getCode());
            return new ApiResponse
                    .ApiResponseBuilder<>(messageService.getMessage(I18Code.MESSAGE_RECORD_CREATED_SUCCESSFULLY.getCode(), new String[]{Medication.class.getSimpleName()}, locale))
                    .setData(mapper.map(savedMedication, CreateMedicationResponse.class))
                    .build();

        }


        @Override
    public ApiResponse<?> edit(Long id, UpdateMedicationRequest updateMedicationRequest, Locale locale) throws ServiceException {
            Medication medication = medicationRepository.findById(id).
                    orElseThrow(() -> new ServiceException(
                            messageService.getMessage(I18Code.MESSAGE_RECORD_NOT_FOUND.getCode(), new String[]{Medication.class.getSimpleName()}, locale),
                            HttpStatus.NOT_FOUND.value())
                    );

            if(updateMedicationRequest.getCode()!=null) {
                medication.setCode(updateMedicationRequest.getCode());
            }

            if(updateMedicationRequest.getName()!=null) {
                medication.setName(updateMedicationRequest.getName());
            }

            if(updateMedicationRequest.getWeight()!=0) {
                medication.setWeight(updateMedicationRequest.getWeight());
            }

            medication.setDateLastUpdated(LocalDateTime.now().toString());

            logger.info(">>> Medication info::{} ", medication);


            Medication savedMedication = medicationRepository.save(medication);
            return new ApiResponse
                    .ApiResponseBuilder<>(messageService.getMessage(I18Code.MESSAGE_RECORD_UPDATED_SUCCESSFULLY.getCode(), new String[]{Medication.class.getSimpleName()}, locale))
                    .setData(mapper.map(savedMedication, UpdateMedicationResponse.class))
                    .build();
        }

    @Override
    public List<MedicationResponse> getAllMedications() {
        List<Medication> medications = medicationRepository.findAll();
        List<MedicationResponse> medicationResponses = new ArrayList<>();
        for (Medication medication : medications) {
            MedicationResponse spResponse = mapper.map(medication, MedicationResponse.class);
            medicationResponses.add(spResponse);
        }
        return medicationResponses;
    }

}
