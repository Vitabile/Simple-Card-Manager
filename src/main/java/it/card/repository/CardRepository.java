package it.card.repository;

import it.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

	public Card findByNumber(int number);


}