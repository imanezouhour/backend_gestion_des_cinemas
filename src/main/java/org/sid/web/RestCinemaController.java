package org.sid.web;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import javax.websocket.server.PathParam;

import org.sid.dao.CinemaRepository;
import org.sid.dao.FilmRepository;
import org.sid.dao.TicketRepository;
import org.sid.entities.Cinema;
import org.sid.entities.Film;
import org.sid.entities.Ticket;
import org.sid.entities.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@CrossOrigin("*")
@JsonFormat
public class RestCinemaController {
		@Autowired
		private FilmRepository filmRepository;
		@Autowired
		private CinemaRepository cinemaRepository;
		@Autowired
		private TicketRepository ticketRepository;
		@GetMapping(path="/imageFilm/{id}",produces = MediaType.IMAGE_JPEG_VALUE) 
		public byte[] image(@PathVariable(name="id")Long id ) throws Exception { 
			Film f=filmRepository.findById(id).get();
			String photoName=f.getPhoto();
			File file=new File(System.getProperty("user.home")+"/cinemas/"+photoName);
			Path path=Paths.get(file.toURI());
			return Files.readAllBytes(path);
		}
		
		@PostMapping(path="/payerTickets")
		@Transactional
		public void payerTickets(@RequestBody TicketForm ticketForm){
			List<Ticket> ticketList=new ArrayList<>();
			ticketForm.getTickets().forEach(idTicket ->{
				Ticket ticket=ticketRepository.findById(idTicket).get();
				ticket.setNomClient(ticketForm.getNomClient());
				ticket.setReserve(true);
				ticket.setCodePayment(ticketForm.getCodePayment());
				ticketRepository.save(ticket);
				ticketList.add(ticket);
			});
			
		}
		
		public boolean checkCodePaymentIsTaken( Integer codePayment) {
			Ticket ticket=ticketRepository.findTicketByCodePayment(codePayment);
			if(ticket==null) {
				return false;
			}else {
				return true;
			}
		}
		@PostMapping(path="/AjouterCinema")
		public void  AjouterCinema(@RequestBody Cinema cinema) {
			if(cinema!=null) {
				Cinema cinemaN=new Cinema();
				cinemaN.setName(cinema.getName());
				cinemaN.setNombreSalles((int) (3+Math.random()*7));
				Ville ville = new Ville();
				ville.setName(cinema.getName());
				cinemaN.setVille(ville);
				
			
				cinemaRepository.save(cinema);
			}
			
		}
		
		
		
		
	
	
}

@Data
class TicketForm{
	
	private String nomClient;
	private List<Long> tickets=new ArrayList<>();
	private int codePayment;
}


