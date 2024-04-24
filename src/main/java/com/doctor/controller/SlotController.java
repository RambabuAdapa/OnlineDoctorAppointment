package com.doctor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.doctor.model.Appointment;
import com.doctor.model.Payment;
import com.doctor.model.Slot;
import com.doctor.repository.AppointmentRepository;
import com.doctor.repository.DoctorRepository;
import com.doctor.repository.PaymentRepository;
import com.doctor.repository.SlotRepository;

@RequestMapping("/slot")
@Controller
public class SlotController {

	@Autowired
	SlotRepository slotRepo;


	@Autowired
	AppointmentRepository aptRepo;
	
	@Autowired
	DoctorRepository docRepo;

	
	
	@RequestMapping("/create")
	public String create() {
		return "slot_create";
	}
	
	@RequestMapping("/save")
	public String save(Slot obj, Model model, HttpServletRequest req){
		String docId = req.getSession().getAttribute("id").toString();
		if(obj.getTime()!="")
		{
			if(slotRepo.findAllByDoctorIdAndDateAndTime(docId,obj.getDate(), obj.getTime()).isEmpty())
			{
			obj.setDoctorId(docId);
			slotRepo.save(obj);		
			}
			else
				model.addAttribute("msg","This slot already exists");
		}
		model.addAttribute("datalist", slotRepo.findAllByDoctorIdAndDate(docId,obj.getDate()));
		model.addAttribute("date",obj.getDate());
		return "slot_create";
	}
	
	 @RequestMapping("/delete/{id}")
	    public String delete(@PathVariable String id, Model model) {
		 
		 	
	        Slot obj = slotRepo.findById(id).get();
	        System.out.println(obj);
	        
	        if(obj.getStatus().equals("Available"))
	        	slotRepo.delete(obj);
	        else
	        	model.addAttribute("msg","Appointment fixed slots cant be deleted, please cancel the appointment");
	        
	        model.addAttribute("datalist", slotRepo.findAllByDoctorIdAndDate(obj.getDoctorId(),obj.getDate()));
			model.addAttribute("date",obj.getDate());
  
			return "slot_create";
	    }
	    
		
		
		@RequestMapping("/list/available")
	    public String availableSlots(@RequestParam String doctorId, @RequestParam String date, Model model, HttpServletRequest request) {
	        model.addAttribute("datalist", slotRepo.findAllByDoctorIdAndDateAndStatus(doctorId,date,"Available"));
	        model.addAttribute("obj", docRepo.findById(doctorId).get());
			model.addAttribute("date", date);

	        return "pat_appointment_slots";
	    }
		
	
		/*	
		@RequestMapping("/list")
	    public String home(@RequestParam String doctorId, @RequestParam String date, Model model, HttpServletRequest request) {
	        model.addAttribute("datalist", slotRepo.findAllByDoctorIdAndDate(doctorId,date));
	        model.addAttribute("obj", docRepo.findById(doctorId).get());
			model.addAttribute("date", date);

	        return "pat_appointment_slots";
	    }



	@RequestMapping("/show/{id}")
	public String show(@PathVariable String id, Model model, HttpServletRequest request) {
		model.addAttribute("obj",slotRepo.findById(id).get());
		return "staff_show";
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
*/

	
}
