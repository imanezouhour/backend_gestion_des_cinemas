package org.sid.dao;

import java.util.List;
import java.util.Optional;

import org.sid.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource @RestResource
@CrossOrigin("*")
public interface CinemaRepository  extends JpaRepository<Cinema,Long>{
	


}
