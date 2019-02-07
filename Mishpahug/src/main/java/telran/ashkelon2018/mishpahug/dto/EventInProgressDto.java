package telran.ashkelon2018.mishpahug.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.ashkelon2018.mishpahug.domain.Address;
import telran.ashkelon2018.mishpahug.domain.Event.Holiday;
import telran.ashkelon2018.mishpahug.dto.enums.Confession;
import telran.ashkelon2018.mishpahug.domain.Person;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventInProgressDto {

	Long eventId;
	String title;
	Holiday holiday;
	Confession confession;
	LocalDate date;
	LocalTime time;
	int duration;
	Address address;
	HashSet<String> food;
	String description;
	Person owner;
}
