package by.htp.controller;


import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class UserController {

	 
	 @GetMapping("/")
	  public String index(Model model, Principal principal) {
	    model.addAttribute("message", "You are logged in as " + principal.getName());
	    return "index";
	  }
}
