package com.GhereDaniel.CodingEvents.models.user;

import com.GhereDaniel.CodingEvents.models.AbstractEntity;
import com.GhereDaniel.CodingEvents.models.event.Event;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Users extends AbstractEntity {

    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String username;

    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String password;

    @Email(message = "Email not valid")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private UserType userType;

    @OneToMany(mappedBy = "eventCategory")
    private final List<Event> events = new ArrayList<>();

    public Users(@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters") String name, @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters") String username, @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters") String password, @Email(message = "Email not valid") @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters") String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
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

    @Override
    public String toString() {
        return this.name;
    }

}
