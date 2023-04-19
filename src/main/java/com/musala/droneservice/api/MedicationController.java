package com.musala.droneservice.api;

import com.musala.droneservice.api.dtos.MedicationDto;
import com.musala.droneservice.repository.MedicationRepository;
import com.musala.droneservice.service.MedicationService;
import com.musala.droneservice.utils.Constants.Constants;
import com.musala.droneservice.utils.exceptions.ServiceException;
import com.musala.droneservice.utils.helpers.UtilityFile;
import com.musala.droneservice.utils.request.*;
import com.musala.droneservice.utils.response.ApiResponse;
import com.musala.droneservice.utils.response.DispatchApiResponse;
import io.swagger.annotations.ApiParam;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Locale;

@RestController
@RequestMapping("/api/medication")
public class MedicationController {

    private static final Logger log = LoggerFactory.getLogger(MedicationController.class);

    private final MedicationService medicationService;

    private final DozerBeanMapper mapper;

    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationController(MedicationService medicationService, DozerBeanMapper mapper, MedicationRepository medicationRepository) {
        this.medicationService = medicationService;
        this.mapper = mapper;
        this.medicationRepository = medicationRepository;
    }

    @PostMapping(path = "/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ApiResponse<?>> create(MedicationDto medicationDto,
                                                 @RequestParam("image") MultipartFile multipartFile,
                                                 @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                         defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) throws Exception {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        medicationDto.setImageName(fileName);
        String uploadDir = "./MedicationImages";
        UtilityFile.saveFile(uploadDir, fileName, multipartFile);
        log.info(">>> request to register medication {}", medicationDto.toString());
        return new ResponseEntity<>(medicationService.create(medicationDto, locale), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResponse<?>> edit(@PathVariable(name = "id") final Long id,
                                               @RequestBody final UpdateMedicationRequest updateMedicationRequest,
                                               @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                       defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) throws ServiceException {
        log.info(">>> request to update drone with id {}", id);
        return ResponseEntity.ok(medicationService.edit(id, updateMedicationRequest, locale));
    }

    @GetMapping("/allMedications")
    public ResponseEntity<DispatchApiResponse<?>> getAllMedications() {
        log.info(">>> request to fetch all Medications");
        DispatchApiResponse<?> response = DispatchApiResponse.builder().statusCode(HttpStatus.OK.toString()).message("Medications fetched successfully").success(true).data(medicationService.getAllMedications()).build();
        return ResponseEntity.ok(response);
    }

}
