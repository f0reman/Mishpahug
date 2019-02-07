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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventAddDto {

	String title;
	Holiday holiday;
	Address address;
	Confession confession;
	LocalDate date;
	LocalTime time;
	int duration;
	HashSet<String> food;
	String description;
	HashSet<String> languages;
		
	}
