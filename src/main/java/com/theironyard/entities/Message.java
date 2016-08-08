package com.theironyard.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.theironyard.utilities.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by vasantia on 8/6/16.
 */

@Entity
@Table(name = "messages")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Message {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private int replyId;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime dateCreated;

    @ManyToOne
    private User owner;

    @ManyToMany
    @JsonIgnore
    private Collection<User> applicants;

    @ManyToOne
    @JsonIgnore
    private Posting postings;

    @ManyToOne
    private Message originalMessage;

    @OneToMany
    private Collection<Message> messages;

    public Message() {
        setCreated();
    }

    public Message(String text) {
        this.text = text;
        setCreated();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Collection<User> getApplicants() {
        return applicants;
    }

    public void setApplicants(Collection<User> applicants) {
        this.applicants = applicants;
    }

    public Posting getPostings() {
        return postings;
    }

    public void setPostings(Posting postings) {
        this.postings = postings;
    }

    public void setCreated(){
        dateCreated = LocalDateTime.now();
    }

    public void addUserToCollection (User user){
        applicants.add(user);
    }

    public Message getOriginalMessage() {
        return originalMessage;
    }

    public void setOriginalMessage(Message originalMessage) {
        this.originalMessage = originalMessage;
    }

    public Collection<Message> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

    public void addReply(Message message)
    { messages.add(message);}
}
