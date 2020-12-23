package com.GhereDaniel.CodingEvents.Data;

import com.GhereDaniel.CodingEvents.models.EventDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDetailsRepository extends CrudRepository<EventDetails, Integer> {

}