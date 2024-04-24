package com.doctor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "review")
public class Review {
	
	@Id
	String id;
	String appointmentId;
	String patientId;
	String doctorId;
	int rating;
	String review;
	
	public Review() {
		super();
	}

	public Review(String id, String appointmentId, String patientId, String doctorId, int rating, String review) {
		super();
		this.id = id;
		this.appointmentId = appointmentId;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.rating = rating;
		this.review = review;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", appointmentId=" + appointmentId + ", patientId=" + patientId + ", doctorId="
				+ doctorId + ", rating=" + rating + ", review=" + review + "]";
	}

	

}
