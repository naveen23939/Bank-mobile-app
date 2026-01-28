package com.bank.acconts.service;

import com.bank.acconts.dto.CustomerDto;

public interface IAccountService {


    void creteaccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    CustomerDto fetchAccountByName(String name);

    boolean updatedAccount( CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
