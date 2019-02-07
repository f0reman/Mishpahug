package telran.ashkelon2018.mishpahug.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of= {"latitude","longitude"})
@Embeddable
public class Location implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Double latitude;
	Double longitude;

	public double distanceFrom(Location other) {
		return Math.sqrt(Math
				.sqrt((Math.pow(other.latitude - this.latitude, 2))
						+ Math.pow(other.longitude - this.longitude, 2)));
	}
	


}