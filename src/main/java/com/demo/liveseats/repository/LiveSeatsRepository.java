package com.demo.liveseats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.liveseats.model.LiveSeats;

@Repository
public interface LiveSeatsRepository extends JpaRepository<LiveSeats,Long> {
}
