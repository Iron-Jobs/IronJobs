package com.theironyard.entities;

import com.theironyard.utilities.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by EddyJ on 8/3/16.
 */
@Entity
@Table(name = "postings")
public class Posting {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer salaryStart;

    @Column(nullable = false)
    private Integer salaryEnd;

    @Column(nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime dateCreated;

    @ManyToMany
    private Collection<User> applicants;

    @ManyToOne
    private User owner;

    @ManyToOne
    private Location location;

    public Posting() {
    }

    public Posting(String title, String description, Integer salaryStart, Integer salaryEnd, Location location) {
        this.title = title;
        this.description = description;
        this.salaryStart = salaryStart;
        this.salaryEnd = salaryEnd;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSalaryStart() {
        return salaryStart;
    }

    public void setSalaryStart(Integer salaryStart) {
        this.salaryStart = salaryStart;
    }

    public Integer getSalaryEnd() {
        return salaryEnd;
    }

    public void setSalaryEnd(Integer salaryEnd) {
        this.salaryEnd = salaryEnd;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Collection<User> getApplicants() {
        return applicants;
    }

    public void setApplicants(Collection<User> applicants) {
        this.applicants = applicants;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
