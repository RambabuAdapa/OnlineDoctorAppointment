package com.doctor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "prescription")

public class Prescription {

	@Id
	String id;
	String doctorId;
	String patientId;
	String appointmentId;
	String prescription;
	
	public Prescription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Prescription(String id, String doctorId, String patientId, String appointmentId, String prescription) {
		super();
		this.id = id;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.appointmentId = appointmentId;
		this.prescription = prescription;
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

	public String getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	@Override
	public String toString() {
		return "Prescription [id=" + id + ", doctorId=" + doctorId + ", patientId=" + patientId + ", appointmentId="
				+ appointmentId + ", prescription=" + prescription + "]";
	}
	
	
}
