package it.card.service;

import it.card.dto.CardDto;

public interface CardService {
    void addCredit(Integer number,float amount);

	void removeCredit(Integer number,float amount);

    CardDto findCardByNumber(Integer number);

    void createCard(Integer number, float amount);

    void blockCard(Integer number);

    void unlockCard(Integer number);

}