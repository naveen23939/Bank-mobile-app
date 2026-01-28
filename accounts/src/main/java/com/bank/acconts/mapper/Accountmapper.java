package com.bank.acconts.mapper;

import com.bank.acconts.dto.AccountDto;
import com.bank.acconts.entity.Account;

public class Accountmapper {

    public static AccountDto mapToAccountDto(Account account, AccountDto accountDto) {
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setBranchAddress(account.getBranchAddress());
        return accountDto;
    }

    public static Account mapToAccount(AccountDto accountDto, Account account) {
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setBranchAddress(accountDto.getBranchAddress());
        return account;
    }

    public static void updateAccountFromDto(AccountDto dto, Account entity) {

        if (dto.getAccountType() != null) {
            entity.setAccountType(dto.getAccountType());
        }

        if (dto.getBranchAddress() != null) {
            entity.setBranchAddress(dto.getBranchAddress());
        }

        // ❌ Never update accountNumber (it is PK)
    }


}
