package com.dj.busly.trip;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.juli.logging.Log;

import com.dj.busly.BuslyApplication;
import com.dj.busly.user.User;

@Entity
@Table(name = "trip")
public class Trip {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@NotNull(message="Trip id cannot be null")
	String tripId;

	@NotNull(message="User id cannot be null")
	@ManyToOne
	User user;

	@NotNull(message="Name id cannot be null")
	String name;

	@NotNull(message="Source id cannot be null")
	String source;

	@NotNull(message="Destination id cannot be null")
	String destination;

	Date createdOn;

	Date modifiedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
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

	public static @NotNull Trip getTripById(String tripId2) {

		TripRespository repo = BuslyApplication.getBean(TripRespository.class);
		return repo.findByTripId(tripId2);
	}

	public static class TripJSON {

		Long id;
		String tripId;
		String name, source, destination;
		String modifiedOn, createdOn;
		User user;



		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTripId() {
			return tripId;
		}

		public void setTripId(String tripId) {
			this.tripId = tripId;
		}


		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getDestination() {
			return destination;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}

		public String getModifiedOn() {
			return modifiedOn;
		}

		public void setModifiedOn(String modifiedOn) {
			this.modifiedOn = modifiedOn;
		}

		public String getCreatedOn() {
			return createdOn;
		}

		public void setCreatedOn(String createdOn) {
			this.createdOn = createdOn;
		}
		
		

	}

	public void fromJSON(TripJSON trip) {

		try {
		System.out.println("Name: "+trip.getName()+"Source "+trip.getSource()+" Destination : "+trip.getDestination()+" Username "+trip.getUser().getUsername()+" tripId "+trip.getTripId());
		this.tripId = trip.getTripId();
		// this when client having an object this.user = User.getByUsername(trip.getUser().getUsername());
		this.user = User.getByUsername(trip.getUser().getUsername());
		// modified 
		//this.user = User.getUserById(trip.getUser());
		this.createdOn = new Date();
		this.destination = trip.getDestination();
		this.name = trip.getName();
		this.modifiedOn = new Date();
		this.source = trip.getSource();
		
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
