package telran.ashkelon2018.mishpahug.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import telran.ashkelon2018.mishpahug.dto.enums.Status;
//import cz.cvut.kbss.jopa.model.annotations;

@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {"eventId"})
@Builder
//@Table(name = "event_for_work") 
//@SqlResultSetMapping(name = "EventForWork", classes = {@ConstructorResult(
//    targetClass = EventForWork.class, columns = { 
//     @ColumnResult(eventId = "event_id"), 
//     @ColumnResult(dateTimeStart = "date_time_start"), 
//     @ColumnResult(dateTimeFinish = "date_time_finish"), 
//     @ColumnResult(status = "status") 
//    	})
//	})


public class EventForWork implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
			@Id
			Long eventId;
			@NotNull
//			@Column(columnDefinition= "TIMESTAMP WITH TIME ZONE")
//			ZonedDateTime dateTimeStart;
			long dateTimeStart;
			@NotNull
//			@Column(columnDefinition= "TIMESTAMP WITH TIME ZONE")
//			ZonedDateTime dateTimeFinish;
			long dateTimeFinish;
			@NotNull
			@Enumerated(EnumType.STRING)
			Status status;
			        	      
}