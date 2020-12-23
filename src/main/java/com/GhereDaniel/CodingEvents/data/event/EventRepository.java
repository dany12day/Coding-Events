package com.GhereDaniel.CodingEvents.data.event;

import com.GhereDaniel.CodingEvents.models.event.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

}
