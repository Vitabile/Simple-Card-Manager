package it.card.service.impl;

import it.card.dto.CardDto;
import it.card.entity.Card;
import it.card.repository.CardRepository;
import it.card.service.CardService;

import org.springframework.stereotype.Service;


@Service
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CardDto findCardByNumber(Integer number){
        Card card = cardRepository.findByNumber(number);
        if(card != null){
            return card.toDto();
        }
        else
            return null;
    }

    public void addCredit(Integer number, float amount){
        Card card = cardRepository.findByNumber(number);
        card.setCredit(card.getCredit()+amount);
        cardRepository.save(card);
    }

    public void removeCredit(Integer number, float amount){
        Card card = cardRepository.findByNumber(number);
        card.setCredit(card.getCredit()-amount);
        cardRepository.save(card);
    }

    public void createCard(Integer number, float amount){
        Card card = new Card();
        card.setNumber(number);
        card.setCredit(amount);
        card.setActivated(true);
        cardRepository.save(card);
    }

    public void blockCard(Integer number){
        Card card = cardRepository.findByNumber(number);
        card.setActivated(false);
        cardRepository.save(card);
    }

    public void unlockCard(Integer number){
        Card card = cardRepository.findByNumber(number);
        card.setActivated(true);
        cardRepository.save(card);
    }

    
}