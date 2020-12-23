package com.GhereDaniel.CodingEvents.data.event;

import com.GhereDaniel.CodingEvents.models.event.EventDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDetailsRepository extends CrudRepository<EventDetails, Integer> {

}