package telran.ashkelon2018.mishpahug.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="This user has already created the event on this date and time!")
public class EventDublicateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
