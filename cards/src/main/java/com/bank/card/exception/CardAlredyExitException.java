package com.bank.card.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CardAlredyExitException extends RuntimeException {

    public CardAlredyExitException(String message) {
        super(message);
    }
}
