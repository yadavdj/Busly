package com.dj.busly.packet;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.dj.busly.trip.Trip;

@Entity
@Table(name="packet")
public class Packet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	//@Null
	String packetId;
	
	public String getPacketId() {
		return packetId;
	}

	public void setPacketId(String packetId) {
		this.packetId = packetId;
	}

	@NotNull
	@ManyToOne
	Trip trip;
	 
	// String tripId = trip.getTripId();

	Double latitude;

	Double longitude;

	Date timestamp;

	Double speed;

	Date createdOn;
	
	
	/*
	 * String tripId;
	 * 
	 * 
	 * public String getTripId() { return tripId; }
	 * 
	 * public void setTripId(String tripId) { this.tripId = tripId; }
	 */	  
	 
	/*
	 * public long getTripId() { return trip_Id; }
	 * 
	 * public void setTripId(long tripId) { this.tripId = tripId; }
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	
	  public Trip getTrip() { return trip; }
	  
	  public void setTrip(Trip trip) { this.trip = trip; }
	 

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * Json of Packet
	 * 
	 * @author dj
	 *
	 */
	
	public static class PacketJSON {
		
		
		String packetId;
		String tripId;
		Trip trip;
		Double latitude;
		Double longitude;
		String timeStamp;
		Double speed;

		
		public void setPacketId(String packetId) {
			
			this.packetId = packetId;
			
		}
		
		public String getPacketId() {
			
			return packetId;
			
		}
		
		public Trip getTrip() {
			return trip;
		}

		public void setTrip(Trip trip) {
			this.trip = trip;
		}

		public void setTimeStamp(String timeStamp) {
			this.timeStamp = timeStamp;
		}

		public Double getLatitude() {
			return latitude;
		}

		public void setLatitude(Double latitude) {
			this.latitude = latitude;
		}

		public Double getLongitude() {
			return longitude;
		}

		public void setLongitude(Double longitude) {
			this.longitude = longitude;
		}

		public Double getSpeed() {
			return speed;
		}

		public void setSpeed(Double speed) {
			this.speed = speed;
		}

	}

	/**
	 * Take out all values from json and assign to entity object
	 * 
	 * @param json
	 */
	
	public void fromJSON(PacketJSON json) {
		
		this.packetId = json.getPacketId();
		this.createdOn = new Date();
		this.latitude = json.getLatitude();
		this.longitude = json.getLongitude();
		this.speed = json.getSpeed();
		// this.trip_Id = Long.parseLong(json.getTripId());

		/**
		 * 1. we get the trip object but we need to store only trip id and id
		 * automatically get created in mysql db and we have trip id in string format
		 * 
		 * 2. this.trip object store the whole object but just need a tripId and we cant
		 * reach it.
		 */

		this.trip = Trip.getTripById(json.getTrip().getTripId());

		// TODO this below line is wrong...take out date string from json
		// and convert it into date object assign to timestamp
		this.timestamp = new Date();	
		
	}
	
	
}
