package com.dj.busly.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.dj.busly.BuslyApplication;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	String username;
	
	String firstName;
	
	String lastName;
	
	String profilePicture;
	
	Date createdOn;
	
	Date modifiedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public static @NotNull User getByUsername(String user) {
		UserRepository userRepo = BuslyApplication.getBean(UserRepository.class);
		
		return userRepo.findByUsername(user);
	}
	
	
	public static class UserJSON{
		
		Long id;
		
		String username;
		
		String firstName;
		
		String lastName;
		
		String profilePicture;
		
		Date createdOn;
		
		Date modifiedOn;

		public Long getId() {
			return id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getProfilePicture() {
			return profilePicture;
		}

		public void setProfilePicture(String profilePicture) {
			this.profilePicture = profilePicture;
		}

		public Date getCreatedOn() {
			return createdOn;
		}

		public void setCreatedOn(Date createdOn) {
			this.createdOn = createdOn;
		}

		public Date getModifiedOn() {
			return modifiedOn;
		}

		public void setModifiedOn(Date modifiedOn) {
			this.modifiedOn = modifiedOn;
		}
		
		
	}
	
	
	public void fromJSON(UserJSON json) {
		
		try {
		System.out.println("Name: "+json.getFirstName()+" lastname "+json.getLastName()+" username : "+json.getUsername()+" profilePicture "+json.getProfilePicture());

		this.username = json.getUsername();
		this.createdOn = new Date();
		this.modifiedOn = new Date();
		this.firstName = json.getFirstName();
		this.lastName = json.getLastName();
		this.profilePicture = json.getProfilePicture();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
