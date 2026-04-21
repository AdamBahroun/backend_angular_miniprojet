package com.adam.voiture.security;

public interface SecParams {
    long EXP_TIME = 10L * 24 * 60 * 60 * 1000;
    String SECRET = "voiture-secret-adam-2024";
    String PREFIX = "Bearer ";
}