package com.bank.acconts.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor
public class Account extends BaseEntity{


    @Column(name = "customer_id")
    private long coustomerid;

    @Column(name = "account_number")
    @Id
    private long AccountNumber;

    @Column(name = "account_type")
    private String AccountType;

    @Column(name = "branch_address")
    private String BranchAddress;

}
