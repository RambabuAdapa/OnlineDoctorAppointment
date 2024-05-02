package com.doctor.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.doctor.model.Admin;
import com.doctor.model.Doctor;
import com.doctor.model.Login;
import com.doctor.model.Nurse;
import com.doctor.model.Patient;
import com.doctor.repository.AdminRepository;
import com.doctor.repository.DoctorRepository;
import com.doctor.repository.NurseRepository;
import com.doctor.repository.PatientRepository;

@Controller
public class HomeController {

	@Autowired
	AdminRepository admRepo;

	@Autowired
	PatientRepository patRepo;

	@Autowired
	NurseRepository stfRepo;

	@Autowired
	DoctorRepository docRepo;

	@RequestMapping("/")
	public String login(Model model) {
		return "login";
	}
	@RequestMapping("/login")
	public String login1(Model model) {
		return "login";
	}

	@RequestMapping("/home")
	public String home() {
		return "home";
	}

	@RequestMapping("/invalid")
	public String login_invalid(RedirectAttributes rm) {
		return "login";
	}

	@RequestMapping("/logout")
	public String logout(RedirectAttributes rm, HttpServletRequest req) {
		req.getSession().invalidate();
		rm.addFlashAttribute("msg", "Logged Out Successfully");
		return "redirect:/login";
	}

	@PostMapping("/login")
	public String login(Login login, RedirectAttributes rm, HttpServletRequest request, Model model) {
		System.out.println(login.getUsertype());
		if (login.getUsertype().equals("admin")) {
			Optional<Admin> obj = admRepo.findByUsernameAndPassword(login.getUsername(), login.getPassword());

			if (obj.isPresent()) {
				request.getSession().setAttribute("id", "1000");
				request.getSession().setAttribute("usertype", "admin");
				request.getSession().setAttribute("name", "admin");
				return "home";
			} else if (((List<Admin>) admRepo.findAll()).isEmpty()) {
				admRepo.save(new Admin("1000", "admin", "admin@gmail.com", "admin", "admin"));
				rm.addFlashAttribute("msg", "Admin Created: username: admin password: admin");
				return "redirect:/login";

			} else {
				rm.addFlashAttribute("msg", "Invalid Admin Credentials");
				return "redirect:/login";
			}
		} else if (login.getUsertype().equals("patient")) {
			
			Optional<Patient> obj = patRepo.findByUsernameAndPassword(login.getUsername(), login.getPassword());

			if (obj.isPresent()) {
				request.getSession().setAttribute("id", obj.get().getId());
				request.getSession().setAttribute("usertype", "patient");
				request.getSession().setAttribute("name", obj.get().getName());
				return "home";
			} else
			{
				rm.addFlashAttribute("msg", "Invalid User Credentials");
				return "redirect:/invalid";
			}
			
		} else if (login.getUsertype().equals("doctor")) {
			Optional<Doctor> obj = docRepo.findByUsernameAndPassword(login.getUsername(), login.getPassword());

			if (obj.isPresent()) {
				request.getSession().setAttribute("id", obj.get().getId());
				request.getSession().setAttribute("usertype", "doctor");
				request.getSession().setAttribute("name", obj.get().getFirstName());
				
				if(obj.get().isFirstLogin()) 
					return "password_reset";
				else
					return "home";
			} else
			{
				rm.addFlashAttribute("msg", "Invalid Doctor Credentials");
				return "redirect:/invalid";
			}
		} else if (login.getUsertype().equals("nurse")) {
			Optional<Nurse> obj = stfRepo.findByUsernameAndPassword(login.getUsername(), login.getPassword());

			if (obj.isPresent()) {
				request.getSession().setAttribute("id", obj.get().getId());
				request.getSession().setAttribute("usertype", "nurse");
				request.getSession().setAttribute("name", obj.get().getFirstName());
				
				if(obj.get().isFirstLogin()) 
					return "password_reset";
				else
					return "home";
				
			} else {
				rm.addFlashAttribute("msg", "Invalid Nurse Credentials");
				return "redirect:/invalid";
			}
		} else {
			rm.addFlashAttribute("msg", "User Type must be selected");
			return "redirect:/login";
		}
	}

	
	@RequestMapping("/reset")
	public String reset(@RequestParam String password, RedirectAttributes rm, HttpServletRequest req) {
		
		String id = (String) req.getSession().getAttribute("id");
		String utype = (String) req.getSession().getAttribute("usertype");
		if(utype.equals("doctor"))
		{
			Doctor obj = docRepo.findById(id).get();
			obj.setPassword(password);
			obj.setFirstLogin(false);
			docRepo.save(obj);

		}
		else if(utype.equals("nurse"))
		{
			Nurse obj = stfRepo.findById(id).get();
			obj.setPassword(password);
			obj.setFirstLogin(false);
			stfRepo.save(obj);
		}
				
		req.getSession().invalidate();
		rm.addFlashAttribute("msg", "Password Reset Successful");
		return "redirect:/login";
	}

}
