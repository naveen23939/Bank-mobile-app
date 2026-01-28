package com.bank.card.service;
import com.bank.card.dto.CardDto;
import jakarta.validation.constraints.Pattern;

public interface ICardService {


    void createcard( String mobileNumber);

    CardDto fetchCard( String mobileNumber);

    boolean updateCard(CardDto cardsDto);

    boolean deleteCard( String mobileNumber);
}
