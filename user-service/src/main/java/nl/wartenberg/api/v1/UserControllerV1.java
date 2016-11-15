package nl.wartenberg.api.v1;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.wartenberg.user.User;

@RestController
@RequestMapping(path = "/v1")
public class UserControllerV1 {

	private final UserServiceV1 userService;

	@Autowired
	public UserControllerV1(UserServiceV1 userService) {
		this.userService = userService;
	}

	@RequestMapping(path = "/me")
	public ResponseEntity<User> me(Principal principal) {
		User user = null;
		if (principal != null) {
			user = userService.getUserByUsername(principal.getName());
		}

		return Optional.ofNullable(user).map(a -> new ResponseEntity<User>(a, HttpStatus.OK))
				.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
	}
}
