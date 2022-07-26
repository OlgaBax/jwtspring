package ru.bainc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.bainc.dto.AuthDto;
import ru.bainc.dto.UserDto;
import ru.bainc.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {


    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers (){
        return new ResponseEntity<>(userService.getAllToFront(), HttpStatus.OK);
    }

    @PreAuthorize("ADMIN")
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody AuthDto authDto){
        if(userService.addUser(authDto) != null){
            return new ResponseEntity<>("User add", HttpStatus.OK);
        }else
            return new ResponseEntity<>("Что-то пошло не так",HttpStatus.BAD_REQUEST);
    }

}
