package com.bank.acconts.service.impl;

import com.bank.acconts.Expection.CustomerAlredyExitException;
import com.bank.acconts.Expection.ResouseNotFoundException;
import com.bank.acconts.Repository.Accountrepository;
import com.bank.acconts.Repository.Customerrepository;
import com.bank.acconts.constents.AccountsConstants;
import com.bank.acconts.dto.AccountDto;
import com.bank.acconts.dto.CustomerDto;
import com.bank.acconts.entity.Account;
import com.bank.acconts.entity.Customer;
import com.bank.acconts.mapper.Accountmapper;
import com.bank.acconts.mapper.Customermapper;
import com.bank.acconts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {


    private Accountrepository accountrepository;
    private Customerrepository customerrepository;

    @Override
    public void creteaccount(CustomerDto customerDto) {

        Customer customer = Customermapper.mapToCustomer(customerDto , new Customer());
        Optional<Customer> optionalCustomer = customerrepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlredyExitException("Customer Already Registered with the same mobile number"+customerDto.getMobileNumber());
        }
        Customer saveaccount = customerrepository.save(customer);
        accountrepository.save(createnewAccount(saveaccount));
    }


    private Account createnewAccount(Customer customer) {
        Account newAccount=new Account();
        newAccount.setCoustomerid(customer.getCoustomerid());
        long rendomAccountNumber= 1000000000L+ new Random().nextInt(900000000);

        newAccount.setAccountNumber(rendomAccountNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer =  customerrepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResouseNotFoundException("customer", "mobileNumber" ,mobileNumber)
        );

            Account account  =  accountrepository.findByCoustomerid(customer.getCoustomerid()).orElseThrow(
                    () -> new ResouseNotFoundException("Account", "customerid" ,String.valueOf( customer.getCoustomerid()))
            );
        CustomerDto customerDto = Customermapper.mapToCustomerDto(customer,new CustomerDto());
        customerDto.setAccountDto(Accountmapper.mapToAccountDto(account,new AccountDto()));

        return customerDto;
    }

    @Override
    public CustomerDto fetchAccountByName(String name) {

        Customer customer = customerrepository.findByName(name).orElseThrow(
                () -> new ResouseNotFoundException("Customer", "name", name)
        );

        Account account = accountrepository.findByCoustomerid(customer.getCoustomerid()).orElseThrow(
                () -> new ResouseNotFoundException("Account", "customerid",
                        String.valueOf(customer.getCoustomerid()))
        );

        CustomerDto customerDto = Customermapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountDto(Accountmapper.mapToAccountDto(account, new AccountDto()));

        return customerDto;
    }

    @Override
    public boolean updatedAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountDto accountsDto = customerDto.getAccountDto();

        if (accountsDto != null) {
            // Use findById() to get Optional<Account>
            Account account = accountrepository.findById(accountsDto.getAccountNumber())
                    .orElseThrow(() -> new ResouseNotFoundException(
                            "Account", "accountNumber", String.valueOf(accountsDto.getAccountNumber())
                    ));
            Accountmapper.updateAccountFromDto(accountsDto, account);


            accountrepository.save(account);

            long CustomerId = account.getCoustomerid();
            Customer customer = customerrepository.findById(CustomerId).orElseThrow(
                    () -> new ResouseNotFoundException("Customer", "customerid", String.valueOf(CustomerId))
            );
            Customermapper.updateCustomerFromDto(customerDto, customer);


            customerrepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerrepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResouseNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountrepository.deleteByCoustomerid(customer.getCoustomerid());
        customerrepository.delete(customer);
        return true;

    }
}


