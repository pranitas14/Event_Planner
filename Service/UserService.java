package com.example.Event.Management.Service;

import com.example.Event.Management.Entity.User;
import java.util.List;

/**
 * Service interface for managing users.
 */
public interface UserService {

    /**
     * Creates a new user.
     * @param user the user to be created.
     * @return the created user.
     */
    User createUser(User user);

    /**
     * Retrieves all users.
     * @return a list of all users.
     */
    List<User> getAllUsers();

    /**
     * Retrieves a user by ID.
     * @param id the ID of the user to retrieve.
     * @return the user with the specified ID, or null if not found.
     */
    User getUserById(Long id);
}
