package telran.ashkelon2018.mishpahug.service;

import java.util.Set;

import telran.ashkelon2018.mishpahug.dto.security.UserProfileDto;

//@Service
public class AccountServiceImpl implements AccountService{

	@Override
	public UserProfileDto addAdmin(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfileDto addUser(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfileDto removeUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> addRole(String login, String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> removeRole(String login, String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changePassword(String login, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserProfileDto GetUser(String login) {
		// TODO Auto-generated method stub
		return null;
	}

}
