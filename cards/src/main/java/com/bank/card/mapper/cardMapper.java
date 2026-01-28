package com.bank.card.mapper;

import com.bank.card.dto.CardDto;
import com.bank.card.entity.Card;

public class cardMapper {

    public static  CardDto   maptocardDto(Card card , CardDto carddto){
        carddto.setCardNumber( card.getCardNumber() );
        carddto.setCardType( card.getCardType() );
        carddto.setMobileNumber(card.getMobileNumber());
        carddto.setTotalLimit(carddto.getTotalLimit());
        carddto.setAvailableAmount(carddto.getAvailableAmount());
        carddto.setAmountUse(carddto.getAmountUse());

        return carddto;
    }


    public static Card  maptoCard(CardDto cardDto, Card card){
        card.setCardNumber(cardDto.getCardNumber());
        card.setCardType(cardDto.getCardType());
        card.setMobileNumber(cardDto.getMobileNumber());
        card.setTotelLimit(cardDto.getTotalLimit());
        card.setAvalableAmount(cardDto.getAvailableAmount());
        card.setAmountUsed(cardDto.getAmountUse());
        return card;
    }
}
