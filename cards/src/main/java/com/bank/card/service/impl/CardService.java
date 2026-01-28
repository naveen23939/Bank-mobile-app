package com.bank.card.service.impl;

import com.bank.card.constant.CardsConstants;
import com.bank.card.dto.CardDto;
import com.bank.card.entity.Card;
import com.bank.card.exception.CardAlredyExitException;
import com.bank.card.exception.ResourceNotFoundException;
import com.bank.card.mapper.cardMapper;
import com.bank.card.repository.CardRepository;
import com.bank.card.service.ICardService;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardService implements ICardService {


    CardRepository cardRepository;

    @Override
    public void createcard(String mobileNumber) {
        Optional<Card> optioncard = cardRepository.findBymobileNumber(mobileNumber);
        if (optioncard.isPresent()) {
            throw new CardAlredyExitException("Card already registered with given mobileNumber " + mobileNumber);
        }
        cardRepository.save(createNewcard(mobileNumber));
    }

    private Card createNewcard(String mobileNumber) {

        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotelLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvalableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }
    @Override
    public CardDto fetchCard(String mobileNumber) {

        Card cards = cardRepository.findBymobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return cardMapper.maptocardDto(cards, new CardDto());
    }

    @Override
    public boolean updateCard(CardDto cardsDto) {
        Card cards = cardRepository.findBycardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
        cardMapper.maptoCard(cardsDto, cards);
        cardRepository.save(cards);
        return  true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Card cards = cardRepository.findBymobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardRepository.deleteById(cards.getCardid());
        return true;
    }
    }





