package com.GhereDaniel.CodingEvents.data.user;

import com.GhereDaniel.CodingEvents.models.user.UserType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends CrudRepository<UserType, Integer> {

}
