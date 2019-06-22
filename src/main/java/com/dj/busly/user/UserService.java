package com.dj.busly.user;

import java.util.List;

import com.dj.busly.packet.Packet.PacketJSON;
import com.dj.busly.user.User.UserJSON;
import com.dj.busly.utils.ResponseMessage;

public interface UserService {

  
	ResponseMessage saveUser(UserJSON userJson);
	
	
	User  getUser(String username);




	
}
