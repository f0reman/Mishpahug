package telran.ashkelon2018.mishpahug.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import telran.ashkelon2018.mishpahug.domain.KeyNotification;
import telran.ashkelon2018.mishpahug.domain.Notifications;



public interface NotificationRepository extends JpaRepository<Notifications, KeyNotification>{



}
