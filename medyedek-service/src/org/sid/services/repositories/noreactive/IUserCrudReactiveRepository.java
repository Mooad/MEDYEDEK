package org.sid.services.repositories.noreactive;


import org.sid.services.entities.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface IUserCrudReactiveRepository extends  R2dbcRepository<User, Long> {


    @Query(value = "SELECT * from user  where email=:email")
    Flux<User> findByEmail(String email);


}
