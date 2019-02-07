package telran.ashkelon2018.mishpahug.service;

import java.util.Set;

import telran.ashkelon2018.mishpahug.dto.security.UserProfileDto;

public interface AccountService {

	UserProfileDto addAdmin(String token);
	
	UserProfileDto addUser(String token);
	
	UserProfileDto removeUser(String id);
	
	Set<String> addRole(String login, String role);
	
	Set<String> removeRole(String login, String role);
	
	void changePassword(String login, String password);
	
	UserProfileDto GetUser(String login);

}
