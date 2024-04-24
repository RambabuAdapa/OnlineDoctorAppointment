package com.doctor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.doctor.model.Review;



public interface ReviewRepository extends CrudRepository<Review, String>{

	Optional<Review> findById(String id);
	Optional<Review> findByAppointmentId(String id);
	List<Review> findByDoctorId(String id);
	List<Review> findByPatientId(String id);
	List<Review> findAll();
	List<Review> findAllByOrderByIdDesc();
	List<Review> findAllByDoctorIdOrderByIdDesc(String string);

}