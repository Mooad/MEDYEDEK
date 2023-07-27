package org.sid.services.repositories;

import org.sid.services.entities.Post;
import org.springframework.data.r2dbc.repository.R2dbcRepository;


public interface PostsRepository extends R2dbcRepository<Post, Long> {

	

}

