package com.bank.acconts.controller;
import com.bank.acconts.constents.AccountsConstants;
import com.bank.acconts.dto.AccountContactInfoDto;
import com.bank.acconts.dto.CustomerDto;
import com.bank.acconts.dto.ResponceDto;
import com.bank.acconts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;


@RestController
@RequestMapping(path = "/api",produces = (MediaType.APPLICATION_JSON_VALUE))
@Validated
public class Accountcontroller {

    private final IAccountService iAccountService;


    public Accountcontroller(IAccountService iAccountService){
    this.iAccountService = iAccountService;
}

    private String buildversion;

    @Autowired
    private Environment environment;


    @Autowired
    private AccountContactInfoDto contactInfoDto;



    @PostMapping("/create")
    public ResponseEntity<ResponceDto> createaccount(@Valid@RequestBody CustomerDto customerDto) {
        iAccountService.creteaccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponceDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }
    @GetMapping("/fetch")
    public  ResponseEntity<CustomerDto> fetchaccountDetails(@RequestParam
                                                                @Pattern(regexp = "(^$|\\d{10})",
                                                                        message = "Your mobile number must be 10 digits")
                                                                String mobileNumber) {
         CustomerDto customerDto = iAccountService.fetchAccount(mobileNumber);
         return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @GetMapping("/fetchByName")
    public ResponseEntity<CustomerDto> fetchAccountDetailsByName(@RequestParam String name) {
        CustomerDto customerDto = iAccountService.fetchAccountByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }
    @PutMapping("/update")
    public ResponseEntity<ResponceDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountService.updatedAccount(customerDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponceDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponceDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponceDto> deleteAccount(@RequestParam
                                                         @Pattern(regexp = "(^$|\\d{10})",
                                                                 message = "Your mobile number must be 10 digits")
                                                         String mobileNumber) {
        boolean isDeleted = iAccountService.deleteAccount(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponceDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponceDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }
    }

    @GetMapping("/build-info")
    public ResponseEntity<String> getbuild(){
        return ResponseEntity.
                status(HttpStatus.OK).
                body(buildversion);
    }

    @GetMapping("/java-version")
    public ResponseEntity<String> getjava(){
        return ResponseEntity.
                status(HttpStatus.OK).
                body(environment.getProperty("JAVA_HOME"));
    }


    @GetMapping("/contact-info")
    public ResponseEntity<AccountContactInfoDto> getcontactinfo(){
        return ResponseEntity.
                status(HttpStatus.OK).
                body(contactInfoDto);
    }
}


