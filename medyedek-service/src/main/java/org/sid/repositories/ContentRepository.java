package org.sid.repositories;

import org.sid.entities.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@Repository
public interface  ContentRepository extends JpaRepository<Content, Integer> {

    @Query("select c from Content c where c.post_id = :post_id")
    List<Content> getContentsOfPost(@Param("post_id") int post_id);

}
