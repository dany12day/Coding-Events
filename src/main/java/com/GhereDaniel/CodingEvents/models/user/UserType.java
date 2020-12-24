package com.GhereDaniel.CodingEvents.models.user;

import com.GhereDaniel.CodingEvents.models.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserType  extends AbstractEntity {

    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotNull
    private int accessClarence;

    @OneToMany
    @Valid
    private final List<Users> users = new ArrayList<>();

    public UserType(@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")  String name, @NotNull int accessClarence) {
        this.name = name;
        this.accessClarence = accessClarence;
    }

    public UserType() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public int getAccessClarence() {
        return accessClarence;
    }

    public void setAccessClarence(int accessClarence) {
        this.accessClarence = accessClarence;
    }

    public List<Users> getUsers() {
        return users;
    }



    @Override
    public String toString() {
        return this.name;
    }
}
