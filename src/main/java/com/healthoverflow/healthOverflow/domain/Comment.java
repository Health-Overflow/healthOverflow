package com.healthoverflow.healthOverflow.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String commentBody;
    private Date date;
private String anonymous;

    @ManyToOne
    private Post post;

    @ManyToOne
    private ApplicationUser applicationUser;

    public String getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(String anonymous) {
        this.anonymous = anonymous;
    }

    public Comment(String commentBody, Post post, ApplicationUser applicationUser, String anonymous) {
        this.commentBody = commentBody;
        this.post = post;
        this.applicationUser = applicationUser;
        this.date=new Date();
        this.anonymous=anonymous;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Comment(){}

    public Long getId() {
        return id;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }
}
