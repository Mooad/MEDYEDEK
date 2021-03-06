package repositories;

import org.sid.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin
@Repository
public interface postsRepository extends JpaRepository<Post, Integer>{

	

}

