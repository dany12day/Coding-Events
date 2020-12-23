package com.GhereDaniel.CodingEvents.data.event;

import com.GhereDaniel.CodingEvents.models.event.EventCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventCategoryRepository extends CrudRepository<EventCategory, Integer> {

}
