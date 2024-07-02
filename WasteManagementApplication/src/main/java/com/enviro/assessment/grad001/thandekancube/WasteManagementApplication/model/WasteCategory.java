package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "categories")
public class WasteCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

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

    public void setId(long id) {
        Id = id;
    }

    public long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return "Waste Category [id=" + Id + " name=" + name + ", desc=" + description + ",]";
    }
}


