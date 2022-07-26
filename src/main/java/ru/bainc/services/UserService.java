package ru.bainc.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.bainc.dto.AuthDto;
import ru.bainc.dto.UserDto;
import ru.bainc.model.Role;
import ru.bainc.model.Status;
import ru.bainc.model.User;
import ru.bainc.repositories.RoleRepository;
import ru.bainc.repositories.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(Long id){
        return userRepository.getById(id);
    }

    public User getByUserName(String userName){
        User user = userRepository.findByUserName(userName);

        log.info ("User with username {} found", userName);
        return  user;
    }

    public List<UserDto> getAllToFront(){
        return  getAll().stream().map(user -> new UserDto(user)).collect(Collectors.toList());
    }

    @Transactional
    public User addUser(AuthDto authDto){
        User user = new User();
        user.setUserName(authDto.getUsername());
        user.setFirstName("test");
        user.setLastName("test");
        user.setStatus(Status.ACTIVE);
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        user.setEmail("test");
        user.setPassword(passwordEncoder.encode(authDto.getPassword()));
        user.setCreated(LocalDateTime.now());
        user.setUpdated(LocalDateTime.now());

        return userRepository.save(user);
    }

}
