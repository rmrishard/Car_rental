package com.rental.Car.services;


import com.rental.Car.model.User;
import com.rental.Car.repository.UserJPARepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UserJPARepository repository;

    public UserService(UserJPARepository repository) {
        this.repository = repository;
    }

    public List<User> findAllUsers() {
        return repository.findAll();
    }

    public Optional<User> findUserByUserId(Long user_id) {
        return repository.findById(user_id);
    }


    public boolean deleteUserByUserId(Long user_id) {
        if (repository.existsById(user_id)) {
            repository.deleteById(user_id);
            return true;
        }
        ;
        return false;
    }

    public void addUser(@NonNull String user_name,
                        String first_name,
                        String last_name,
                        String email,
                        String password,
                        Timestamp created_at)            {
        repository.save(new User(user_name, first_name, last_name, email, password, created_at));
    }


    public void updateUser(Long user_id, @NonNull String user_name, String first_name, String last_name,
                           String email, String password, Timestamp created_at) {
        User user = repository.findById(user_id).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        user.setUserName(user_name);
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setEmail(email);
        user.setPassword(password);
        user.setCreated_at(created_at);
        repository.save(user);


    }
}