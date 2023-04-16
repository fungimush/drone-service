package com.musala.droneservice.api;

import com.musala.droneservice.utils.exceptions.ServiceException;
import com.musala.droneservice.utils.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdvice.class);

    @ResponseBody
    @ExceptionHandler(value = {ServiceException.class})
    public ResponseEntity<?> handleServiceException(ServiceException e) {
        LOGGER.info("service exception thrown ---> ", e);
        return new ResponseEntity<>(new ApiResponse.ApiResponseBuilder<>(e.getMessage()).build(), HttpStatus.valueOf(e.getStatusCode()));
    }

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handleException(Exception e) {
        LOGGER.info("exception thrown ---> ", e);
        return new ResponseEntity<>(new ApiResponse.ApiResponseBuilder<>("Something went wrong").build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        LOGGER.info("httpRequestMethodNotSupportedException thrown ---> ", e);
        return new ResponseEntity<>(new ApiResponse.ApiResponseBuilder<>(e.getLocalizedMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        LOGGER.info("argumentNotValidException thrown ---> ", e);
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();

        StringBuilder errorMessage = new StringBuilder("");

        for (ObjectError error : allErrors) {
            errorMessage.append(error.getDefaultMessage()).append(";");
        }
        return new ResponseEntity<>(new ApiResponse.ApiResponseBuilder<>(errorMessage.toString()).build(), HttpStatus.BAD_REQUEST);
    }
}
