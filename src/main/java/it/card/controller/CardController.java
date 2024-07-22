package it.card.controller;

import it.card.dto.CardDto;
import it.card.service.CardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CardController {

	private CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    // handler method to handle home page request
    @GetMapping(value={"/credit","/"})
    public String credit(){
        return "credit";
    }

	// handler method to handle result of credit form submited request
    @PostMapping("/credit")
    public String resultCredit(String number, Model model){
		CardDto card = cardService.findCardByNumber(Integer.parseInt(number));
		
		if (card != null){
			model.addAttribute("number", card.getNumber());
			model.addAttribute("credit", card.getCredit());
			model.addAttribute("view", true);
		}
		else{
			model.addAttribute("error", true);
		}

		return "credit";
	}



	// handler method to handle the creation card page request
    @GetMapping("/create")
    public String create(){
        return "create";
    }

	// handler method to handle result of creation card submited request
    @PostMapping("/create")
    public String create(Integer number, float amount, Model model){
		CardDto card = cardService.findCardByNumber(number);
		
		if (card == null){
			cardService.createCard(number, amount);
			model.addAttribute("success", true);
		}
		else{
			model.addAttribute("error", true);
		}

		return "create";
	}

	// handler method to handle the management card page request
    @GetMapping("/card/manage")
    public String manageCard(){
        return "manage-card";
    }


	// handler method to handle the management card submited request
	@PostMapping("/card/manage")
    public String manageCard(@RequestParam("action") String action, Integer number, Model model){

		CardDto card = cardService.findCardByNumber(number);

		if(card != null && action.equals("unlock")){
			if(!card.getActivated()){
				cardService.unlockCard(card.getNumber());
				model.addAttribute("success", true);
			}
			else
				model.addAttribute("errUnlock", true);
			
		}
		
		else if(card != null && action.equals("block")){
			if(card.getActivated()){
				cardService.blockCard(card.getNumber());
				model.addAttribute("success", true);
			}
			else
				model.addAttribute("errBlock", true);
				
		}
		else{
			model.addAttribute("errNumber", true);
		}

		return "manage-card";
	}

	

}