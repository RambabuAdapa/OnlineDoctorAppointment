package com.doctor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.doctor.model.Appointment;
import com.doctor.model.Doctor;
import com.doctor.model.Payment;
import com.doctor.model.Prescription;
import com.doctor.repository.AppointmentRepository;
import com.doctor.repository.DoctorRepository;
import com.doctor.repository.NurseRepository;
import com.doctor.repository.PatientRepository;
import com.doctor.repository.PaymentRepository;
import com.doctor.repository.PrescriptionRepository;


@RequestMapping("/doctor")
@Controller
public class DoctorController {

	@Autowired
	DoctorRepository repo;
	
	@Autowired
	NurseRepository nurseRepo;
	
	@Autowired
	PatientRepository patRepo;
	
	@Autowired
	AppointmentRepository aptRepo;
	
	@Autowired
	PrescriptionRepository preRepo;
//
//	@Autowired
//	PaymentRepository payRepo;
	
	
	@RequestMapping("/list")
	public String home(Model model, HttpServletRequest request) {
		model.addAttribute("datalist", repo.findAll());
		return "doc";
	}

	@RequestMapping("/create")
	public String create() {
		return "doc_create";
	}

	@RequestMapping("/save")
	public String save(Doctor obj) {
		repo.save(obj);
		return "redirect:/doctor/list";
	}

	@RequestMapping("/show/{id}")
	public String show(@PathVariable String id, Model model, HttpServletRequest request) {
		model.addAttribute("obj", repo.findById(id).get());
		return "doc_show";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam String id) {
		Optional<Doctor> obj = repo.findById(id);
		repo.delete(obj.get());

		return "redirect:/doctor/list";
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable String id, Model model) {
		model.addAttribute("obj", repo.findById(id).get());
		return "doc_edit";
	}

	@RequestMapping("/update")
	public String update(Doctor obj) {
		repo.save(obj);
		return "redirect:/doctor/show/" + obj.getId();
	}

    
    @RequestMapping("/profile")
    public String profile(Model model, HttpServletRequest req) {
        Optional<Doctor> obj = repo.findById(req.getSession().getAttribute("id").toString());
        model.addAttribute("obj", obj.get());
        return "doc_profile";
    }

    
    @PostMapping("/profile")
    public String profile(Doctor obj) {
        repo.save(obj);
        return "redirect:/home";
    }
    
	
	
	@RequestMapping("/appointments")
	public String appointments(Model model, HttpServletRequest req) {
		
		List<Appointment> list = aptRepo.findAllByDoctorIdOrderByIdDesc(req.getSession().getAttribute("id").toString());

//		List<Appointment> newList = list.stream()
//				.filter(apt -> !apt.getStatus().equals("Booked"))
//				.collect(Collectors.toList());
		List<Appointment> newList = new ArrayList<>();
		for(Appointment obj: list) {
			obj.setPatientId(patRepo.findById(obj.getPatientId()).get().getName());
			newList.add(obj);
		}
		model.addAttribute("datalist", newList);
		return "doc_appointments";
	}
	

	@RequestMapping("/appointment/delete/{id}")
	public String appointmentDelete(@PathVariable String id, RedirectAttributes rm) {
		Appointment obj = aptRepo.findById(id).get();
		obj.setStatus("Cancelled");
		aptRepo.save(obj);
		
		rm.addFlashAttribute("msg", "Appointment Cancelled Successfully");
		return "redirect:/doctor/appointments";
	}

	@RequestMapping("/appointment/prescribe/{id}")
	public String prescribe(@PathVariable String id, Model model) {
		Appointment obj = aptRepo.findById(id).get();
		model.addAttribute("obj",obj);
		return "doc_prescribe";
	}

	@PostMapping("/appointment/prescribe")
	public String save(Prescription obj) {
		preRepo.save(obj);
		
		Appointment apt = aptRepo.findById(obj.getAppointmentId()).get();
		apt.setStatus("Completed");
		aptRepo.save(apt);
		
		return "redirect:/doctor/appointments";
	}
	
}
