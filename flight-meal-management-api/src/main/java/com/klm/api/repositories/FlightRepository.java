package com.klm.api.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klm.api.model.Flight;

@Repository
@Transactional
public interface FlightRepository extends JpaRepository<Flight, String> {
    
}
