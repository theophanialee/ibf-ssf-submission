package sg.edu.nus.iss.ibfb4ssfassessment.service;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;

@Service
public record FileService() {

    // TODO: Task 1
    public List<Movie> readFile(String fileName) throws IOException {

        List<Movie> movieList = new ArrayList<>();

         String jsonContent = new String(Files.readAllBytes(Paths.get(fileName)));
         
        JsonReader jReader = Json.createReader(new StringReader(jsonContent));
        JsonArray a = jReader.readArray();

        for (int i = 0; i < a.size(); i++) {
            JsonObject e = a.get(i).asJsonObject();

            Movie movie = jsonToMovie(e);
           
            Long epochDate = e.getJsonNumber("Released").longValue();
            Date javaDate = new Date(epochDate);

            movie.setReleaseDateF(javaDate);

            movieList.add(movie);
        }

        System.out.println("✅ Task 1: Read movies.json and return List<Movies>");
        System.out.println(">>>>> File content: "+ movieList);

        return movieList;
    }

    public Movie jsonToMovie(JsonObject e){
        Movie movie = new Movie();
           
        movie.setId(e.getInt("Id"));
        movie.setTitle(e.getString("Title"));
        movie.setYear(e.getString("Year"));
        movie.setRated(e.getString("Rated"));
        movie.setRunTime(e.getString("Runtime"));
        movie.setGenre(e.getString("Genre"));
        movie.setDirector(e.getString("Director"));
        movie.setRating(Double.parseDouble(e.get("Rating").toString()));
        movie.setCount(e.getInt("Count"));

        movie.setReleaseDate(e.getJsonNumber("Released").longValue());

        
        return movie;
    }
}
