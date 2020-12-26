package com.GhereDaniel.CodingEvents.data.event;

import com.GhereDaniel.CodingEvents.models.event.EventDetails;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EventDetailsRepository extends CrudRepository<EventDetails, Integer> {

    @Transactional
    @Modifying
    @Query("update EventDetails u set u.contactEmail = :contactEmail where u.id = :id")
    void updateDetailsContactMail(@Param(value="id") int id, @Param(value="contactEmail") String contactEmail);

    @Transactional
    @Modifying
    @Query("update EventDetails u set u.description = :description where u.id = :id")
    void updateDetailsDescription(@Param(value="id") int id, @Param(value="description") String description);

}