package com.doctor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.doctor.model.Patient;
import com.doctor.model.Prescription;

public interface PrescriptionRepository extends CrudRepository<Prescription, String>{

	List<Prescription> findAllByPatientId(String id);

	Optional<List<Prescription>> findByPatientId(String id);

	Optional<Prescription> findByAppointmentId(String id);



}