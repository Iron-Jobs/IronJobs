package com.theironyard.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by EddyJ on 8/3/16.
 */
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @OneToMany
    private Collection<Posting> postingCollection;

    public Location() {
    }

    public Location(String city, String state) {
        this.city = city;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Collection<Posting> getPostingCollection() {
        return postingCollection;
    }

    public void setPostingCollection(Collection<Posting> postingCollection) {
        this.postingCollection = postingCollection;
    }
}
