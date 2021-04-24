package org.sid.repositories;

import org.sid.entities.Typepost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin
public interface TypepostRepository  extends JpaRepository<Typepost, Integer>{

}
