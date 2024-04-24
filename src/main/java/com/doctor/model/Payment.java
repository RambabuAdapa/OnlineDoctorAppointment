package com.doctor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "payment")
public class Payment {

	@Id
	String _id;
	String appointmentId;
	String patientId;
	String amount;
	String paymentType;
	String status;
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(String _id, String appointmentId, String patientId, String amount, String paymentType,
			String status) {
		super();
		this._id = _id;
		this.appointmentId = appointmentId;
		this.patientId = patientId;
		this.amount = amount;
		this.paymentType = paymentType;
		this.status = status;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Payment [_id=" + _id + ", appointmentId=" + appointmentId + ", patientId=" + patientId + ", amount="
				+ amount + ", paymentType=" + paymentType + ", status=" + status + "]";
	}

	
	
}
