package com.bank.card.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
name = "ErrorResponce"
)
@Data@AllArgsConstructor
public class ErrorResponceDto {

    @Schema(
description = "Api path invalide by clint"
    )
    private String apipath;

    @Schema(
            description = "Error code respesnting"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message representing the error happened"
    )

    private String errorMessage;

    @Schema(
            description = "Time representing when the error happened"
    )
    private LocalDateTime errortime;
}
