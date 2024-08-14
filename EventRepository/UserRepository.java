package com.example.Event.Management.EventRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.Event.Management.Entity.User;

/**
 * Repository interface for accessing and managing User entities in the database.
 * Extends CrudRepository to provide CRUD operations.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    // This interface inherits basic CRUD methods from CrudRepository, such as:
    // - <S extends User> S save(S entity);
    // - Optional<User> findById(Long id);
    // - Iterable<User> findAll();
    // - void deleteById(Long id);

    // Additional custom query methods can be defined here if needed.
    // For example, to find a user by email, you might add:
    // Optional<User> findByEmail(String email);
}
