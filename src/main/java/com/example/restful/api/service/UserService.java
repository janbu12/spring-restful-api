package com.example.restful.api.service;

import com.example.restful.api.entity.User;
import com.example.restful.api.model.RegisterUserRequest;
import com.example.restful.api.repository.UserRepository;
import com.example.restful.api.security.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    public void registerUser(RegisterUserRequest request) {
        validationService.validate(request);

        if(userRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setNamalengkap(request.getNamalengkap());
        user.setNotelepon(request.getNotelepon());
        user.setAlamat(request.getAlamat());
        user.setStatus(1);
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAllByStatus(1);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
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
