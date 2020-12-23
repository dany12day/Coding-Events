package com.GhereDaniel.CodingEvents.data.user;

import com.GhereDaniel.CodingEvents.models.user.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {

}
