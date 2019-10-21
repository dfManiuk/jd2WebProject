package by.htp.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import by.htp.entity.User;
import by.htp.service.UserService;

@Controller
//@RequestMapping("/")
//@SessionAttributes("roles")
public class UserController {

//	 @Autowired
//	 AuthenticationTrustResolver authenticationTrustResolver;
	 	
	 @Autowired
	 UserDetailsService userDetailsService;
	 
//	 @Autowired
//	 private UserService userService;	 
//	    
	 
	 @GetMapping("/loginAction")
	  public String index(Model model, Principal principal) {
	    model.addAttribute("message", "You are logged in as " + principal.getName());
	    return "index";
	  }
//	 @RequestMapping(value = "/login", method = RequestMethod.GET)
//	   public String loginPage(Model model ) {
//	        
//	       return "/check-user";
//	   }
//	 @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
//	   public String checrPage(Model model ) {
//	        
//	       return "userlist";
	 
//	 @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
//	    public String listUsers(ModelMap model) {
//		 System.out.println("LIIIIST!");
//	        List<User> users = userService.findAllUsers();
//	        model.addAttribute("users", users);
//	       model.addAttribute("loggedinuser", getPrincipal());
//	        return "userslist";
//	    }
//	 
//	 @RequestMapping(value = "/login", method = RequestMethod.GET)
//	   public String loginPage( ) {
//		 if (isCurrentAuthenticationAnonymous()) {
//			 System.out.println("LOOOOOGING!");
//	            return "login";
//	        } else {
//	            return "check-user";  
//	        }
//	   }
//	 private boolean isCurrentAuthenticationAnonymous() {
//	        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	        return authenticationTrustResolver.isAnonymous(authentication);
//	    }
//	 private String getPrincipal(){
//	        String userName = null;
//	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//	 
//	        if (principal instanceof UserDetails) {
//	            userName = ((UserDetails)principal).getUsername();
//	        } else {
//	            userName = principal.toString();
//	        }
//	        return userName;
//	    }
}


