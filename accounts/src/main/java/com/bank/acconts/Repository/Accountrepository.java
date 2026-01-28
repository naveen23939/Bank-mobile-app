package com.bank.acconts.Repository;

import com.bank.acconts.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface Accountrepository extends JpaRepository<Account,Long> {

    Optional<Account> findByCoustomerid(Long coustomerid);

    @Transactional
    @Modifying
    void deleteByCoustomerid(Long coustomerid);
}
