package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity // Marks the class as a JPA entity
@Table(name = "guidelines") // Maps the class to the "guidelines" table in the database
public class DisposalGuideline {

    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Specifies how the primary key should be generated
    private long Id;

    @Column(name = "guideline") // Maps the field to the "guideline" column in the database
    @NotNull(message = "Guideline cannot be null") // Adds validation constraint for not null
    @Size(min = 1, max = 100, message = "Guideline must be between 1 and 100 characters") // Adds validation constraint for size
    private String guideline;

    @Column(name = "complexity")
    @NotNull(message = "Complexity cannot be null")
    @Size(min = 1, max = 100, message = "Complexity must be between 1 and 100 characters")
    private String complexity;

    // Default constructor
    public DisposalGuideline() {

    }

    public DisposalGuideline(String guideline, String complexity) {
        this.guideline = guideline;
        this.complexity = complexity;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getGuideline() {
        return guideline;
    }

    public void setGuideline(String guideline) {
        this.guideline = guideline;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    // toString method for displaying object information
    public String toString() {
        return "Disposal Guideline [id=" + Id + " Guideline=" + guideline + ", complexity=" + complexity + ",]";
    }
}
