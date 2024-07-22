package it.card.service.impl;

import it.card.dto.UserDto;
import it.card.entity.Role;
import it.card.entity.User;
import it.card.repository.RoleRepository;
import it.card.repository.UserRepository;
import it.card.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setActivated(true);
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_MERCHANT");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public void disableUser(String username) {
        User user = userRepository.findByUsername(username);
        user.setActivated(false);
        userRepository.save(user);
    }

    @Override
    public void activeUser(String username) {
        User user = userRepository.findByUsername(username);
        user.setActivated(true);
        userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_MERCHANT");
        return roleRepository.save(role);
    }
}