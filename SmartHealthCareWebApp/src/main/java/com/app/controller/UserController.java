package com.app.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.entities.Disease;
import com.app.entities.User;
import com.app.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepo;
	
	
	// method for adding data to response
	
	

	@ModelAttribute
	public void addCommonData(Model model,Principal principal)
	{  String userName=principal.getName();
	System.out.println("username="+userName);
User user=	userRepo.getUserByUsername(userName);
System.out.println(user.getName());
	model.addAttribute("user",user);
		
	}
	
	
	@RequestMapping("/index")
	// dashboard home
public String dashboard( Model model , Principal principal)
{
		 model.addAttribute("title","User dashboard");
	  
	return "normal/user_dashboard"; 
}
	
	
	@GetMapping("/add-disease")
	public String  openaddcontactForm(Model model)
	{  model.addAttribute("title","Add Disease");
		model.addAttribute("disease", new Disease());
		return "normal/add_disease";
	}
	
	//  process add contact form
	
	@PostMapping("/process-disease")
	public String processdisease(@ModelAttribute Disease disease,Principal principal)
{
//		String name=principal.getName();
//		User user=this.userRepo.getUserByUsername(name);
//	
//		disease.setUser(user);
//		user.getDisease().add(disease);		
//		this.userRepo.save(user);
		System.out.println("data"+disease);
	System.out.println("added data");
		return"normal/add_disease";
	}
	
	@GetMapping("/show-diseases")
	public String showdiseases(Model m,Principal principal)
	{ //m.addAttribute("title", "show user  diseases");
//	String user=principal.getName();
//	this.userRepo.getUserByUsername(userName);
//	
//	List<Disease>disease=user.get
		return"normal/show_diseases";
	}
}
