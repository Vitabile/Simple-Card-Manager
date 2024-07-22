package it.card.service.impl;

import it.card.dto.TransactionDto;
import it.card.entity.User;
import it.card.entity.Card;
import it.card.entity.Transaction;
import it.card.repository.CardRepository;
import it.card.repository.TransactionRepository;
import it.card.repository.UserRepository;
import it.card.service.TransactionService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private CardRepository cardRepository;
    private UserRepository userRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                CardRepository cardRepository,
                                UserRepository userRepository)
    {
        this.transactionRepository = transactionRepository;
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    public void saveTransaction(TransactionDto transactionDto){

        Card card = cardRepository.findByNumber(transactionDto.getCardNumber());
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTimestamp(transactionDto.getTimestamp());
        transaction.setCard(card);
        transaction.setUser(user);
        transactionRepository.save(transaction);
    }

    @Override
    public List<TransactionDto> findAllTransaction(User user) {
        List<Transaction> transactions = transactionRepository.findByUser(user);
        return transactions.stream()
                .map((transaction) -> transaction.toDto())
                .collect(Collectors.toList());
    }

}