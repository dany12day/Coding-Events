package com.GhereDaniel.CodingEvents.models;

public enum EventType {
    CONFERENCE("Conference"),
    MEETUP("MeetUp"),
    WORKSHOP("WorkShop"),
    SOCIAL("Social");

    private final String displayName;

    EventType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
