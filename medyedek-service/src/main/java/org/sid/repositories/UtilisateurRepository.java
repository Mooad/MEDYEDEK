package org.sid.repositories;

import org.sid.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    @Query(value="select * from utilisateur  where email = :email",nativeQuery = true)
    List<Utilisateur> getUserLogin(@Param("email") String email);

    @Modifying
    @Query("update Utilisateur u set u.isValidUser = '01' where u.userToken =:token")
    void validateUser(@Param("token") String token);

    @Modifying
    @Query("update Utilisateur u set u.temp_pass = :temp_password where u.email =:email")
    void updateTemporaryPassword(@Param("email") String email,@Param("temp_password")String temp_password);

    @Query(value="select * from utilisateur  where  user_token = :token",nativeQuery = true)
    List<Utilisateur> getUserbyToken(@Param("token") String token);

}
