package telran.ashkelon2018.mishpahug.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.ashkelon2018.mishpahug.dao.EventInProgresRepository;
import telran.ashkelon2018.mishpahug.dao.EventRepository;
import telran.ashkelon2018.mishpahug.dao.PersonRepository;
import telran.ashkelon2018.mishpahug.dao.SettingRepository;
import telran.ashkelon2018.mishpahug.domain.Event;
import telran.ashkelon2018.mishpahug.domain.EventForWork;
import telran.ashkelon2018.mishpahug.domain.Person;
import telran.ashkelon2018.mishpahug.domain.SettingsProject;
import telran.ashkelon2018.mishpahug.dto.DataDto;
import telran.ashkelon2018.mishpahug.dto.EventAddDto;
import telran.ashkelon2018.mishpahug.dto.EventInProgressDto;
import telran.ashkelon2018.mishpahug.dto.PersonDto;
import telran.ashkelon2018.mishpahug.dto.enums.Status;
import telran.ashkelon2018.mishpahug.exceptions.EventCreateException;
import telran.ashkelon2018.mishpahug.exceptions.EventCreateToWorkException;
import telran.ashkelon2018.mishpahug.exceptions.EventDublicateException;
import telran.ashkelon2018.mishpahug.exceptions.UserAddExist;
import telran.ashkelon2018.mishpahug.exceptions.UserAddServerException;
import telran.ashkelon2018.mishpahug.exceptions.UserUpdateNotFoundException;
import telran.ashkelon2018.mishpahug.exceptions.UserUpdateServerException;
import telran.ashkelon2018.mishpahug.utils.DateUtils;

@Service
public class MishpahugServiceImpl implements MishpahugService {
	
	@Autowired
	SettingRepository settingRepository;
	@Autowired
	PersonRepository personRepository;
	@Autowired
	EventRepository eventRepository;
	@Autowired
	EventInProgresRepository eventInProgresRepository;
	

	@Override
	public PersonDto getPersonById(String id) {
		Person person = personRepository.findById(id).orElse(null);
		if (person == null) {
			return null;
		}
		return convertPersonToPersonDto(person);
	}

	private PersonDto convertPersonToPersonDto(Person person) {
		
			return PersonDto.builder()
				
					.firstName(person.getFirstName())
					.lastName(person.getLastName())
					.dateOfBirth(DateUtils.millsToZonedDateTime(person.getDateOfBirth()).toLocalDate())
					.gender(person.getGender())
					.maritalStatus(person.getMaritalStatus())
					.confession(person.getConfession())
					.pictureLink(person.getPictureLink())
					.phoneNumber(person.getPhoneNumber())
					.foodPreferences(person.getFoodPreferences())
					.languages(person.getLanguages())
					.description(person.getDescription())
					.rate(person.getRate())
					.numberOfVoters(person.getNumberOfVoters())
					.build();
		
		}

	@Override
	public Set<EventInProgressDto> getEvenInProgress(Integer page, Integer size, DataDto data) {
	
		
		return null;
	}

	@Override
	public boolean addPerson(String id) {
		
	if (personRepository.existsById(id)) throw new UserAddExist();	
	
	try {	
		Person person = new Person(id);
		personRepository.saveAndFlush(person);
		return true;
	}
	catch (Exception e) {
		throw new UserAddServerException();
		}
		
	}


