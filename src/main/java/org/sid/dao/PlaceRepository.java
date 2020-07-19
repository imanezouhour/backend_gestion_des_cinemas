package org.sid.dao;

import org.sid.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
@RepositoryRestResource @RestResource
@CrossOrigin("*")
public interface PlaceRepository extends JpaRepository<Place, Long> {

}
