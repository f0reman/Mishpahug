package telran.ashkelon2018.mishpahug.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of= {"city"})
@Embeddable
public class Address implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@NotNull
	String city;
@NotNull
	String place_id;
@NotNull
@Embedded
   Location location;
}