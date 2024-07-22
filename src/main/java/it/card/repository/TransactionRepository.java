package it.card.repository;

import it.card.entity.Transaction;
import it.card.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	public List<Transaction> findByUser(User user);


}