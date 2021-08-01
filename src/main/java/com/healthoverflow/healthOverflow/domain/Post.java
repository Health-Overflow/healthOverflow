package com.healthoverflow.healthOverflow.domain;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Post{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="TEXT")
    private String body;

    private Date date;

    @ManyToOne
    @JoinColumn(name="applicationUser_id")
    private ApplicationUser applicationUser;

    @ManyToOne
    @JoinColumn(name="section_id")
    private Section section;

    public Post() {
    }

    public Post(ApplicationUser applicationUser) {
        this.body = body;
        applicationUser = applicationUser;
        this.date = new Date();
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        applicationUser = applicationUser;
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getFormattedDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
