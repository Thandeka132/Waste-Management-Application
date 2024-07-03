package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity // Marks the class as a JPA entity
@Table(name = "categories") // Maps the class to the "categories" table in the database
public class WasteCategory {

    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Specifies how the primary key should be generated
    private long Id;

    @Column(name = "name")
    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;

    @Column(name = "description")
    @NotNull(message = "Description cannot be null")
    @Size(min = 1, max = 100, message = "Description must be between 1 and 100 characters")
    private String description;

    public WasteCategory() {
    }

    public WasteCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString method for displaying object information
    public String toString() {
        return "Waste Category [id=" + Id + " name=" + name + ", desc=" + description + "]";
    }
}
