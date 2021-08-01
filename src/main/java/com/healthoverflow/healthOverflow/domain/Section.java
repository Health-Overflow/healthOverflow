package com.healthoverflow.healthOverflow.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(columnDefinition="TEXT")
    private String sectionImage;
    private String title;
    @Column(columnDefinition="TEXT")
    private String description;

    public Section( String sectionImage, String title, String description) {
        this.sectionImage = sectionImage;
        this.title = title;
        this.description = description;
    }
    @OneToMany(mappedBy = "section")
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public Section() {
    }

    public Long getId() {
        return id;
    }

    public String getSectionImage() {
        return sectionImage;
    }

    public void setSectionImage(String sectionImage) {
        this.sectionImage = sectionImage;
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
}
