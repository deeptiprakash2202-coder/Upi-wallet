package com.deep.fintechbackend.dto;

public record RegisterRequest(
        String name,
        String email,
        String password
) {}