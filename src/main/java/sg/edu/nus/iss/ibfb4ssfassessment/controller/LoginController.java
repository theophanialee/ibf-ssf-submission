package sg.edu.nus.iss.ibfb4ssfassessment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Login;

import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {
    


    // TODO: Task 6
    public String login() {

        return "";
    }

    // TODO: Task 7
    public String processlogin() {
        
        return "";

    }
    

    // For the logout button shown on View 2
    // On logout, session should be cleared
    public String logout() {

        return "";
    }
    
}