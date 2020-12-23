package com.GhereDaniel.CodingEvents.models.event;

import com.GhereDaniel.CodingEvents.models.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EventTag extends AbstractEntity {

    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @ManyToMany(mappedBy = "tags")
    private final List<Event> events = new ArrayList<>();

    public EventTag(String name) {
        this.name =name;
    }

    public EventTag() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name= name;
    }

    public String getDisplayName(){
        return "#"+ this.name;
    }

    public List<Event> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return this.name;
    }
}