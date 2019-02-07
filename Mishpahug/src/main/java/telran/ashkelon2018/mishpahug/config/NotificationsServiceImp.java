package telran.ashkelon2018.mishpahug.config;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.ashkelon2018.mishpahug.dao.EventRepository;
import telran.ashkelon2018.mishpahug.dao.NotificationRepository;
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
	
	
	public void workWithDoneEvents(HashSet<EventForWork> eventsDone) {
			System.out.println("Start method Check Done Events");
			for (EventForWork eventForWork : eventsDone) {
				Event eventUpdate = eventRepository.findById(eventForWork.getEventId()).orElse(null);
				if (eventUpdate!=null && !eventUpdate.getStatus().equals(Status.done))
				{
					System.out.println("Find Done Events");
					notificationDoneEventsForParticipants(eventForWork,eventUpdate);
					eventUpdate.setStatus(Status.done);
					eventUpdate.setCountNotify(eventUpdate.getCountNotify()+1);
					eventRepository.saveAndFlush(eventUpdate);
					System.out.println("Finish Generate Notification for Done Events");
					}
				}
			}
	
	private void notificationDoneEventsForParticipants(EventForWork eventForWork, Event eventUpdate) {
		for (Person personInEvent : eventUpdate.getParticipants()) {
			System.out.println("Start Generate Notification for Done Events");
			Notifications notifications = new Notifications();
			notifications.setKeynotification(new KeyNotification
				(eventUpdate.getCountNotify()+1,eventForWork.getEventId()));
			notifications.setDate(DateUtils.localDateTimeToMills(LocalDateTime.now()));
			notifications.setMessage("Congratulations! The holiday is over. You can vote.");
			notifications.setRead(false);		
			notifications.setType(Type.system);
			notifications.setRecipient(personInEvent);
			notificationRepository.saveAndFlush(notifications);
			}
		}
	
	public void workWithBadEvents(Set<EventForWork> eventsBad) {
		System.out.println("Start method Check Bad Events" + eventsBad);
		for (EventForWork eventForWork : eventsBad) {
			System.out.println("ID event " +eventForWork.getEventId());
			System.out.println("Count event in Repositiry" +eventRepository.count());
			
			Event eventUpdate = eventRepository.findById(eventForWork.getEventId()).orElse(null);
//			if (eventUpdate!=null)// && !eventUpdate.getStatus().equals(Status.not_done))
//			{
			System.out.println("Find Bad Events");
				notificationBadEventsForParticipants(eventForWork,eventUpdate);
				notificationBadEventsForSubscribers(eventForWork,eventUpdate);
				notificationBadEventsForOwner(eventForWork,eventUpdate);
			eventUpdate.setStatus(Status.not_done);
			eventUpdate.setCountNotify(eventUpdate.getCountNotify()+1);
			eventRepository.saveAndFlush(eventUpdate);
			System.out.println("Finish Generate Notification for Bad Events");
//			}
		}
	}
	
	private void notificationBadEventsForOwner(EventForWork eventForWork, Event eventUpdate) {
			System.out.println("Start Generate Notification Owner for Bad Events");
			Notifications notifications = new Notifications();
			notifications.setKeynotification(new KeyNotification
				(eventUpdate.getCountNotify()+1,eventForWork.getEventId()));
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
	
	private void notificationBadEventsForSubscribers(EventForWork eventForWork, Event eventUpdate) {
		System.out.println("Start Generate Notification Subscribers for Bad Events");
		for (Person personInEvent : eventUpdate.getSubscribers()) {
		Notifications notifications = new Notifications();
		notifications.setKeynotification(new KeyNotification
			(eventUpdate.getCountNotify()+1,eventForWork.getEventId()));
		notifications.setDate(DateUtils.localDateTimeToMills(LocalDateTime.now()));
		notifications.setMessage("Sorry! The holiday is canceled.");
		notifications.setRead(false);		
		notifications.setType(Type.system);
		notifications.setRecipient(personInEvent);
		notificationRepository.saveAndFlush(notifications);
		}
	}
	
	private void notificationBadEventsForParticipants(EventForWork eventForWork, Event eventUpdate) {
		System.out.println("Start Generate Notification Participants for Bad Events");
		for (Person personInEvent : eventUpdate.getParticipants()) {
			Notifications notifications = new Notifications();
			notifications.setKeynotification(new KeyNotification
				(eventUpdate.getCountNotify()+1,eventForWork.getEventId()));
			notifications.setDate(DateUtils.localDateTimeToMills(LocalDateTime.now()));
			notifications.setMessage("Sorry! The holiday is canceled.");
			notifications.setRead(false);		
			notifications.setType(Type.system);
			notifications.setRecipient(personInEvent);
			notificationRepository.saveAndFlush(notifications);
			}
		}

}
