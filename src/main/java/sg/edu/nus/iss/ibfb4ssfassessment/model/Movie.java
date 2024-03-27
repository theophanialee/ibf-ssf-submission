package sg.edu.nus.iss.ibfb4ssfassessment.model;

import java.util.Date;

public class Movie {

    private int id;
    private String title;
    private String year;
    private String rated;
    private String runTime;
    private String genre;
    private String director;
    private double rating;
    private int count;
    private Long releaseDate;
    private Date releaseDateF;

    public Movie() {
    }
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Long releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getReleaseDateF() {
        return releaseDateF;
    }

    public void setReleaseDateF(Date releaseDateF) {
        this.releaseDateF = releaseDateF;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + "\", " +
                "\"title\":\"" + title + "\", " +
                "\"year\":\"" + year + "\", " +
                "\"rated\":\"" + rated + "\", " +
                "\"runTime\":\"" + runTime + "\", " +
                "\"genre\":\"" + genre + "\", " +
                "\"director\":\"" + director + "\", " +
                "\"releaseDate\":\"" + releaseDate + "\", " +
                "\"releaseDateF\":\"" + releaseDateF + "\", " +
                "\"rating\":\"" + rating + "\", " +
                "\"count\":\"" + count + "\"" +
                "}";
    }
    
}
