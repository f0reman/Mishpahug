package telran.ashkelon2018.mishpahug.dto;

import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ParticipantDto {

	   int userId;
       String fullName;
       String confession;
       String gender;
       int age;
       HashSet<String>pictureLink;
       String maritalStatus;
       HashSet<String>foodPreferences; 
       HashSet<String>languages;
       double rate;
       int numberOfVoters;
       boolean isInvited;
}
