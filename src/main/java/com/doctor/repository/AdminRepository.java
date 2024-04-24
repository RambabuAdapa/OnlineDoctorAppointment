package com.doctor.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.doctor.model.Admin;



public interface AdminRepository extends CrudRepository<Admin, String>{


	Optional<Admin> findByUsernameAndPassword(String username, String password);


}