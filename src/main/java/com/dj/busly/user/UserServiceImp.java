package com.dj.busly.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dj.busly.user.User.UserJSON;
import com.dj.busly.utils.ResponseMessage;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	private UserRepository userRepository;

	
	@Override
	public ResponseMessage saveUser(UserJSON userJson) {
		// TODO Auto-generated method stub
		
		User user = new User();
		
		user.fromJSON(userJson);
		
		User user2 = userRepository.save(user);
	
		/**
		 * 
		 */
		
		
		return new ResponseMessage("data is stored "+user2.username);
	}




	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		
	
		return this.userRepository.findByUsername(username);				
	}


}
