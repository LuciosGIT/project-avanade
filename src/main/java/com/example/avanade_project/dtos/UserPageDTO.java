package com.example.avanade_project.dtos;

import java.util.List;

public record UserPageDTO(List<UserDTO> users, long totalElements, int totalPages) {
}
