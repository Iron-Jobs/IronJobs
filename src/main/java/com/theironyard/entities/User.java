package com.theironyard.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.theironyard.utilities.LocalDateTimeConverter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

/**
 * Created by vasantia on 8/3/16.
 */

@Entity
@Table(name = "users")
public class User {

    public static final int TOKEN_EXPIRATION = 30;

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false, unique = true)
    @ColumnDefault("'abcabcabcabcabc'")
    @JsonIgnore
    private String token;

    @Column(nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    @ColumnDefault("'1970-01-01'")
    private LocalDateTime expiration;

    @OneToMany
    @JsonIgnore
    private Collection<Posting> postingCollection;

    @OneToMany
    @JsonIgnore
    private Collection<Message> messageCollection;

    public String generateToken() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    public void setTokenAndExpiration() {
        token = generateToken();
        expiration = LocalDateTime.now().plus(TOKEN_EXPIRATION, ChronoUnit.DAYS);
    }

    public User() {
        setTokenAndExpiration();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        setTokenAndExpiration();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    public boolean isTokenValid(){
        return expiration.isAfter(LocalDateTime.now());
    }

    public String regenerate(){
        setTokenAndExpiration();
        return token;
    }

    public Collection<Posting> getPostingCollection() {
        return postingCollection;
    }

    public void setPostingCollection(Collection<Posting> postingCollection) {
        this.postingCollection = postingCollection;
    }

    public void addPostingToCollection(Posting posting){
        postingCollection.add(posting);
    }

    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }
}


