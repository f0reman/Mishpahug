package telran.ashkelon2018.mishpahug.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import telran.ashkelon2018.mishpahug.domain.EventForWork;

@Service
public interface NotificationsService {

	public void workWithDoneEvents(HashSet<EventForWork> eventsDone); 
	public void workWithBadEvents(Set<EventForWork> eventsBad); 
}
