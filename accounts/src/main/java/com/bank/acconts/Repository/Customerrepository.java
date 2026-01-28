package com.bank.acconts.Repository;

import com.bank.acconts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface Customerrepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByMobileNumber(String mobileNumber);

    Optional<Customer> findByName(String name);
    List<Customer> findAllByName(String name);
}
