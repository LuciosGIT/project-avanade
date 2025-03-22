package com.example.avanade_project.converter;

import com.example.avanade_project.domain.model.User;
import com.example.avanade_project.dtos.UserDTO;

public class UserConverter {

    public static User ConvertUserDTOtoUser(UserDTO userDto) {
        User user = new User();

        user.setAccount(userDto.account());
        user.setNews(userDto.news());
        user.setName(userDto.name());
        user.setCard(userDto.card());
        user.setFeatures(userDto.features());

        return user;
    }

}
