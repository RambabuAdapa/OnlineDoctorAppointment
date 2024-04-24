package com.doctor.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.doctor.model.Slot;

public interface SlotRepository extends CrudRepository<Slot, String>{

	List<Slot> findAllByDoctorId(String id);

	List<Slot> findAllByDoctorIdAndDate(String docId, String date);

	List<Slot> findAllByDoctorIdAndDateAndTime(String docId, String date, String time);

	Slot findByDoctorIdAndDateAndTime(String docId, String date, String time);

	List<Slot> findAllByDoctorIdAndDateAndStatus(String doctorId, String date, String string);



}