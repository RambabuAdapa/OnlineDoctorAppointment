package com.doctor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "timeslot")
public class Slot {

	@Id
	String id;
	String doctorId;
	String date;
	String time;
	String status;

	
	public Slot() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Slot(String id, String doctorId, String date, String time, String status) {
		super();
		this.id = id;
		this.doctorId = doctorId;
		this.date = date;
		this.time = time;
		this.status = status;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getDoctorId() {
		return doctorId;
	}


	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Slot [id=" + id + ", doctorId=" + doctorId + ", date=" + date + ", time=" + time + ", status=" + status
				+ "]";
	}


}
