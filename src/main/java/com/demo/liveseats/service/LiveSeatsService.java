package com.demo.liveseats.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.demo.liveseats.model.LiveSeats;
import com.demo.liveseats.repository.LiveSeatsRepository;
import com.google.gson.Gson;

@Service
public class LiveSeatsService {

	private final Logger logger = LoggerFactory.getLogger(LiveSeatsService.class);

	private final Producer producer;

	@Autowired
	public LiveSeatsRepository liveSeatsRepository;

	public LiveSeatsService(Producer producer) {
		this.producer = producer;
	}

	public List<LiveSeats> getAllLiveSeatsStatus() {
		return liveSeatsRepository.findAll();
	}

	public ResponseEntity<?> saveLiveSeatStatus(LiveSeats liveSeats) {
		send(new Gson().toJson(liveSeats));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public void send(String liveSeatsStr) {

		ListenableFuture<SendResult<String, String>> listenableFuture = this.producer.sendMessage("LIVE_SEAT_DATA",
				"IN_KEY", liveSeatsStr);

		SendResult<String, String> result;
		try {
			result = listenableFuture.get();
			logger.info(String.format("Produced:\ntopic: %s\noffset: %d\npartition: %d\nvalue size: %d",
					result.getRecordMetadata().topic(), result.getRecordMetadata().offset(),
					result.getRecordMetadata().partition(), result.getRecordMetadata().serializedValueSize()));
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
