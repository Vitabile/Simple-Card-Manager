package it.card.controller;

import it.card.dto.CardDto;
import it.card.dto.TransactionDto;
import it.card.entity.User;
import it.card.service.CardService;
import it.card.service.TransactionService;
import it.card.service.UserService;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TransactionController {

	private TransactionService transactionService;
	private UserService userService;
    private CardService cardService;

    public TransactionController(TransactionService transactionService,
                                UserService userService,
                                CardService cardService)
    {
        this.transactionService = transactionService;
		this.userService = userService;
        this.cardService = cardService;
    }

    // handler method to handle the transaction card page request
    @GetMapping("/transaction")
    public String transaction(Model model){
        TransactionDto transaction = new TransactionDto();
        model.addAttribute("transaction", transaction);
        return "transaction";
    }

    // handler method to handle the transaction card submited request
	@PostMapping("/transaction")
    public String transaction(@RequestParam("action") String action,
                              @Valid @ModelAttribute("transaction") TransactionDto transaction,
                              Model model)
    {

		CardDto card = cardService.findCardByNumber(transaction.getCardNumber());
		

		if(card != null){
			Boolean activated = card.getActivated();
			float credit = card.getCredit();
			if(activated){
                transaction.setTimestamp(LocalDateTime.now());
				if(action.equals("add")){
					cardService.addCredit(card.getNumber(), transaction.getAmount());
                    transactionService.saveTransaction(transaction);
					model.addAttribute("success", true);
				}
				else if(action.equals("remove") && credit >= transaction.getAmount()){
					cardService.removeCredit(card.getNumber(), transaction.getAmount());
                    transaction.setAmount(-transaction.getAmount());
                    transactionService.saveTransaction(transaction);
					model.addAttribute("success", true);
				}
				else 
					model.addAttribute("errCredit",true);
				
			}
			else
				model.addAttribute("errState",true);
		} 
		else
			model.addAttribute("errNumber", true);
		
        transaction = new TransactionDto();
        model.addAttribute("transaction", transaction);
		return "transaction";
	}

    // handler to handle the report transactions request 
	@GetMapping("/transaction/report")
    public String transactions(Model model){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findUserByUsername(username);
        List<TransactionDto> transactions = transactionService.findAllTransaction(user);
        model.addAttribute("transactions", transactions);
        return "transactions-view";
    }

}