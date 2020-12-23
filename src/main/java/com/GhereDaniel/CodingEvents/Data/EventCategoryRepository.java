package com.GhereDaniel.CodingEvents.Data;

import com.GhereDaniel.CodingEvents.models.EventCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventCategoryRepository extends CrudRepository<EventCategory, Integer> {

}
