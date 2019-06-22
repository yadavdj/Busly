package com.dj.busly.trip;

import javax.validation.constraints.NotNull;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRespository extends PagingAndSortingRepository<Trip, Long>{

	@NotNull
	Trip findByname(String tripId2);
	
	Trip findBySourceAndDestination(String source, String destination);

	@NotNull
	Trip findByTripId(String tripId2);
	
	
	@NotNull
	Trip findTopByTripId(String tripId);
	
	/* Query query = session.creat */
	

}
