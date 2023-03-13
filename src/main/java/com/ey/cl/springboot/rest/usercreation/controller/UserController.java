package com.ey.cl.springboot.rest.usercreation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.cl.springboot.rest.usercreation.dtos.UserRequestDTO;
import com.ey.cl.springboot.rest.usercreation.entities.UserInfo;
import com.ey.cl.springboot.rest.usercreation.exceptions.UserEmailExistException;
import com.ey.cl.springboot.rest.usercreation.services.UserInfoService;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    UserInfoService userInfoService;

    @GetMapping
    private ResponseEntity<List<UserInfo>> getAllUsers() {
        return ResponseEntity.ok().body(userInfoService.getAll());
    }

    @PostMapping(produces = "application/json")
    private ResponseEntity<Object> createUser(@RequestBody @Valid UserRequestDTO userDTO) {
        try {
            return new ResponseEntity<>(userInfoService.create(userDTO), HttpStatus.CREATED);
        } catch (Exception exception) {
            throw new UserEmailExistException("Correo ya registrado");
        }
    }

}
