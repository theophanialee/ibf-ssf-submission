package sg.edu.nus.iss.ibfb4ssfassessment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Login;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }
   
    // TODO: Task 6
    @GetMapping("/login")
    public String login(Model model) {
        Login user = new Login();
        model.addAttribute("user", user);
        
        return "login";
    }

    // TODO: Task 7
    @PostMapping("/login")
    public String processlogin(HttpSession sess, @Valid @ModelAttribute("user") Login user, BindingResult result) {
        if(result.hasErrors()){
            return "login";
        }
        // System.out.println(sess);
        sess.setAttribute("user", user);
        sess.setAttribute("email", user.getEmail());
        sess.setAttribute("birthDate", user.getBirthDate());

        return "successlogin";
    }
  
    
    // For the logout button shown on View 2
    // On logout, session should be cleared
    @GetMapping("/logout")
    public String logout(HttpSession sess) {

        sess.invalidate();
        System.out.flush();

        // System.out.println(sess);
        return "redirect:/";
    }
    
    
      
}
