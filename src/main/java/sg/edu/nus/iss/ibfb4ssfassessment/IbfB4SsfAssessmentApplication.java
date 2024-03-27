package sg.edu.nus.iss.ibfb4ssfassessment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;
import sg.edu.nus.iss.ibfb4ssfassessment.repo.MoviesRepo;
import sg.edu.nus.iss.ibfb4ssfassessment.service.DatabaseService;
import sg.edu.nus.iss.ibfb4ssfassessment.service.FileService;

// TODO: Put in the necessary code as described in Task 1 & Task 2
@SpringBootApplication
public class IbfB4SsfAssessmentApplication implements CommandLineRunner  {

	@Autowired
	FileService fileSvc;
	public static void main(String[] args) {
		SpringApplication.run(IbfB4SsfAssessmentApplication.class, args);
	}

	@Autowired
	DatabaseService dbSvc;

	@Override
	public void run(String... args) throws Exception {

		List<Movie> movieList = fileSvc.readFile("movies.json");
		
		for (Movie movie : movieList) {
			dbSvc.saveRecord(movie);
		}
		System.out.println("✅ Task 2: saved record in Redis");
	
		System.out.println("✅ Task 3: Events size : " + dbSvc.getNumberOfEvents());

		System.out.println("✅ Task 4: getById eg movieId=12336 : " + dbSvc.getMovieById(12336));

		System.out.println("✅ Task 5: getAllMovies : " + dbSvc.getAllMovies());

	}
}


