package com.doctor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "appointment")
public class Appointment {

	@Id
	String id;
	String doctorId;
	String patientId;
	String date;
	String time;
	String reason;
	String status;
	String notes;
	
	
	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Appointment(String id, String doctorId, String patientId, String date, String time, String reason,
			String status, String notes) {
		super();
		this.id = id;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.date = date;
		this.time = time;
		this.reason = reason;
		this.status = status;
		this.notes = notes;
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


	public String getPatientId() {
		return patientId;
	}


	public void setPatientId(String patientId) {
		this.patientId = patientId;
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


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	@Override
	public String toString() {
		return "Appointments [id=" + id + ", doctorId=" + doctorId + ", patientId=" + patientId + ", date=" + date
				+ ", time=" + time + ", reason=" + reason + ", status=" + status + ", notes=" + notes + "]";
	}


}
