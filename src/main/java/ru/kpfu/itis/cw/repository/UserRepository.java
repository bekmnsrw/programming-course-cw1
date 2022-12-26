package ru.kpfu.itis.cw.repository;

import ru.kpfu.itis.cw.models.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findByEmail(String email);
}
