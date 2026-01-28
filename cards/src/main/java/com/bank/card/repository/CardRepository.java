package com.bank.card.repository;

import com.bank.card.entity.Card;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {

    Optional<Card> findBymobileNumber(String mobileNumber);

    Optional<Card> findBycardNumber(String cardNumber);

}
