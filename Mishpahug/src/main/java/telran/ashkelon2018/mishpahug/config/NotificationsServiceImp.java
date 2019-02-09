package telran.ashkelon2018.mishpahug.config;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.ashkelon2018.mishpahug.dao.EventRepository;
import telran.ashkelon2018.mishpahug.dao.NotificationRepository;
import telran.ashkelon2018.mishpahug.dao.PersonRepository;
import telran.ashkelon2018.mishpahug.domain.Event;
import telran.ashkelon2018.mishpahug.domain.EventForWork;
import telran.ashkelon2018.mishpahug.domain.KeyNotification;
import telran.ashkelon2018.mishpahug.domain.Notifications;
import telran.ashkelon2018.mishpahug.domain.Person;
import telran.ashkelon2018.mishpahug.dto.enums.Status;
import telran.ashkelon2018.mishpahug.dto.enums.Type;
import telran.ashkelon2018.mishpahug.utils.DateUtils;

@Service
public class NotificationsServiceImp implements NotificationsService {

	@Autowired
	EventRepository eventRepository;
	@Autowired
	NotificationRepository notificationRepository;
	@Autowired
	PersonRepository personRepository;
	
	public void workWithDoneEvents(HashSet<EventForWork> eventsDone) {
			System.out.println("Start method Check Done Events");
			for (EventForWork eventForWork : eventsDone) {
				Event eventUpdate = eventRepository.findById(eventForWork.getEventId()).orElse(null);
				if (eventUpdate!=null && !eventUpdate.getStatus().equals(Status.done))
				{
					System.out.println("Find Done Events");
					long countNotify = notificationDoneEventsForParticipants(eventForWork,eventUpdate);
					eventUpdate.setStatus(Status.done);
					eventUpdate.setCountNotify(eventUpdate.getCountNotify()+countNotify);
					eventRepository.saveAndFlush(eventUpdate);
					System.out.println("Finish Generate Notification for Done Events");
					}
				}
			}
	
	private long notificationDoneEventsForParticipants(EventForWork eventForWork, Event eventUpdate) {
		LinkedList <Notifications> notifyList = new LinkedList<>();
		for (Person personInEvent : personRepository.findAllById(eventUpdate.getParticipants())) {
			System.out.println("Start Generate Notification for Done Events");
			Notifications notifications = new Notifications();
			notifications.setKeynotification(new KeyNotification
				(eventUpdate.getCountNotify()+notifyList.size()+1,eventForWork.getEventId()));
			notifications.setDate(DateUtils.localDateTimeToMills(LocalDateTime.now()));
			notifications.setMessage("Congratulations! The holiday is over. You can vote.");
			notifications.setRead(false);		
			notifications.setType(Type.system);
			notifications.setRecipient(personInEvent);
			notifyList.add(notifications);
			}
			System.out.println("Notify for partipians" + notifyList+  " size= " +notifyList.size());
			notificationRepository.saveAll(notifyList);
			return notifyList.size();
		}
	
	public void workWithBadEvents(Set<EventForWork> eventsBad) {
		System.out.println("Start method Check Bad Events" + eventsBad);
		for (EventForWork eventForWork : eventsBad) {
			System.out.println("ID event " +eventForWork.getEventId());
			System.out.println("Count event in Repositiry" +eventRepository.count());
			
			Event eventUpdate = eventRepository.findById(eventForWork.getEventId()).orElse(null);
//			if (eventUpdate!=null) && !eventUpdate.getStatus().equals(Status.not_done))
		{
			System.out.println("Find Bad Events");
			long nbefs=notificationBadEventsForSubscribers(eventForWork,eventUpdate);
			long nbefp=notificationBadEventsForParticipants(eventForWork,eventUpdate,nbefs);
				notificationBadEventsForOwner(eventForWork,eventUpdate,nbefs+nbefp);
			eventUpdate.setStatus(Status.not_done);
			eventUpdate.setCountNotify(eventUpdate.getCountNotify()+1+nbefp+nbefs);
			eventRepository.saveAndFlush(eventUpdate);
			System.out.println("Finish Generate Notification for Bad Events. Total done =" + eventUpdate.getCountNotify()+1+nbefp+nbefs);
		}
		}
	}
	
	private void notificationBadEventsForOwner(EventForWork eventForWork, Event eventUpdate,long countNotify) {
			System.out.println("Start Generate Notification Owner for Bad Events");
			Notifications notifications = new Notifications();
			notifications.setKeynotification(new KeyNotification
				(eventUpdate.getCountNotify()+countNotify+1,eventForWork.getEventId()));
			notifications.setDate(DateUtils.localDateTimeToMills(LocalDateTime.now()));
			notifications.setMessage("Sorry! The holiday is canceled.");
			notifications.setRead(false);		
			notifications.setType(Type.system);
			notifications.setRecipient(eventUpdate.getOwner());
			System.out.println("eventForWork in ownerMethod " + eventForWork);
			System.out.println("Event in ownerMethod " + eventUpdate);
			System.out.println("Notification for owner " + notifications);
			notificationRepository.saveAndFlush(notifications);
		}
	
	private long notificationBadEventsForSubscribers(EventForWork eventForWork, Event eventUpdate) {
		System.out.println("Start Generate Notification Subscribers for Bad Events");
		LinkedList <Notifications> notifyList = new LinkedList<>();
		for (Person personInEvent : personRepository.findAllById(eventUpdate.getSubscribers())) {
			Notifications notifications = new Notifications();
			notifications.setKeynotification(new KeyNotification
					(eventUpdate.getCountNotify()+notifyList.size()+1,eventForWork.getEventId()));
			notifications.setDate(DateUtils.localDateTimeToMills(LocalDateTime.now()));
			notifications.setMessage("Sorry! The holiday is canceled.");
			notifications.setRead(false);		
			notifications.setType(Type.system);
			notifications.setRecipient(personInEvent);
			notifyList.add(notifications);
		}
		System.out.println("Notify for Subscribes" + notifyList + " size= " +notifyList.size());
		notificationRepository.saveAll(notifyList);
		return notifyList.size();
	}
	
	private long notificationBadEventsForParticipants(EventForWork eventForWork, Event eventUpdate, long countNotify) {
		System.out.println("Start Generate Notification Participants for Bad Events");
		LinkedList <Notifications> notifyList = new LinkedList<>();
		for (Person personInEvent : personRepository.findAllById(eventUpdate.getParticipants())) {
			Notifications notifications = new Notifications();
			notifications.setKeynotification(new KeyNotification
				(eventUpdate.getCountNotify()+countNotify+1,eventForWork.getEventId()));
			notifications.setDate(DateUtils.localDateTimeToMills(LocalDateTime.now()));
			notifications.setMessage("Sorry! The holiday is canceled.");
			notifications.setRead(false);		
			notifications.setType(Type.system);
			notifications.setRecipient(personInEvent);
			notifyList.add(notifications);
		}
		System.out.println("Participants" + notifyList + " size= " +notifyList.size());
		notificationRepository.saveAll(notifyList);
		return notifyList.size();
		}

}
