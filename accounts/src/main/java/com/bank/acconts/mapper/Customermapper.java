package com.bank.acconts.mapper;

import com.bank.acconts.dto.CustomerDto;
import com.bank.acconts.entity.Customer;

public class Customermapper {

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

    public static void updateCustomerFromDto(CustomerDto dto, Customer entity) {

        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }

        if (dto.getEmail() != null) {
            entity.setEmail(dto.getEmail());
        }

        if (dto.getMobileNumber() != null) {
            entity.setMobileNumber(dto.getMobileNumber());
        }
    }

}
