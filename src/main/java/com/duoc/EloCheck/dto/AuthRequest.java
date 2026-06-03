package com.duoc.EloCheck.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}