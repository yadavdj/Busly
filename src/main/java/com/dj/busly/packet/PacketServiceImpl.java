package com.dj.busly.packet;

import java.awt.RenderingHints.Key;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.busly.packet.Packet.PacketJSON;
import com.dj.busly.trip.Trip;
import com.dj.busly.utils.ResponseMessage;

@Service
public class PacketServiceImpl implements PacketService {

	@Autowired
	private PacketRespository repository;
	
	@Override
	public ResponseMessage savePacket(PacketJSON json) {
		
		Packet packet = new Packet();
		packet.fromJSON(json);
		
		System.out.println(">TripId : "+json.getTrip().getTripId()+" >Lat: "+json.getLatitude()+">Long : "+json.getLongitude());
		
		repository.save(packet);
		
		
		
		return new ResponseMessage("Packet saved succesfully!");
	}
	

	
	/**
	 * save the packets from list of packets to the DB
	 */
	
	@Override
	public ResponseMessage savePackets(List<PacketJSON> packets) {
		

		for(PacketJSON packetJson : packets) {
			
			savePacket(packetJson);
			
		}
		return new ResponseMessage("Packets saved succesfully!");
	}
	
	
	@Override
	public List<Packet> getPacket(){
		
		List<Packet> packetList = (List<Packet>) repository.findAll();
		return packetList;
		
	}


	
	@Override
	public List<Packet> getPackets(String tripId) {

			
		/**
		 * 1. search tripId in packet table
		 * 
		 * 2. get the latest packets against that trip
		 * 
		 * 3. send it to the consumer
		 * 
		 * get the data in string
		 * 
		 * find the trip object 
		 * 
		 * with that trip object we can find out the packets
		 * 
		 */
		
		Trip trip = Trip.getTripById(tripId);
		
		Long id = trip.getId();
		List<Packet> packetList =  repository.findByTrip(trip);
		
		
		
		System.out.println("Getting the trip object list "+packetList.get(0).getId()+""+packetList.get(0).getLatitude()+""+packetList.get(0).getLongitude()+""+packetList.get(0).getSpeed());
	
		return packetList;
		
		
	}



	@Override
	public Packet getLatestTripPacket(String tripId) {
		// TODO Auto-generated method stub
		
		Trip trip = Trip.getTripById(tripId);
		
		
		
		return repository.findLatestPacket(trip);
	}



	@Override
	public List<Packet> getPacketsWithTime(String tripId1, String time) {
		// TODO Auto-generated method stub
	
		Trip trip = Trip.getTripById(tripId1);
		
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			date=sdf.parse(time);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return 	repository.findPacketByTimeAndTrip(trip.getId(), date);
	}

	 static void fileProcessor(int cipherMode,String key,File inputFile,File outputFile){
		 try {
		       SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
		       Cipher cipher = Cipher.getInstance("AES");
		       cipher.init(cipherMode, (java.security.Key) secretKey);

		       FileInputStream inputStream = new FileInputStream(inputFile);
		       byte[] inputBytes = new byte[(int) inputFile.length()];
		       inputStream.read(inputBytes);

		       byte[] outputBytes = cipher.doFinal(inputBytes);
	               
		       FileOutputStream outputStream = new FileOutputStream(outputFile);
		       outputStream.write(outputBytes);

		       inputStream.close();
		       outputStream.close();

		    } catch (Exception e) {
			e.printStackTrace();
	            }
	     }



	
	
}

