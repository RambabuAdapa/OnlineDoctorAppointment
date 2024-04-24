package com.doctor.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.doctor.model.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, String>{


	Optional<Doctor> findByUsernameAndPassword(String username, String password);



}