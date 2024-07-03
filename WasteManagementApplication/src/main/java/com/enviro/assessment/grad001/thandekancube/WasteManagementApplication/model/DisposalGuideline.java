package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "guidelines")
public class DisposalGuideline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column(name = "guideline")
    @NotNull(message = "Guideline cannot be null")
    @Size(min = 1, max = 100, message = "Guideline must be between 1 and 100 characters")
    private String guideline;

    @Column(name = "complexity")
    @NotNull(message = "Complexity cannot be null")
    @Size(min = 1, max = 100, message = "Complexity must be between 1 and 100 characters")
    private String complexity;

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

    public String toString() {
        return "Disposal Guideline [id=" + Id + " Guideline=" + guideline + ", complexity=" + complexity + ",]";
    }
}
