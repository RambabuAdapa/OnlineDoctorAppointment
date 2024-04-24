package com.doctor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.doctor.model.Appointment;

public interface AppointmentRepository extends CrudRepository<Appointment, String>{

	List<Appointment> findAllByPatientId(String id);

	Optional<List<Appointment>> findByPatientId(String id);

	List<Appointment> findAllByPatientIdOrderByIdDesc(String string);

	List<Appointment> findAllByDoctorIdOrderByIdDesc(String string);



}