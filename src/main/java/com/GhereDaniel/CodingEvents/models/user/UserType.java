package com.GhereDaniel.CodingEvents.models.user;

import com.GhereDaniel.CodingEvents.models.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class UserType  extends AbstractEntity {

    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotNull
    private int accessClarence;

    public UserType(@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters") String name, @NotNull int accessClarence) {
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

    @Override
    public String toString() {
        return this.name;
    }
}
