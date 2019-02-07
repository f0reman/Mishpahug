package telran.ashkelon2018.mishpahug.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.ashkelon2018.mishpahug.dto.enums.Confession;
import telran.ashkelon2018.mishpahug.dto.enums.Gender;
import telran.ashkelon2018.mishpahug.dto.enums.MaritalStatus;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of= {"id"})
public class Person implements Serializable{

	/**
	 * 
	 */
			private static final long serialVersionUID = 1L;
			@Id
			String id;
			String firstName;
			String lastName;
			
//			LocalDate dateOfBirth;
			
			long dateOfBirth;
			@Enumerated(EnumType.STRING)
			Gender gender;
			@Enumerated(EnumType.STRING)
			MaritalStatus maritalStatus;
			@Enumerated(EnumType.STRING)
			Confession confession;
			HashSet<String> pictureLink;
//			@Column(length=12)
			String phoneNumber;
			HashSet<String> foodPreferences;
			HashSet<String> languages;
			String description;
			double rate;
			long numberOfVoters;
		
			@OneToMany(mappedBy = "owner")
			Set<Event> events;
			@OneToMany(mappedBy="recipient")
			Set<Notifications> notifications;
			
			public Person(String id) {
				this.id = id;
			}

	
}
