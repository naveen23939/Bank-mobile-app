package com.bank.acconts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NonNull;

@Data

public class AccountDto {


    @Pattern(regexp = "(^$|\\d{10})", message = "Your account number must be 10 digits")
    private long AccountNumber;


    private String AccountType;


    private String BranchAddress;
}
