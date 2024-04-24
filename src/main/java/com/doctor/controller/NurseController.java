package com.doctor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.doctor.model.Appointment;
import com.doctor.model.Nurse;
import com.doctor.model.Payment;
import com.doctor.model.Review;
import com.doctor.repository.AppointmentRepository;
import com.doctor.repository.DoctorRepository;
import com.doctor.repository.NurseRepository;
import com.doctor.repository.PatientRepository;
import com.doctor.repository.PaymentRepository;
import com.doctor.repository.ReviewRepository;

@RequestMapping("/staff")
@Controller
public class NurseController {

	@Autowired
	NurseRepository repo;

	@Autowired
	AppointmentRepository aptRepo;

	@Autowired
	PaymentRepository payRepo;
	
	@Autowired
	PatientRepository patRepo;

	@Autowired
	DoctorRepository docRepo;

	@Autowired
	ReviewRepository revRepo;

	
	@RequestMapping("/list")
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("datalist", repo.findAll());
        return "staff";
    }

	
	@RequestMapping("/create")
	public String create() {
		return "staff_create";
	}
	
	@RequestMapping("/save")
	public String save(Nurse obj){
		repo.save(obj);		
		return "redirect:/staff/list";
	}
	
	@RequestMapping("/show/{id}")
	public String show(@PathVariable String id, Model model, HttpServletRequest request) {
		model.addAttribute("obj",repo.findById(id).get());
		return "staff_show";
	}
	
	 @RequestMapping("/delete")
	    public String delete(@RequestParam String id) {
	        Optional<Nurse> obj = repo.findById(id);
	        repo.delete(obj.get());

	        return "redirect:/staff/list";
	    }
	    
	    @RequestMapping("/edit/{id}")
	    public String edit(@PathVariable String id, Model model) {
	    	model.addAttribute("obj", repo.findById(id).get());
	        return "staff_edit";
	    }
	    
	    
	    @RequestMapping("/update")
	    public String update(Nurse obj) {
	        repo.save(obj);
	        return "redirect:/staff/show/" + obj.getId();
	    }
	    
	    @RequestMapping("/profile")
	    public String profile(Model model, HttpServletRequest req) {
	        Optional<Nurse> obj = repo.findById(req.getSession().getAttribute("id").toString());
	        model.addAttribute("obj", obj.get());
	        return "staff_profile";
	    }

	    
	    @PostMapping("/profile")
	    public String profile(Nurse obj) {
	        repo.save(obj);
	        return "redirect:/home";
	    }
	
	    
	    @RequestMapping("/payment/{id}")
	    public String payment(@PathVariable String id, Model model, HttpServletRequest req) {
	        Payment obj = payRepo.findByAppointmentId(id);
	        model.addAttribute("obj", obj);
	        return "nurse_payment";
	    }

	    
	    @RequestMapping("/payment/update")
	    public String paymentUpdate(@RequestParam String id, @RequestParam String paymentType, Model model, HttpServletRequest req) {
	        Payment obj = payRepo.findById(id).get();
	        obj.setPaymentType(paymentType);
	        obj.setStatus("Paid");
	        payRepo.save(obj);
	        
	        Appointment apt = aptRepo.findById(obj.getAppointmentId()).get();
	        apt.setNotes("Paid");
	        aptRepo.save(apt);
	        return "redirect:/patient/allappointments";
	    }

				
		@RequestMapping("/payment/list")
	    public String paymentList(Model model, HttpServletRequest request) {
	        model.addAttribute("datalist", payRepo.findAll());
	        return "payment";
	    }

		@RequestMapping("/review/list")
	    public String reviewList(Model model, HttpServletRequest req) {
			List<Review> list;
			if(req.getSession().getAttribute("usertype").toString().equals("doctor"))
				list = revRepo.findAllByDoctorIdOrderByIdDesc(req.getSession().getAttribute("id").toString());
			else
				list = revRepo.findAllByOrderByIdDesc();

			List<Review> newList = new ArrayList<>();
			for (Review obj : list) {
				obj.setDoctorId(docRepo.findById(obj.getDoctorId()).get().getName());
				obj.setPatientId(patRepo.findById(obj.getPatientId()).get().getName());
				newList.add(obj);
			}
			model.addAttribute("datalist", newList);
			return "pat_reviews";
	    }


}
