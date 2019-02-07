package telran.ashkelon2018.mishpahug.controller;

import static telran.ashkelon2018.mishpahug.api.MishpahugApi.*;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.ashkelon2018.mishpahug.dto.DataDto;
import telran.ashkelon2018.mishpahug.dto.EventAddDto;
import telran.ashkelon2018.mishpahug.dto.EventInProgressDto;
import telran.ashkelon2018.mishpahug.dto.PersonDto;
import telran.ashkelon2018.mishpahug.service.MishpahugService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MishpahugController {

	@Autowired
	MishpahugService mishpahugService;
	
	@GetMapping(GETPERSON+ "/{id}")
	public PersonDto getPersonById(@PathVariable String id) {
		return mishpahugService.getPersonById(id);
	}	
	
//	event/allprogresslist?page=0&size=10
	
	@PostMapping(EVENTINPROGRES)
	public Set<EventInProgressDto> addBook(@RequestParam("page") int page, @RequestParam("size") int size,  @RequestBody DataDto data) {
		return mishpahugService.getEvenInProgress(page,size,data);
	}
	@PostMapping(ADDPERSON+ "/{id}")
	public boolean addBook(@PathVariable String id) {
		return mishpahugService.addPerson(id);
	}
	
	@PostMapping(UPDUSER+ "/{id}")
	public PersonDto updPerson(@RequestBody PersonDto personDto,@PathVariable String id) {
		return mishpahugService.updPerson(personDto,id);
	}
	
	@PostMapping(ADDEVENT+ "/{id}")
	public boolean addEvent(@RequestBody EventAddDto eventAddDto,@PathVariable String id) {
		return mishpahugService.addEvent(eventAddDto,id);
	}
	
}