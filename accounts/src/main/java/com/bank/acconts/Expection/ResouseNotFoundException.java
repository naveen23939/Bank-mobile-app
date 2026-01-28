package com.bank.acconts.Expection;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResouseNotFoundException extends RuntimeException
{
    public ResouseNotFoundException(String resouseName, String fieldName, String fieldValue)
    {
        super(String.format("%s not found with the given input data %s:'%s'", resouseName, fieldName , fieldValue));
    }
}
