package com.GhereDaniel.CodingEvents.models.user;

import com.GhereDaniel.CodingEvents.models.AbstractEntity;
import com.GhereDaniel.CodingEvents.models.event.Event;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Users extends AbstractEntity {

    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Email(message = "Email not valid")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String email;

    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String username;

    @Size(min = 3, message = "Name must be between 3 and 50 characters")
    private String password;

    private boolean enabled;

    @ManyToOne
    @Valid
    private UserType userType;

    @OneToMany(mappedBy = "eventCategory")
    private final List<Event> events = new ArrayList<>();

    public Users(@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters") String name, @Email(message = "Email not valid") @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters") String email, @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters") String username, @Size(min = 3, message = "Name must be between 3 and 50 characters") String password, boolean enabled, @Valid UserType userType) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.userType = userType;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Users() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<Event> getEvents() {
        return events;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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



    @Override
    public String toString() {
        return this.name;
    }

}
