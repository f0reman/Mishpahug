package telran.ashkelon2018.mishpahug.domain;

import java.io.Serializable;
import java.util.HashSet;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import telran.ashkelon2018.mishpahug.dto.enums.Confession;
import telran.ashkelon2018.mishpahug.dto.enums.Status;

@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {"eventId"})
@Builder
public class Event implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
			@Id
			@GeneratedValue(strategy=GenerationType.AUTO)
			Long eventId;
			@NotNull
			String title;
			@NotNull
			@Enumerated(EnumType.STRING)
			Holiday holiday;
			@NotNull
			@Enumerated(EnumType.STRING)
			Confession confession;
			@NotNull
//			@Column(columnDefinition= "TIMESTAMP WITH TIME ZONE")
//			ZonedDateTime dateTimeStart;
			long dateTimeStart;
			@NotNull
//			@Column(columnDefinition= "TIMESTAMP WITH TIME ZONE")
//			ZonedDateTime dateTimeFinish;
			long dateTimeFinish;
			@NotNull
			int duration;
			@NotNull
			@Embedded
			Address address;
			@NotNull
			HashSet<String> food;
			@NotNull
			HashSet<String> languages;
			@NotNull
			String description;
			@NotNull
			@Enumerated(EnumType.STRING)
			Status status;
			@ManyToOne
			@NotNull
			Person owner;
			long countNotify;
			HashSet<Person> participants;
			HashSet<Person> subscribers;
			
		public enum Holiday
		{
			pesah,shabat;
		}
		
			        	      
}