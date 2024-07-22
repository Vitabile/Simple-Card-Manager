package it.card.service;

import it.card.dto.TransactionDto;
import it.card.entity.User;

import java.util.List;

public interface TransactionService {
	
	void saveTransaction(TransactionDto transactionDto);
	
	List<TransactionDto> findAllTransaction(User user);

}
