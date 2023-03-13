package com.ey.cl.springboot.rest.usercreation.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.cl.springboot.rest.usercreation.dtos.PhoneRequestDTO;
import com.ey.cl.springboot.rest.usercreation.dtos.UserRequestDTO;
import com.ey.cl.springboot.rest.usercreation.entities.UserInfo;
import com.ey.cl.springboot.rest.usercreation.entities.UserPhone;
import com.ey.cl.springboot.rest.usercreation.repositories.UserInfoRepository;
import com.ey.cl.springboot.rest.usercreation.services.UserInfoService;

@Service
public class UserInfoServiceImpl
        implements UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

    public List<UserInfo> getAll() {
        List<UserInfo> users = new ArrayList<>();
        userInfoRepository.findAll().forEach(user -> users.add(user));
        return users;
    }

    public UserInfo create(UserRequestDTO userDTO) {
        UserInfo user = UserInfo.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
        for (PhoneRequestDTO phone : userDTO.getPhones()) {
            user.getPhones().add(UserPhone.builder()
                    .number(phone.getNumber())
                    .citycode(phone.getCitycode())
                    .contrycode(phone.getContrycode())
                    .build());
        }
        return userInfoRepository.save(user);
    }

}
