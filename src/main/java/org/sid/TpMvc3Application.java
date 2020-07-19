package org.sid;

import org.sid.dao.CinemaRepository;
import org.sid.dao.SalleRepository;
import org.sid.dao.VilleRepository;
import org.sid.entities.Cinema;
import org.sid.entities.Film;
import org.sid.entities.Salle;
import org.sid.entities.Ticket;
import org.sid.entities.Ville;
import org.sid.service.InitialisationCinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class TpMvc3Application  implements ApplicationRunner{
	@Autowired
	private InitialisationCinemaService initCinemaService;
	@Autowired
	private RepositoryRestConfiguration restConfiguration;
	public static void main(String[] args) {
		SpringApplication.run(TpMvc3Application.class, args);
	}
	@Override
	public void run(ApplicationArguments args) throws Exception {
		restConfiguration.exposeIdsFor(Film.class);
		restConfiguration.exposeIdsFor(Salle.class,Ticket.class);
		initCinemaService.initVilles();
		initCinemaService.initCinemas();
		initCinemaService.initSalles();
		initCinemaService.initPlaces();
		initCinemaService.initSeances();
		initCinemaService.initCategories();
		initCinemaService.initFilmes();
		initCinemaService.initProjections();
		initCinemaService.initTickets();

	}
	




}
