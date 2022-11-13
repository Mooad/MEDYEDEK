package org.sid.repositories;

import org.sid.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@Repository
public interface UtilisateurRepository extends JpaRepository<User, Integer> {

    @Query(value="select * from user  where email = :email",nativeQuery = true)
    List<User> getUserLogin(@Param("email") String email);

    @Modifying
    @Query("update User u set u.isValidUser = '01' where u.userToken =:token")
    void validateUser(@Param("token") String token);

    @Modifying
    @Query("update User u set u.temp_pass = :temp_password where u.email =:email")
    void updateTemporaryPassword(@Param("email") String email,@Param("temp_password")String temp_password);

    @Query(value="select * from user  where  user_token = :token",nativeQuery = true)
    List<User> getUserbyToken(@Param("token") String token);
}
