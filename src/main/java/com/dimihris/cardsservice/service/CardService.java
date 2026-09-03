package com.dimihris.cardsservice.service;

import com.dimihris.cardsservice.dto.CardDto;

public interface CardService {

    void createCard(String mobileNumber);

    CardDto getCardDetails(String mobileNumber);

}
