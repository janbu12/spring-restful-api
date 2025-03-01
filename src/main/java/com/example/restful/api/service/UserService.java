package com.example.restful.api.service;

import com.example.restful.api.model.User;
import com.example.restful.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAllByStatus(1);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User registerUser(User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        user.hashPassword();
        user.setStatus(1);
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setNamalengkap(userDetails.getNamalengkap());
        user.setNotelepon(userDetails.getNotelepon());
        user.setAlamat(userDetails.getAlamat());

        return userRepository.save(user);
    }

    public Optional<User> getUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public User deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setStatus(0);
        return userRepository.save(user);
    }
}
