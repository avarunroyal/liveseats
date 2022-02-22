package com.demo.liveseats.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.liveseats.model.LiveSeats;
import com.demo.liveseats.service.LiveSeatsService;

@RestController
public class LiveSeatsController {

	@Autowired
	public LiveSeatsService liveSeatsService;

	@GetMapping(path = "liveSeatsStatus")
	public List<LiveSeats> getLiveSeatsStatus() {
		return liveSeatsService.getAllLiveSeatsStatus();
	}

	@PutMapping(path = "liveSeatsStatus")
	public ResponseEntity<?> updateLiveSeatsStatus(@RequestBody LiveSeats liveSeats) {
		return liveSeatsService.saveLiveSeatStatus(liveSeats);
	}

}
