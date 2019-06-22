package com.dj.busly.user;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dj.busly.user.User.UserJSON;
import com.dj.busly.utils.ResponseMessage;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

	/**
	 * Get user by username from database
	 * 
	 * @param user
	 * @return
	 */
	@NotNull(message="there is wrong username...please check it")
	User findByUsername(String user);

	ResponseMessage save(UserJSON userJson);


}
