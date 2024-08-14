package com.example.Event.Management.EventServiceImpl;

import com.example.Event.Management.Entity.User;
import com.example.Event.Management.EventRepository.UserRepository;
import com.example.Event.Management.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the UserService interface.
 * Provides business logic for managing users, including CRUD operations.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new user and saves it to the repository.
     * @param user the user to be created.
     * @return the created user.
     */
    @Override
    public User createUser(User user) {
        if (user == null) {
            logger.warn("Attempted to create a null user");
            throw new IllegalArgumentException("User must not be null");
        }
        User createdUser = userRepository.save(user);
        logger.info("Created new user with ID: {}", createdUser.getId());
        return createdUser;
    }

    /**
     * Retrieves all users from the repository.
     * @return a list of all users.
     */
    @Override
    public List<User> getAllUsers() {
        Iterable<User> usersIterable = userRepository.findAll();
        List<User> users = new ArrayList<>();
        usersIterable.forEach(users::add);
        logger.info("Retrieved all users");
        return users;
    }

    /**
     * Retrieves a user by its ID.
     * @param id the ID of the user.
     * @return the user if found, or null if not found.
     */
    @Override
    public User getUserById(Long id) {
        if (id == null) {
            logger.warn("Attempted to retrieve user with a null ID");
            throw new IllegalArgumentException("ID must not be null");
        }
        return userRepository.findById(id)
                .orElseGet(() -> {
                    logger.warn("User with ID {} not found", id);
                    return null;
                });
    }
}
