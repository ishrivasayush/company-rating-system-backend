package com.narainox.jobApplication.exception;

import com.narainox.jobApplication.utils.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<APIResponse> recordNotFoundExceptionHandler(RecordNotFoundException recordNotFoundException)
    {
        APIResponse apiResponse=new APIResponse();
        apiResponse.setData(null);
        apiResponse.setMessage(recordNotFoundException.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

}
