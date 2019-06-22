package com.dj.busly.packet;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dj.busly.trip.Trip;

@Repository
public interface PacketRespository extends JpaRepository<Packet, Long>{
	
	List<Packet> findByTrip(Trip trip);

	//Packet findBycreatedOn(Trip trip);
	
	
	Packet findTopByTrip(Trip trip);
	
	//Packet findLastByTrip(Trip trip);
	/**
	 * @return
	 * 
	 * put the query here of the getting packets from the Trip_id 
	 * 
	 */
	

	@Query(value="select *from packet p where p.trip_id =?1 ORDER BY p.id  DESC LIMIT 1",nativeQuery=true)
	Packet findLatestPacket(Trip trip);
	
	@Query(value="select *from packet p where p.trip_id =?1 ORDER BY p.id  DESC LIMIT 1",nativeQuery=true)
	Packet findLastPacketByTrip(Trip trip1);
	
	@Query(value="select *from packet p where p.trip_id=?1 AND p.created_on>?2",nativeQuery=true)
	List<Packet> findPacketByTimeAndTrip(long trip_id,Date date);

	
}
