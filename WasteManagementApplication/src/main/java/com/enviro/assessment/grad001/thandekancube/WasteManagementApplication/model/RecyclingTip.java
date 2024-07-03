package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity // Marks the class as a JPA entity
@Table(name = "tips") // Maps the class to the "tips" table in the database
public class RecyclingTip {

    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Specifies how the primary key should be generated
    private long Id;

    @Column(name = "tip") // Maps the field to the "tip" column in the database
    @NotNull(message = "Tip cannot be null") // Adds validation constraint for not null
    @Size(min = 1, max = 100, message = "Tip must be between 1 and 100 characters") // Adds validation constraint for size
    private String tip;

    @Column(name = "benefit")
    @Size(min = 1, max = 100, message = "Benefit must be between 1 and 100 characters")
    private String benefit;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public String toString() {
        return "Recycling Tips [id=" + Id + " tip=" + tip + ", benefit=" + benefit + ",]";
    }
}
