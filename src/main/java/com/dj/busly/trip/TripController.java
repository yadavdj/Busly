package com.dj.busly.trip;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dj.busly.trip.Trip.TripJSON;
import com.dj.busly.utils.ResponseMessage;

@RestController
@RequestMapping("/trip")
public class TripController {

	@Autowired
	private TripService tripService;

	@RequestMapping(value = "/saveTrip", method = RequestMethod.POST)
	public ResponseMessage saveTrip(@RequestBody TripJSON trip) {
		System.out.println("saveTrip");
		return tripService.saveTrip(trip);

	}

	/**
	 * CONSUMER TRIPS API DETAILS
	 * 
	 * MethodType: GET
	 * 
	 * Endpoint: /trip/getbylocationanddistance/{latitude}/{longitude}/{distance}
	 * 
	 * Respones: List<Trip>
	 * 
	 */

	@RequestMapping(value = "/getByLocationAndDistance/{latitude}/{longitude}", method = RequestMethod.GET)
	public List<Trip> getTrips(@PathVariable("latitude") String latitudeString,
			@PathVariable("longitude") String longitudeString) {

		System.out.println("getTrips");

		double latitude = Double.parseDouble(latitudeString);
		double longitude = Double.parseDouble(longitudeString);

		return tripService.findTripByDistance(latitude, longitude);

	}
	  
	  @RequestMapping(value="/getTripList",method=RequestMethod.GET)
	  public List<Trip> getTrip(){
	  
	  return tripService.findTripByDistance(17.297430,74.361600);
	  
	  }
	 
}
