package com.doctor.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.doctor.model.Nurse;

public interface NurseRepository extends CrudRepository<Nurse, String>{

	Optional<Nurse> findByUsernameAndPassword(String username, String password);



}