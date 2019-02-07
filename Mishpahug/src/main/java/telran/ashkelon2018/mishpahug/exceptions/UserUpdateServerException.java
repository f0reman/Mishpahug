package telran.ashkelon2018.mishpahug.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="User update error")
public class UserUpdateServerException  extends RuntimeException{

	

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		

}

