package telran.ashkelon2018.mishpahug.domain;

import java.util.Set;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.Singular;

public class UserAccount {

	@Id
	@NotNull 
	String login;
	@NotNull 
	String password;
	@Singular 
	Set<String> roles;
//	LocalDateTime expdate;
	
	public void addRole(String role){
		roles.add("ROLE_" + role.toUpperCase());
	}

	public void removeRole(String role) {
		roles.remove(role);
	} 
	
	
}
