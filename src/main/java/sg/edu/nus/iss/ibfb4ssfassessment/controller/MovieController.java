package sg.edu.nus.iss.ibfb4ssfassessment.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;
import sg.edu.nus.iss.ibfb4ssfassessment.service.DatabaseService;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MovieController {
    @Autowired
    DatabaseService dbSvc;

    // TODO: Task 8
    @GetMapping("/list")
      public String displayMovies(HttpSession sess, Model model) {

        if(sess == null || sess.getAttribute("user") == null) {
            return "redirect:/login";
        }
        Object user = sess.getAttribute("user");
        model.addAttribute("user", user);
    
         List<Movie> movieList = dbSvc.getAllMovies();
         model.addAttribute("movieList", movieList);
         System.out.println(movieList);

        return "listing";
    }
    

    // TODO: Task 9
    @GetMapping("/list/book/{id}")
    public String bookMovie(Model model, HttpSession sess, @PathVariable("id") String movieId) throws ParseException {

        if(sess == null || sess.getAttribute("user") == null) {
            return "redirect:/login";
        }

        System.out.println(sess.getAttribute("birthDate"));
        String bdStr = sess.getAttribute("birthDate").toString();
        SimpleDateFormat dueDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        Date birthDate = dueDateFormat.parse(bdStr);
        int age = countAge(birthDate);
        
        Movie movie = dbSvc.getMovieById(Integer.parseInt(movieId));
      
        String rating = movie.getRated();

        if (rating.equals("PG-13") && age < 13){
            return "underaged";
        }

        if (rating.equals("R") && age < 18){
            return "underaged";
        }

        // Add booking
        System.out.println("Booking for:  " + movie);
        dbSvc.addCount(movie);

        model.addAttribute("movietitle", movie.getTitle());

        return "successbooking";
    }



    public int countAge(Date birthDate) {
    
       LocalDate bdayLocal = birthDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
                System.out.println("Bday local date: " + bdayLocal);
        LocalDate currentDate = LocalDate.now();
        System.out.println("Bday local date: " + currentDate);

        Period period = Period.between(currentDate, bdayLocal);
        int age = Math.abs(period.getYears());

        return age;
    }
}
