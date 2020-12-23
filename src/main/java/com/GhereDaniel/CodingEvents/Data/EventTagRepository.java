package com.GhereDaniel.CodingEvents.Data;

import com.GhereDaniel.CodingEvents.models.EventTag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventTagRepository extends CrudRepository<EventTag, Integer> {

}
