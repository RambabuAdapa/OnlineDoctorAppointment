package com.doctor.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.doctor.model.SlotPeriod;
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
	public String save(SlotPeriod obj, Model model, HttpServletRequest req){
		String docId = req.getSession().getAttribute("id").toString();
		if(obj.getFromTime()!="" && obj.getToTime()!="")
		{
			List<String> slots = generateTimeIntervals(obj.getFromTime(), obj.getToTime());

			slots.forEach(slot->{
				if(slotRepo.findAllByDoctorIdAndDateAndTime(docId,obj.getDate(), slot).isEmpty())
				{
					Slot slot1 = new Slot();
					slot1.setDoctorId(docId);
					slot1.setStatus("Available");
					slot1.setDate(obj.getDate());
					slot1.setTime(slot);
					slotRepo.save(slot1);
				}else {
					model.addAttribute("msg","This slot already exists");
				}
			});

		}
		model.addAttribute("datalist", slotRepo.findAllByDoctorIdAndDate(docId,obj.getDate()));
		model.addAttribute("date",obj.getDate());
		return "slot_create";
	}

	public static List<String> generateTimeIntervals(String startTime, String endTime) {
		List<String> intervals = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

		LocalTime start = LocalTime.parse(startTime, formatter);
		LocalTime end = LocalTime.parse(endTime, formatter);

		while (start.isBefore(end)) {
			intervals.add(start.format(formatter));
			start = start.plusMinutes(30);
		}

		return intervals;
	}
	
	 @RequestMapping("/delete/{id}")
	    public String delete(@PathVariable String id, Model model) {
		 
		 	
	        Slot obj = slotRepo.findById(id).get();
	        System.out.println(obj);
	        
	        if(obj.getStatus()==null || obj.getStatus().equals("Available") )
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

	
}
