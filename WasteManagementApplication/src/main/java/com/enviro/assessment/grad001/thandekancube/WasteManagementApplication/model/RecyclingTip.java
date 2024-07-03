package com.enviro.assessment.grad001.thandekancube.WasteManagementApplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tips")
public class RecyclingTip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column(name = "tip")
    @NotNull(message = "Tip cannot be null")
    @Size(min = 1, max = 100, message = "Tip must be between 1 and 100 characters")
    private String tip;

    @Column(name = "benefit")
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
