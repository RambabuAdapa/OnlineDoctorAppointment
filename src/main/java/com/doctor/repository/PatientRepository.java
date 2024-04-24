package com.doctor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.doctor.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, String>{

	Optional<Patient> findByUsernameAndPassword(String username, String password);

	List<Patient> findAllByStatusNot(String string);

	Optional<Patient> findByEmail(String email);



}