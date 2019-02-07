package telran.ashkelon2018.mishpahug.dto;

import java.time.LocalDate;
import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.ashkelon2018.mishpahug.dto.enums.Confession;
import telran.ashkelon2018.mishpahug.dto.enums.Gender;
import telran.ashkelon2018.mishpahug.dto.enums.MaritalStatus;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PersonDto {
				
				String firstName;
				String lastName;
				LocalDate dateOfBirth;
				Gender gender;
				MaritalStatus maritalStatus;
				Confession confession;
				HashSet<String> pictureLink;
				String phoneNumber;
				HashSet<String> foodPreferences;
				HashSet<String> languages;
				String description;
				Double rate;
				Long numberOfVoters;
}
