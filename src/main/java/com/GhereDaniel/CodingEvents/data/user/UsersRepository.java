package com.GhereDaniel.CodingEvents.data.user;

import com.GhereDaniel.CodingEvents.models.user.UserType;
import com.GhereDaniel.CodingEvents.models.user.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {

    @Transactional
    @Modifying
    @Query("update Users u set u.name = :name where u.id = :id")
    void updateName(@Param("id") int id, @Param("name") String name);

    @Transactional
    @Modifying
    @Query("update Users u set u.email = :email where u.id = :id")
    void updateEmail(@Param(value="id") int id, @Param(value="email") String email);

    @Transactional
    @Modifying
    @Query("update Users u set u.username = :username where u.id = :id")
    void updateUsername(@Param(value="id") int id, @Param(value="username") String name);

    @Transactional
    @Modifying
    @Query("update Users u set u.password = :password where u.id = :id")
    void updatePassword(@Param(value="id") int id, @Param(value="password") String name);

    @Transactional
    @Modifying
    @Query("update Users u set u.userType = :userType where u.id = :id")
    void updateUserType(@Param(value="id") int id, @Param(value="userType") UserType name);

}
