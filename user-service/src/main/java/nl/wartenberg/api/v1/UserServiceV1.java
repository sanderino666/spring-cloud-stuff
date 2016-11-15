package nl.wartenberg.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import nl.wartenberg.user.User;
import nl.wartenberg.user.UserRepository;

@Service
public class UserServiceV1 {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceV1(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@HystrixCommand
	public User getUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}
}
