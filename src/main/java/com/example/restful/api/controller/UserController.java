package com.example.restful.api.controller;

import com.example.restful.api.error.ErrorResponse;
import com.example.restful.api.entity.User;
import com.example.restful.api.model.RegisterUserRequest;
import com.example.restful.api.model.WebResponse;
import com.example.restful.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class that handles RESTful API operations for {@link User} entities.
 * <p>
 * This class provides endpoints to perform CRUD operations on users, including
 * retrieving user details, registering new users, updating existing users, and
 * marking users as deleted.
 * </p>
 *
 * <p><b>Base URL:</b> /api/users</p>
 *
 * @author Mizan
 * @version 1.0
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves a list of all registered users.
     *
     * @return a list of {@link User} objects representing all users.
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Retrieves a specific user by their unique ID.
     *
     * @param id the ID of the user to retrieve.
     * @return a {@link ResponseEntity} containing the {@link User} if found,
     *         or an empty response with HTTP 404 status if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retrieves a specific user by their username.
     *
     * @param username the username of the user to retrieve.
     * @return a {@link ResponseEntity} containing the {@link User} if found,
     *         or an empty response with HTTP 404 status if not found.
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Registers a new user in the system.
     *
     * @param request the request body containing user registration details.
     * @return a {@link WebResponse} object indicating that the user has been successfully created.
     */
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> registerUser(@RequestBody RegisterUserRequest request) {
        userService.registerUser(request);
        return WebResponse.<String>builder().data("Created").build();
    }

    /**
     * Updates an existing user's information based on their ID.
     *
     * @param id          the ID of the user to update.
     * @param userDetails the updated user details.
     * @return a {@link ResponseEntity} containing the updated {@link User}.
     * @throws IllegalArgumentException if the user does not exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Marks a user as deleted or removes the user record.
     *
     * @param id the ID of the user to delete.
     * @return a {@link ResponseEntity} containing the deleted {@link User} data.
     */
    @PutMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        User deletedUser = userService.deleteUser(id);
        return ResponseEntity.ok(deletedUser);
    }
}
