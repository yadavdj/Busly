package com.dj.busly.trip;

import java.util.List;

import com.dj.busly.trip.Trip.TripJSON;
import com.dj.busly.utils.ResponseMessage;

public interface TripService {
	
	
	
	ResponseMessage saveTrip(TripJSON tripJSON);
	
	
	public List<Trip> findTripByDistance(double latitude,double longitude);

}
