package org.sid.services.repositories.noreactive;

import org.sid.services.entities.Post;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface IPostCrudRepository extends R2dbcRepository<Post, Integer> {


}