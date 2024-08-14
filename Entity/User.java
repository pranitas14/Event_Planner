package com.example.Event.Management.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;

/**
 * Entity representing a user in the Event Management system.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the user.
     * Must not be blank.
     */
    @NotBlank(message = "User name is mandatory")
    private String name;

    /**
     * Email of the user.
     * Must be a valid email format and not blank.
     */
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    /**
     * Events the user is registered for.
     * Represented as a many-to-many relationship.
     */
    @ManyToMany(mappedBy = "registeredUsers")
    private Set<Event> events;

    /**
     * Vendors associated with the user.
     * Represented as a one-to-many relationship.
     */
    @OneToMany(mappedBy = "user")
    private Set<Vendor> vendors;

    // Getters and Setters

    /**
     * Gets the ID of the user.
     * 
     * @return the user ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     * 
     * @param id the user ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the user.
     * 
     * @return the user name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     * 
     * @param name the user name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email of the user.
     * 
     * @return the user email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     * 
     * @param email the user email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the events the user is registered for.
     * 
     * @return the set of events
     */
    public Set<Event> getEvents() {
        return events;
    }

    /**
     * Sets the events the user is registered for.
     * 
     * @param events the set of events
     */
    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    /**
     * Gets the vendors associated with the user.
     * 
     * @return the set of vendors
     */
    public Set<Vendor> getVendors() {
        return vendors;
    }

    /**
     * Sets the vendors associated with the user.
     * 
     * @param vendors the set of vendors
     */
    public void setVendors(Set<Vendor> vendors) {
        this.vendors = vendors;
    }
}
