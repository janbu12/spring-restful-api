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

/**
 * Service class that handles business logic related to {@link User} management.
 * <p>
 * This class provides methods for user registration, data retrieval, updating,
 * and soft deletion. It acts as a bridge between the controller layer and the
 * repository layer.
 * </p>
 *
 * <p>All operations that involve validation or password encryption are
 * performed here before persisting data to the database.</p>
 *
 * @author Mizan
 * @version 1.0
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    /**
     * Registers a new user into the system.
     * <p>
     * This method validates user input, checks for duplicate usernames,
     * hashes the user's password using {@link BCrypt}, and saves the new user.
     * </p>
     *
     * @param request the registration data provided by the client.
     * @throws ResponseStatusException if the username already exists or validation fails.
     */
    public void registerUser(RegisterUserRequest request) {
        validationService.validate(request);

        if (userRepository.existsByUsername(request.getUsername())) {
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

    /**
     * Retrieves a list of all active users.
     *
     * @return a list of {@link User} objects with active status (status = 1).
     */
    public List<User> getAllUsers() {
        return userRepository.findAllByStatus(1);
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id the user's unique identifier.
     * @return an {@link Optional} containing the {@link User} if found, otherwise empty.
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Updates an existing user's information.
     * <p>
     * If the user does not exist, a {@link RuntimeException} is thrown.
     * </p>
     *
     * @param id          the ID of the user to update.
     * @param userDetails the new user data to be applied.
     * @return the updated {@link User} entity.
     * @throws RuntimeException if the user is not found.
     */
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

    /**
     * Retrieves a user by their username.
     *
     * @param username the username to search for.
     * @return an {@link Optional} containing the {@link User} if found.
     */
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    /**
     * Soft deletes a user by changing their status to 0 (inactive).
     *
     * @param id the ID of the user to deactivate.
     * @return the updated {@link User} entity with inactive status.
     * @throws RuntimeException if the user is not found.
     */
    public User deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setStatus(0);
        return userRepository.save(user);
    }
}
