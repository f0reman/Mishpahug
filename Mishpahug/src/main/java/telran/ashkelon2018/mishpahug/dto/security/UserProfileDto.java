package telran.ashkelon2018.mishpahug.dto.security;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Builder
	@Setter
	public class UserProfileDto {
		String login;
		Set<String> roles;


}
