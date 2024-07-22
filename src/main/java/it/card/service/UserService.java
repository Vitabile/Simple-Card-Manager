package it.card.service;

import it.card.dto.UserDto;
import it.card.entity.User;


public interface UserService {

    void disableUser(String username);

    void activeUser(String username);

    void saveUser(UserDto userDto);

    User findUserByUsername(String username);

}