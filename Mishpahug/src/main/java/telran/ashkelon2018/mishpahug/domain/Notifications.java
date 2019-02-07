package telran.ashkelon2018.mishpahug.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import telran.ashkelon2018.mishpahug.dto.enums.Type;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of={"keynotification"})
public class Notifications implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@NotNull
	Person recipient;
	@EmbeddedId
	KeyNotification keynotification;
	@NotNull
	String title;
	@NotNull
	String message;
	@NotNull
//	@Column(columnDefinition= "TIMESTAMP WITH TIME ZONE")
//	ZonedDateTime date;
	long date;
	@Enumerated(EnumType.STRING)
	Type type;
	boolean isRead ;

	
	
}
