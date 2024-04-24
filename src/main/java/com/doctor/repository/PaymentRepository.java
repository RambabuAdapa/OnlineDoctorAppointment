package com.doctor.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.doctor.model.Payment;



public interface PaymentRepository extends CrudRepository<Payment, String>{

	Payment findByAppointmentId(String id);

	
}