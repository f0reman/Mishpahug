package telran.ashkelon2018.mishpahug.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.ashkelon2018.mishpahug.domain.Person;
import telran.ashkelon2018.mishpahug.dto.enums.Type;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NotificationDto {
	
	Person recipient;
	Long notificationId;
    String title;
    String message;
    LocalDate date;
    Type type;
    boolean isRead ;
    Long eventId;
    
}
