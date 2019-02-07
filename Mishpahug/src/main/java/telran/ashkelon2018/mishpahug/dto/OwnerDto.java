package telran.ashkelon2018.mishpahug.dto;

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
public class OwnerDto {
				
				String fullName;;
				Confession confession;
				Gender gender;
				short age;
				HashSet<String> pictureLink;
				MaritalStatus maritalStatus;
				HashSet<String> foodPreferences;
				HashSet<String> languages;
				Double rate;
				Long numberOfVoters;
}