	@Override
//	@Transactional(isolation=Isolation.SERIALIZABLE)
	public PersonDto updPerson(PersonDto personDto, String id) {
			
		boolean flag=true;
		
		if (personRepository.findById(id).orElse(null)==null ){ 
			throw new UserUpdateNotFoundException();
			}
			try {
			String[] food ={"vegetarian","kosher", "non-vegetarian"};
			String[] language = {"Hebrew","English","Russian"};
			String regexName = "[A-Z][a-z]+( [A-Z][a-z]+)?";
			String regexPhone = "(\\+[0-9]{11}$)|([0-9]{10}$)";
			Person personNew = personRepository.findById(id).orElse(null);	
			System.out.println("persID " + personNew.getId()+"   flag " + flag);
			
			if (Pattern.matches(regexName, personDto.getFirstName().trim()))  
			 personNew.setFirstName(personDto.getFirstName()); else flag=false;
			System.out.println("persFirstName " + personNew.getFirstName()+"   flag " + flag);
			if (Pattern.matches(regexName, personDto.getLastName().trim()))  
				 personNew.setLastName(personDto.getLastName()); else flag=false;
			System.out.println("persLastName " + personNew.getLastName()+"   flag " + flag);
			if (Pattern.matches(regexPhone, personDto.getPhoneNumber().trim()))  
				 personNew.setPhoneNumber(personDto.getPhoneNumber()); else flag=false;
			System.out.println("persPhoneNumber " + personNew.getPhoneNumber()+"   flag " + flag);
			if (personDto.getDateOfBirth().isBefore(LocalDate.now().minusYears(120))||
					personDto.getDateOfBirth().isAfter(LocalDate.now())) flag=false;
			else  personNew.setDateOfBirth(DateUtils.localDateToMills(personDto.getDateOfBirth()));
			System.out.println("persDateOfBirth " + personNew.getDateOfBirth()+"   flag " + flag);
			personNew.setGender(personDto.getGender());
			System.out.println("persGender " + personNew.getGender()+"   flag " + flag);
			personNew.setMaritalStatus(personDto.getMaritalStatus());
			System.out.println("persMaritalStatus " + personNew.getMaritalStatus()+"   flag " + flag);
			personNew.setConfession(personDto.getConfession());
			System.out.println("persConfession " + personNew.getConfession()+"   flag " + flag);
			personNew.setPictureLink(personDto.getPictureLink());
			System.out.println("persPictureLink " + personNew.getPictureLink()+"   flag " + flag);
			if (personDto.getFoodPreferences().stream().
					filter(p->!(Arrays.asList(food).contains(p))).findFirst().isPresent())
					flag=false;
			else 	personNew.setFoodPreferences(personDto.getFoodPreferences());
			System.out.println("persFoodPreferences " + personNew.getFoodPreferences()+"   flag " + flag);
			if (personDto.getLanguages().stream().
					filter(p->!(Arrays.asList(language).contains(p))).findFirst().isPresent())
					flag=false;
			else personNew.setLanguages(personDto.getLanguages());
			System.out.println("persLanguages " + personNew.getLanguages()+"   flag " + flag);
			personNew.setDescription(personDto.getDescription());
			System.out.println("persDescription " + personNew.getDescription()+"   flag " + flag);
			personRepository.saveAndFlush(personNew);
			
			if (flag) return convertPersonToPersonDto(personNew);
			}
			catch (Exception e) {
				throw new UserUpdateServerException();
			}
			throw new UserUpdateServerException();
			
		}

	
@Transactional
@Override
	public boolean addEvent(EventAddDto eventAddDto, String id) {
//		ZonedDateTime start=eventAddDto.getDate().atTime(eventAddDto.getTime()).atZone(ZoneId.of("UTC+2"));
//		ZonedDateTime finish=eventAddDto.getDate().atTime(eventAddDto.getTime()).plusHours(eventAddDto.getDuration()).atZone(ZoneId.of("UTC+2"));;
		long start=DateUtils.localDateTimeToMills(eventAddDto.getDate().atTime(eventAddDto.getTime()));
		long finish=DateUtils.localDateTimeToMills(eventAddDto.getDate().atTime(eventAddDto.getTime()).plusHours(eventAddDto.getDuration()));;
		
if (eventAddDto.getDate().minusDays(2).isAfter(LocalDate.now()))
		throw new EventCreateException();

	if (eventRepository.checkEvent(start,finish,id)>0)
			throw new EventDublicateException();
	
//	if (eventRepository.checkEvent(id).stream()
//			.filter(p->((p.getDateTimeStart().isAfter(start)&&p.getDateTimeStart().isBefore(finish))||
//					(p.getDateTimeFinish().isAfter(start)&&p.getDateTimeStart().isBefore(finish)))||p.getDateTimeStart().equals(start))
//			.findFirst().isPresent())
//		throw new EventDublicateException();

	System.out.println("Start bild NewEvent");
try {	
//	SettingsProject settingsProject = settingRepository.findById("countEventAll").orElse(null);
//	
//	settingsProject.setCountId(settingsProject.getCountId()+1);
//	System.out.println("Count in setting " + settingsProject.getCountId());

//	Event eventNew = Event.builder()
//	.eventId(settingsProject.getCountId())
//	.owner(personRepository.getPersonById(id))
//	.title(eventAddDto.getTitle())
//	.holiday(eventAddDto.getHoliday())
//	.duration(eventAddDto.getDuration()*60)
//	.dateTimeStart(start)
//	.dateTimeFinish(finish)
//	.address(eventAddDto.getAddress())
//	.confession(eventAddDto.getConfession())
//	.description(eventAddDto.getDescription())
//	.languages(eventAddDto.getLanguages())
//	.food(eventAddDto.getFood())
//	.status(Status.in_process)
//	.build();

	
		Event eventNew = new Event();
		System.out.println("Set 2 ");
		eventNew.setOwner(personRepository.findById(id).orElse(null));
		System.out.println("Set 3 ");
		eventNew.setTitle(eventAddDto.getTitle());
		System.out.println("Set 4 ");
		eventNew.setHoliday(eventAddDto.getHoliday());
		System.out.println("Set 5 ");
		eventNew.setDuration(eventAddDto.getDuration()*60);
		System.out.println("Set 6 ");
		eventNew.setDateTimeStart(start);
		System.out.println("Set 7 ");
		eventNew.setDateTimeFinish(finish);
		System.out.println("Set 8 ");
		eventNew.setAddress(eventAddDto.getAddress());
		System.out.println("Set 9 ");
		eventNew.setConfession(eventAddDto.getConfession());
		System.out.println("Set 10 ");
		eventNew.setDescription(eventAddDto.getDescription());
		System.out.println("Set 11 ");
		eventNew.setLanguages(eventAddDto.getLanguages());
		System.out.println("Set 12 ");
		eventNew.setFood(eventAddDto.getFood());
		System.out.println("Set 13 ");
		eventNew.setStatus(Status.in_process);
		System.out.println("Set 14 ");
		eventNew.setCountNotify(0);
		System.out.println("Set 15 ");
		eventNew.setSubscribers(new HashSet<Person>());
		System.out.println("Set 16 ");
		eventNew.setParticipants(new HashSet<Person>());
		
		
		System.out.println("Start save in EventRepository");
		
		eventRepository.saveAndFlush(eventNew);
		System.out.println("Start save in EventForWorkRepository");
		addEventToWork(eventNew);
		return true;
		
	}
	catch (Exception e) {
		throw new EventCreateException();
		}
		
	}
		
	public boolean addEventToWork(Event eventAdd) {
	
	try {	
		System.out.println("Start buil in EventWork");
		
		EventForWork eventNew = EventForWork.builder()
				.eventId(eventAdd.getEventId())
				.dateTimeStart(eventAdd.getDateTimeStart())
				.dateTimeFinish(eventAdd.getDateTimeStart())
				.status(eventAdd.getStatus())
				.build();
			System.out.println("Start save in eventInProgresRepository");
		
			eventInProgresRepository.saveAndFlush(eventNew);
		
		return true;
	}
		catch (Exception e) {
			throw new EventCreateToWorkException();
			}
		}
	}




