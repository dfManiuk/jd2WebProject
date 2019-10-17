package by.htp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.htp.entity.User;
import by.htp.service.UserService;

@Controller
@RequestMapping(value = "/")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/check-user", method = RequestMethod.GET)
    public void index(@ModelAttribute("user") User user, Model model) { 
    	 
           user = userService.getUser(user.getLogin(), user.getPassword());
     
   			model.addAttribute("user", user);

    }
    @RequestMapping(value = "/registr-user", method = RequestMethod.GET)
    public void registration() { 
 
    }
}
