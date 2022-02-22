package com.demo.liveseats.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "live_seats")
@IdClass(LiveSeatsId.class)
public class LiveSeats implements Serializable {

	@Id
	@Column(name = "building_id")
	private Long buildingId;

	@Id
	@Column(name = "floor_id")
	private Long floorId;

	@Id
	@Column(name = "seat_id")
	private String seatId;

	@Column(name = "occupancy_status")
	private String occupancyStatus;

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public Long getFloorId() {
		return floorId;
	}

	public void setFloorId(Long floorId) {
		this.floorId = floorId;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getOccupancyStatus() {
		return occupancyStatus;
	}

	public void setOccupancyStatus(String occupancyStatus) {
		this.occupancyStatus = occupancyStatus;
	}

}
