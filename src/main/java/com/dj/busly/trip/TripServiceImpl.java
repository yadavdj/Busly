package com.dj.busly.trip;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.busly.packet.Packet;
import com.dj.busly.packet.PacketRespository;
import com.dj.busly.trip.Trip.TripJSON;
import com.dj.busly.utils.ResponseMessage;

@Service
public class TripServiceImpl implements TripService {

	@Autowired
	private TripRespository repRespository;

	@Autowired
	PacketRespository packetRepository;
	
	
	
	@Override
	public ResponseMessage saveTrip(TripJSON trip) {

		Trip trip2 = new Trip();

		trip2.fromJSON(trip);

		Trip dbTrip = repRespository.save(trip2);

		return new ResponseMessage("Trip created successfully with id: " + dbTrip.getId());

	}

	@Override
	public List<Trip> findTripByDistance(double latitude,double longitude) {

				
		List<Trip> tripList= (List<Trip>) repRespository.findAll();
		List<Trip> sortedTripList = new ArrayList<Trip>();
		double distance = 0;
		
		//System.out.println("List of trip "+tripList.size());
		
		
		for (Trip trip : tripList) {

			try {

				Packet packet = new Packet();

				trip.getId();

				packet = packetRepository.findLastPacketByTrip(trip);

				System.out.println("Source : " + trip.getSource() + " Destination : " + trip.getDestination() + " Name "
						+ trip.getId());

				if (packet != null) {

					packet.getLongitude();
					packet.getLatitude();

					distance = distance(latitude, longitude, packet.getLatitude(), packet.getLongitude());

					System.out.println("Latitude : " + packet.getLatitude() + " Longitude : " + packet.getLongitude()+" Id "+packet.getId());

					/**
					 * distance between 30 km we can show the buses to the users
					 */
					if (distance <= 50 && trip != null) {

					//	List<Trip> trip2 = (List<Trip>) trip;
						
						sortedTripList.add(trip);
						
						System.out.println("Trip is added : Name : "+distance);

					}

				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}		
				
	/***
	 * Demo note :
	 * 
	 *  get the tripList
	 *  
	 *  get the single trip
	 *  
	 *  search the packet of the trip
	 *  
	 *  get latest packet
	 *  
	 *	get latlng of packet
	 * 
	 * 	compare distance with the user location 
	 * 
	 */
		
		/**
		 * Logic to find out the trip against the 30KM
		 * 
		 * retrieve the trip from DB
		 * 
		 * return list of trip;
		 * 
		 */

		/**
		 * RETURN : trip ids
		 */

		return sortedTripList;

	}
	
	

	private static double distance(double lat1, double lon1, double lat2, double lon2) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			dist = dist * 1.609344;
			
			return (dist);
		}
	}
	

}
