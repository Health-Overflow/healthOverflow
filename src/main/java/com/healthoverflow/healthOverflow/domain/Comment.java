package com.healthoverflow.healthOverflow.domain;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String commentBody;

    @ManyToOne
    private Post post;

    @ManyToOne
    private ApplicationUser applicationUser;

    public Comment(String commentBody, Post post, ApplicationUser applicationUser) {
        this.commentBody = commentBody;
        this.post = post;
        this.applicationUser = applicationUser;
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
