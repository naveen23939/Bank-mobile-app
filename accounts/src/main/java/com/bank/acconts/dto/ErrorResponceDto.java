package com.bank.acconts.dto;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data@AllArgsConstructor
public class ErrorResponceDto {

    private String apiPath;
    private  HttpStatus Errorcode;
    private String errormessage;
    private LocalDateTime errortime;


}
