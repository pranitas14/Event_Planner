package com.example.Event.Management.Entity;

import jakarta.validation.constraints.NotNull;

/**
 * Represents a registration request for an event.
 * Contains information about the user registering for the event.
 */
public class RegistrationRequest {

    /**
     * The ID of the user registering for the event.
     * Must not be null.
     */
    @NotNull(message = "User ID is mandatory")
    private Long userId;

    // Getters and Setters

    /**
     * Gets the ID of the user registering for the event.
     * 
     * @return the user ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user registering for the event.
     * 
     * @param userId the user ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
