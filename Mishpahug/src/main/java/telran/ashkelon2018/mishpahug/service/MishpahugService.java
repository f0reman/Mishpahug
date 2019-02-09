package telran.ashkelon2018.mishpahug.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import telran.ashkelon2018.mishpahug.dto.DataDto;
import telran.ashkelon2018.mishpahug.dto.EventAddDto;
import telran.ashkelon2018.mishpahug.dto.EventInProgressDto;
import telran.ashkelon2018.mishpahug.dto.PersonDto;

@Service
public interface MishpahugService {
	PersonDto getPersonById(String id);
	Set<EventInProgressDto> getEvenInProgress(Integer page, Integer size, DataDto data);
	boolean addPerson(String id);
	PersonDto updPerson(PersonDto personDto, String id);
	
	boolean addEvent(EventAddDto eventAddDto,String id);
	
	boolean subscribeToEvent(String id, Long eventId);
	boolean unSubscribeToEvent(String id, Long eventId);
	
}
