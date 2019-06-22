package com.dj.busly.packet;

import java.util.List;

import com.dj.busly.packet.Packet.PacketJSON;
import com.dj.busly.utils.ResponseMessage;

public interface PacketService {

	/**
	 * Create packet and add it into json
	 * 
	 * @param json
	 * @return
	 */
	ResponseMessage savePacket(PacketJSON json);
	
	
	/**
	 * Save multiple packets
	 * 
	 * @param packets
	 * @return
	 */
	ResponseMessage savePackets(List<PacketJSON> packets);
	
	
	/**
	 * Get all packets for given trip id
	 * 
	 * @param tripId
	 * @return
	 */
	List<Packet> getPackets(String tripId);


	List<Packet> getPacket();
	
	Packet getLatestTripPacket(String tripId);
	
	
	List<Packet> getPacketsWithTime(String tripId,String time);
	
}
