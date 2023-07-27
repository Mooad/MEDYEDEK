package org.sid.services.nosql.repositories;

import org.sid.services.nosql.document.CommentsGrappes;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface CommentsTreeRepository extends ReactiveMongoRepository<CommentsGrappes,String> {
}
