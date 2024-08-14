package com.example.Event.Management.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * Entity representing a vendor in the Event Management system.
 */
@Entity
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the vendor.
     * Must not be blank.
     */
    @NotBlank(message = "Vendor name is mandatory")
    @Column(nullable = false)
    private String name;

    /**
     * Details of the vendor.
     * Must not be blank.
     */
    @NotBlank(message = "Vendor details are mandatory")
    @Column(nullable = false)
    private String details;

    /**
     * The user associated with the vendor.
     * Represented as a many-to-one relationship.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and Setters

    /**
     * Gets the ID of the vendor.
     * 
     * @return the vendor ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the vendor.
     * 
     * @param id the vendor ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the vendor.
     * 
     * @return the vendor name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the vendor.
     * 
     * @param name the vendor name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the details of the vendor.
     * 
     * @return the vendor details
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets the details of the vendor.
     * 
     * @param details the vendor details
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Gets the user associated with the vendor.
     * 
     * @return the user associated with the vendor
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with the vendor.
     * 
     * @param user the user associated with the vendor
     */
    public void setUser(User user) {
        this.user = user;
    }
}
