package com.dj.busly.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dj.busly.BuslyApplication;
import com.dj.busly.user.User.UserJSON;
import com.dj.busly.utils.ResponseMessage;

@RestController
@RequestMapping("/user")
public class UserController {
	
	
	private UserService userService = BuslyApplication.getBean(UserService.class);
	
	@RequestMapping(value="/saveUser",method=RequestMethod.POST)
	public ResponseMessage saveUser(@RequestBody UserJSON userJson) {
		
		return 	userService.saveUser(userJson);		
	}
	
	
	@RequestMapping(value="/getUser/{username}",method=RequestMethod.GET)
	public User getUser(@PathVariable ("username") String username) {
		
		return userService.getUser(username);
		
	}
	
	
	
	
	

}
