package com.example.avanade_project.dtos;

import com.example.avanade_project.domain.model.Account;
import com.example.avanade_project.domain.model.Card;
import com.example.avanade_project.domain.model.Feature;
import com.example.avanade_project.domain.model.News;

import java.util.List;

public record UserDTO(String name, Account account, Card card, List<Feature> features, List<News> news) {
}
