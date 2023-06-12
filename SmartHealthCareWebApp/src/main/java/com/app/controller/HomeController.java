package com.app.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.entities.User;
import com.app.helper.Message;
import com.app.repository.UserRepository;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	
@RequestMapping("/")
	public String Home(Model model)
	{ model.addAttribute("title","smart contact manager");
		return "home";
	}


@RequestMapping("/about")
public String about(Model model)
{ model.addAttribute("title"," About smart contact manager");
	return "about";
}

@RequestMapping("/signup")
public String signup(Model model)
{ model.addAttribute("title"," Register smart contact manager");
model.addAttribute("user" ,new User());
	return "signup";
}


@RequestMapping(value="/do_register",method=RequestMethod.POST)
public String registeruser( @Valid @ModelAttribute("user") User user ,BindingResult result1, @RequestParam(value="agreement" , defaultValue="false")
boolean agreement, Model model  , HttpSession session)
{ 
	try {
		if(!agreement)
		{
			System.out.println("not allowed terms and condition");
			throw new Exception("not allowed terms nd condition");
		}
		if(result1.hasErrors())
		{ 
			System.out.println("error"+" "+result1.toString());
			model.addAttribute("user",user);
			return "signup";
		}
		user.setRole("ROLE_USER");
		user.setEnabled(true);
		user.setImageurl("baby.png");
		user.setPassword(pe.encode(user.getPassword()));
		
		
		System.out.println("User"+user);
	System.out.println("Agreement"+agreement);
	User result=this.uRepo.save(user);
	model.addAttribute("user", new User());
	session.setAttribute("message",new Message("register successfully","alert-success"));
	return "signup";
	}catch(Exception e)
	{
		e.printStackTrace();
		model.addAttribute("user",user);
		session.setAttribute("message",new Message("stn went  wrong"+" "+e.getMessage(),"alert-danger"));
		return "signup";
	}

	
}


@GetMapping("/signin")
public String CustomLogin( Model model)
{  model.addAttribute("title","login");
	return "login";
}

}
