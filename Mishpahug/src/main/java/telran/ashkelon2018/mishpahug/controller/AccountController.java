package telran.ashkelon2018.mishpahug.controller;

import java.security.Principal;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import telran.ashkelon2018.mishpahug.dto.security.UserProfileDto;
import telran.ashkelon2018.mishpahug.service.AccountService;



//@RestController
//@RequestMapping("/user")
public class AccountController {

	
//	@Autowired
	AccountService accountService;
	
	@PostMapping("/admin/hgfddRdg53bmm+ddsff6g64chhc")
	public UserProfileDto registerAdmin(Principal principal) {
		return accountService.addAdmin(principal.getName());
	}

	
	@PostMapping
	public UserProfileDto register(Principal principal) {
		return accountService.addUser(principal.getName());
	}
	
	
	@DeleteMapping("/{id}")
	@PreAuthorize("#id == authentication.name or hasAnyRoly('ADMIN','MODERATOR')")
	//@PostAuthorize
	public UserProfileDto remove(@PathVariable String id) {
		return accountService.removeUser(id);
	}

	@PutMapping("role/{login}/{role}")
	public Set<String> addRole(@PathVariable String id, @PathVariable String role){
		return accountService.addRole(id, role);
	};
	
	@DeleteMapping("role/{id}/{role}")
	public Set<String> removeRole(@PathVariable String id, @PathVariable String role){
		return accountService.removeRole(id, role);
	};
	
	@PutMapping("/password")
	public void changePassword(@RequestHeader("X-Authorization") String password,  Principal principal) {
	//FIXME
		accountService.changePassword(principal.getName(), password);
	};
	
	@GetMapping
	public UserProfileDto login(@RequestHeader("Authorization") Principal principal)
	{
		return accountService.GetUser(principal.getName());
	}
}