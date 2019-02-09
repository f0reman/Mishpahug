package telran.ashkelon2018.mishpahug.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY, reason="User is the owner of the event or already subscribed to it!")
public class SubscribeEventDublicateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
