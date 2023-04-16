package com.musala.droneservice.api;


import com.musala.droneservice.service.DispatchService;
import com.musala.droneservice.utils.Constants.Constants;
import com.musala.droneservice.utils.request.CreateDroneRequest;
import com.musala.droneservice.utils.response.ApiResponse;
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
@RequestMapping("/api/drone/register")
public class DispatchController {

    private static final Logger log = LoggerFactory.getLogger(DispatchController.class);

    private final DispatchService dispatchService;

    @Autowired
    public DispatchController(DispatchService dispatchService) {
        this.dispatchService = dispatchService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResponse<?>> create(@Valid @RequestBody final CreateDroneRequest createDroneRequest,
                                                 @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                         defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) throws Exception {
        log.info(">>> request to register a drone {}", createDroneRequest.toString());
        return new ResponseEntity<>(dispatchService.create(createDroneRequest, locale), HttpStatus.CREATED);
    }



}
