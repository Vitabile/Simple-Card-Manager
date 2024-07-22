package it.card.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import it.card.dto.UserDto;
import it.card.entity.User;
import it.card.service.UserService;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // handler method to handle home page request
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    // handler method to handle the access denied page request
    @GetMapping("/access-denied")
    public String access_denied(){
        return "access-denied";
    }

    // handler method to handle the error page request
    @GetMapping("/error")
    public String error(){
        return "error";
    }



    // handler method to handle login request
    @GetMapping("/login")
    public String showLogin(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/login/error")
    public String loginError(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errMex = null;
        if (session != null) {
            AuthenticationException authExc = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION); 
            if (authExc != null)
                errMex = authExc.getMessage();

        }
    
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        model.addAttribute("error", true);
        model.addAttribute("errMex", errMex);
        return "login";
    }


    // handler method to handle merchant registration form request
    @GetMapping("/merchant/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }


    // handler method to handle merchant registration form submit request
    @PostMapping("/merchant/register")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               Model model){

        User existingUser = userService.findUserByUsername(userDto.getUsername());

        if(existingUser != null && existingUser.getUsername() != null && !existingUser.getUsername().isEmpty()){
            return "redirect:/merchant/register?error";
        }
        
        userService.saveUser(userDto);
        return "redirect:/merchant/register?success";
            
    }

    // handler method to handle the manage merchant page request
    @GetMapping("/merchant/manage")
    public String manageMerchant(){
        return "manage-merchant";
    }

    // handler method to handle the submit request about merchant
    @PostMapping("/merchant/manage")
    public String manageMerchant(@RequestParam("action") String action, String username, Model model){
        
        User existingUser = userService.findUserByUsername(username);

        if(existingUser != null && existingUser.getUsername() != null && !existingUser.getUsername().isEmpty()){
            Boolean activated = existingUser.getActivated();
            if(action.equals("activate")){
                if(activated){
                    model.addAttribute("errAct",true);
                }
                else{
                    userService.activeUser(existingUser.getUsername());
                    model.addAttribute("activated", true);
                }
            }
            else if(action.equals("disable")){
                if(!activated){
                    model.addAttribute("errDis",true);
                }
                else{
                    userService.disableUser(existingUser.getUsername());
                    model.addAttribute("disabled", true);
                }
            }
        }
        else
            model.addAttribute("errUser",true);

        return "manage-merchant";
        
    }

}