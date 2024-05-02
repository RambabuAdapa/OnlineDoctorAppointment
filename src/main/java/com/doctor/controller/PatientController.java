package com.doctor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.doctor.model.*;
import com.doctor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/patient")
@Controller
public class PatientController {

	@Autowired
	AppointmentRepository aptRepo;

	@Autowired
	PatientRepository repo;

	@Autowired
	NurseRepository staffRepo;

	@Autowired
	DoctorRepository docRepo;

	@Autowired
	SlotRepository slotRepo;

	@Autowired
	PrescriptionRepository presRepo;

	@Autowired
	ReviewRepository revRepo;

	@Autowired
	PaymentRepository payRepo;

	@RequestMapping("/list")
	public String home(Model model, HttpServletRequest request) {
		model.addAttribute("datalist", repo.findAll());
		return "pat";
	}

	@RequestMapping("/create")
	public String create() {
		return "pat_create";
	}

	@RequestMapping("/save")
	public String save(Patient obj) {
		repo.save(obj);
		return "redirect:/patient/list";
	}

	@RequestMapping("/signup")
	public String signup() {
		return "pat_signup";
	}

	@PostMapping("/signup")
	public String signup(Patient obj, RedirectAttributes rm) {
		if (repo.findByEmail(obj.getEmail()).isPresent()) {
			String msg = "This patient with " + obj.getEmail() + " is already registered";
			rm.addFlashAttribute("msg", msg);
			return "login";
		} else {
			repo.save(obj);
			return "redirect:/";
		}
	}

	@RequestMapping("/show/{id}")
	public String show(@PathVariable String id, Model model, HttpServletRequest request) {
		model.addAttribute("obj", repo.findById(id).get());
		return "pat_show";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam String id) {
		Optional<Patient> obj = repo.findById(id);
		repo.delete(obj.get());

		return "redirect:/patient/list";
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable String id, Model model) {
		model.addAttribute("obj", repo.findById(id).get());
		return "pat_edit";
	}

	@RequestMapping("/update")
	public String update(Patient obj) {
		repo.save(obj);
		return "redirect:/patient/show/" + obj.getId();
	}

	@RequestMapping("/profile")
	public String profile(Model model, HttpServletRequest req) {
		Optional<Patient> obj = repo.findById(req.getSession().getAttribute("id").toString());
		model.addAttribute("obj", obj.get());
		return "pat_profile";
	}

	@PostMapping("/profile")
	public String profile(Patient obj) {
		repo.save(obj);
		return "redirect:/home";
	}

	@RequestMapping("/appointment")
	public String appointment(Model model) {
		model.addAttribute("datalist", docRepo.findAll());
		return "pat_appointment";
	}

	@PostMapping("/appointment")
	public String signup(Appointment obj, RedirectAttributes rm, HttpServletRequest req) {
		obj.setPatientId(req.getSession().getAttribute("id").toString());
		obj.setStatus("Booked");
		obj.setNotes("UnPaid");
		Appointment appointment = aptRepo.save(obj);

		Payment pmt = new Payment();
		pmt.setAmount("50$");
		pmt.setAppointmentId(appointment.getId());
		pmt.setPatientId(obj.getPatientId());
		pmt.setPatientName(repo.findById(obj.getPatientId()).get().getName());
		pmt.setStatus("UnPaid");
		payRepo.save(pmt);

		Slot slot = slotRepo.findByDoctorIdAndDateAndTime(obj.getDoctorId(), obj.getDate(), obj.getTime());
		slot.setStatus("Booked");
		slotRepo.save(slot);

		rm.addFlashAttribute("msg", "Appointment Fixed Successfully");
		return "redirect:/patient/myappointments";
	}

	@RequestMapping("/appointment/delete/{id}")
	public String appointmentDelete(@PathVariable String id, RedirectAttributes rm) {
		Appointment obj = aptRepo.findById(id).get();

		Slot slot = slotRepo.findByDoctorIdAndDateAndTime(obj.getDoctorId(), obj.getDate(), obj.getTime());
		slot.setStatus("Available");
		slotRepo.save(slot);

		aptRepo.delete(obj);

		rm.addFlashAttribute("msg", "Appointment Deleted Successfully");
		return "redirect:/patient/myappointments";
	}

	@RequestMapping("/myappointments")
	public String myAppointments(Model model, HttpServletRequest req) {
		List<Appointment> list = aptRepo.findAllByPatientIdOrderByIdDesc(req.getSession().getAttribute("id").toString());

		List<Appointment> newList = new ArrayList<>();
		for (Appointment obj : list) {
			obj.setDoctorId(docRepo.findById(obj.getDoctorId()).get().getFirstName());
			newList.add(obj);
		}
		model.addAttribute("datalist", newList);
		return "pat_appointments";
	}

	@RequestMapping("/allappointments")
	public String allAppointments(Model model, HttpServletRequest req) {
		List<Appointment> list = (List<Appointment>) aptRepo.findAll();

		List<Appointment> newList = new ArrayList<>();
		for (Appointment obj : list) {
			obj.setDoctorId(docRepo.findById(obj.getDoctorId()).get().getFirstName());
			obj.setPatientId(repo.findById(obj.getPatientId()).get().getName());
			newList.add(obj);
		}
		model.addAttribute("datalist", newList);
		return "pat_appointments_all";
	}

	@RequestMapping("/appointment/prescribtion/{id}")
	public String Prescription(@PathVariable String id, Model model) {
		Prescription obj = presRepo.findByAppointmentId(id).get();

		model.addAttribute("prescription", obj);
		model.addAttribute("doctor", docRepo.findById(obj.getDoctorId()).get());
		model.addAttribute("patient", repo.findById(obj.getPatientId()).get());
		model.addAttribute("appointment", aptRepo.findById(obj.getAppointmentId()).get());

		return "pat_prescription";
	}

	@RequestMapping("/review/{id}")
	public String addReview(@PathVariable String id, Model model) {

		Prescription obj = presRepo.findByAppointmentId(id).get();

		model.addAttribute("prescription", obj);
		model.addAttribute("doctor", docRepo.findById(obj.getDoctorId()).get());
		model.addAttribute("patient", repo.findById(obj.getPatientId()).get());
		model.addAttribute("appointment", aptRepo.findById(obj.getAppointmentId()).get());
		
		Optional<Review> opt = revRepo.findByAppointmentId(obj.getAppointmentId());
		Review rev;
		if(opt.isEmpty())
		{
			rev = new Review();
			rev.setRating(1);
			rev.setReview("");
		}
		else
			rev = opt.get();
		
		model.addAttribute("obj", rev);

		return "pat_review_add";
	}

	@RequestMapping("/review/save")
	public String saveReview(Review obj) {
		Optional<Review> opt = revRepo.findByAppointmentId(obj.getAppointmentId());
		if(opt.isEmpty())
		{
			obj.setId(null);
		}
		revRepo.save(obj);
		return "redirect:/patient/myappointments";
	}

}
