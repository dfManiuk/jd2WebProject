package by.htp.controller;


import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.htp.entity.User;
import by.htp.service.UserService;

@Controller
@RequestMapping(value = "/")
public class UserController {

	 @Autowired
    private UserService userService;
	 
	 @GetMapping("/")
	  public String index(Model model, Principal principal) {
	    model.addAttribute("message", "You are logged in as " + principal.getName());
	    return "index";
	  }

    @RequestMapping(value = "/check-user", method = RequestMethod.GET)
    public void index(@ModelAttribute("user") User user, Model model) { 
    	
    		//List<User> list= userService.allUsers();
    	//	System.out.println(list.toString());
    		
           user = userService.getUser(user.getName(), user.getPassword());
     
          
           
   			model.addAttribute("user", user);

    }
    @RequestMapping(value = "/registr-user", method = RequestMethod.GET)
    public void registration() { 
 
    }
}
