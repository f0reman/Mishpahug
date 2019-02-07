package telran.ashkelon2018.mishpahug.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="User exist!")
public class UserAddExist extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
