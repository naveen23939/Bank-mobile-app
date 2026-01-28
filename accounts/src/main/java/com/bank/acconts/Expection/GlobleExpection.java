package com.bank.acconts.Expection;

import com.bank.acconts.dto.CustomerDto;
import com.bank.acconts.dto.ErrorResponceDto;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import java.util.HashMap;
import java.util.List;



@ControllerAdvice
public class GlobleExpection extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach(error -> {
            if (error instanceof FieldError fieldError) {
                validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
            } else {
                validationErrors.put(error.getObjectName(), error.getDefaultMessage());
            }
        });

        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }




    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponceDto> handleGlobalException(Exception exception,
                                                                  WebRequest webRequest) {
        ErrorResponceDto errorResponseDTO = new ErrorResponceDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(ResouseNotFoundException.class)
    public ResponseEntity<ErrorResponceDto> handleResouseNotFoundException(ResouseNotFoundException exception ,
                                                                      WebRequest webRequest) {
        ErrorResponceDto errorResponceDto= new ErrorResponceDto(
            webRequest.getDescription(false),
        HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponceDto,HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(CustomerAlredyExitException.class)
    public ResponseEntity<ErrorResponceDto> handleAlredyExitException(CustomerAlredyExitException exception ,
                                                                      WebRequest webRequest) {
        ErrorResponceDto errorResponceDto= new ErrorResponceDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponceDto,HttpStatus.BAD_REQUEST);
    }
}
