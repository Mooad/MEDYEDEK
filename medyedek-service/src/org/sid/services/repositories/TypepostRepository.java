package org.sid.services.repositories;


import org.sid.services.entities.Typepost;
import org.springframework.data.r2dbc.repository.R2dbcRepository;


public interface TypepostRepository  extends R2dbcRepository<Typepost, Integer> {

}
