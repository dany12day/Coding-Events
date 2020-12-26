package com.GhereDaniel.CodingEvents.data.event;

import com.GhereDaniel.CodingEvents.models.event.Event;
import com.GhereDaniel.CodingEvents.models.event.EventCategory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

    @Transactional
    @Modifying
    @Query("update Event u set u.name = :name where u.id = :id")
    void updateName(@Param("id") int id, @Param("name") String name);

    @Transactional
    @Modifying
    @Query("update Event u set u.eventCategory = :eventCategory where u.id = :id")
    void updateCategory(@Param(value="id") int id, @Param(value="eventCategory") EventCategory eventCategory);



}
