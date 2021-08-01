package com.healthoverflow.healthOverflow.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@JsonIgnoreProperties(value = { "posts" })
public class ApplicationUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    @Column(unique = true)
    private String username;

    private String firstName;
    private String lastName;
    private String bio;
    private Date dateOfBirth;

    @OneToMany(mappedBy = "applicationUser")
    private List<Post> posts;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="UserRel",
            joinColumns={@JoinColumn(name="UserId")},
            inverseJoinColumns={@JoinColumn(name="ParentId")})
    private Set<ApplicationUser> following = new HashSet<>();

    //    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name="UserRel",
//            joinColumns={@JoinColumn(name="ParentId")},
//            inverseJoinColumns={@JoinColumn(name="UserId")})

    @ManyToMany(mappedBy="following")
    private Set<ApplicationUser> followers = new HashSet<>();

    public ApplicationUser() {
    }

    public ApplicationUser(String password, String username, String firstName, String lastName, String bio, Date dateOfBirth) {
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.dateOfBirth = dateOfBirth;
    }

    public Set<ApplicationUser> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<ApplicationUser> followers) {
        this.followers = followers;
    }

    public Set<ApplicationUser> getFollowing() {
        return following;
    }

    public void setFollowing(Set<ApplicationUser> following) {
        this.following = following;
    }

    public void addFollowing(ApplicationUser user){
        this.following.add(user);
    }
    public void addFollower(ApplicationUser user){
        this.followers.add(user);
    }

    public void deleteFollowing(ApplicationUser user){
        this.following.remove(user);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Long getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setRole(Role newRole) {
        roles.add(newRole);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
