package ru.kpfu.itis.cw.services;

public interface HashService {
    String hashPassword(String password);
    Boolean matches(String inputPassword, String databasePassword);
}
