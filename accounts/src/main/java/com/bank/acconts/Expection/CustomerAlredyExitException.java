package com.bank.acconts.Expection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlredyExitException extends RuntimeException
{
    public CustomerAlredyExitException(String message)
    {
        super(message);
    }
}
