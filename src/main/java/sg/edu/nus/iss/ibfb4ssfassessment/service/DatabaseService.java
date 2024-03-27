package sg.edu.nus.iss.ibfb4ssfassessment.service;


import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;
import sg.edu.nus.iss.ibfb4ssfassessment.repo.MoviesRepo;

@Service
public class DatabaseService {

    @Autowired
    MoviesRepo moviesRepo;
@Autowired
FileService fileSvc;

    // TODO: Task 2 (Save to Redis Map)
    public void saveRecord(Movie movie) {
        moviesRepo.saveMovie(movie.getId(), movie.toString());
    }


    // TODO: Task 3 (Map or List - comment where necesary)
    public long getNumberOfEvents() {
        long eventsCount = 0;
        Map<String,String> moviesMap = moviesRepo.getAllMovies();

        for (Map.Entry<String,String> entry : moviesMap.entrySet()) {
            eventsCount++;
           }

         return eventsCount;
    }

    // TODO: Task 4 (Map)
    public Movie getMovieById(Integer movieId) {
        String movieStr = moviesRepo.getMovieById(movieId);
        Movie movie = strToMovie(movieStr);

        System.out.println(movie);
        return movie;
    }

    // TODO: Task 5
    public List<Movie> getAllMovies() {
        Map<String,String> moviesMap = moviesRepo.getAllMovies();

        List<String> movieListOfStr = new ArrayList<>();

        for (Map.Entry<String,String> entry : moviesMap.entrySet()) {
            movieListOfStr.add(entry.getValue());
           }

        List<Movie> movieList = new ArrayList<>();

        for (String movieStr : movieListOfStr) {
            movieList.add(strToMovie(movieStr));
        }
        System.out.println(movieList);
        return movieList;
    }

     // TODO: Task 9 (Add count when booked)
     public void addCount(Movie movie){
        int currentCount = movie.getCount();
        movie.setCount(currentCount+1);
        moviesRepo.updateMovie(movie.getId(), movie.toString());
     }

    public Movie strToMovie(String movieStr) {
        // System.out.println("from redis" + movieStr);
        JsonReader jReader = Json.createReader(new StringReader(movieStr));
        JsonObject e = jReader.readObject();
   
        Movie movie = new Movie();

        movie.setId(Integer.parseInt(e.getString("id")));
        movie.setTitle(e.getString("title"));
        movie.setYear(e.getString("year"));
        movie.setRated(e.getString("rated"));
        movie.setRunTime(e.getString("runTime"));
        movie.setGenre(e.getString("genre"));
        movie.setDirector(e.getString("director"));
        movie.setRating(Double.parseDouble(e.getString("rating")));
        movie.setCount(Integer.parseInt(e.getString("count")));

        movie.setReleaseDate(Long.parseLong(e.getString("releaseDate")));

        Long epochDate = Long.parseLong(e.getString("releaseDate"));
        Date javaDate = new Date(epochDate);

        movie.setReleaseDateF(javaDate);

        return movie;

    }

}
