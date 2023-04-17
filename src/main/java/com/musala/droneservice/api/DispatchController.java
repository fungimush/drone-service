package com.musala.droneservice.api;


import com.musala.droneservice.service.DispatchService;
import com.musala.droneservice.utils.Constants.Constants;
import com.musala.droneservice.utils.exceptions.ServiceException;
import com.musala.droneservice.utils.request.CreateDroneRequest;
import com.musala.droneservice.utils.request.UpdateDroneRequest;
import com.musala.droneservice.utils.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping("/api/drone")
public class DispatchController {

    private static final Logger log = LoggerFactory.getLogger(DispatchController.class);

    private final DispatchService dispatchService;

    @Autowired
    public DispatchController(DispatchService dispatchService) {
        this.dispatchService = dispatchService;
    }

    @PostMapping(path = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResponse<?>> create(@Valid @RequestBody final CreateDroneRequest createDroneRequest,
                                                 @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                         defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) throws Exception {
        log.info(">>> request to register a drone {}", createDroneRequest.toString());
        return new ResponseEntity<>(dispatchService.create(createDroneRequest, locale), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResponse<?>> edit(@PathVariable(name = "id") final Long id,
                                               @RequestBody final UpdateDroneRequest updateDroneRequest,
                                               @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                       defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) throws ServiceException {
        log.info(">>> request to update drone with id {}", id);
        return ResponseEntity.ok(dispatchService.edit(id, updateDroneRequest, locale));
    }


    @GetMapping("/allDrones")
    public ResponseEntity<DispatchApiResponse<?>> getAllDrones() {
        log.info(">>> request to fetch all Drones");
        DispatchApiResponse<?> response= DispatchApiResponse.builder().statusCode(HttpStatus.OK.toString()).message("Drones fetched successfully").success(true).data(dispatchService.getAllDrones()).build();
        return ResponseEntity.ok(response);
    }


}
