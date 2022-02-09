package sumdu.edu.ua.radchenko.lab3.model;

import java.time.LocalDate;
import java.util.List;

public class Movie {

    private String movieName;
    private LocalDate release;
    private String runtime;
    private String genre;
    private String directors;
    private String writers;
    private String actors;
    private String plot;
    private List<String> ratings;

    public Movie(String movieName, LocalDate release, String runtime, String genre, String directors,
                  String writers, String actors, String plot, List<String> ratings) {
        this.writers = writers;
        this.movieName = movieName;
        this.release = release;
        this.runtime = runtime;
        this.genre = genre;
        this.directors = directors;
        this.actors = actors;
        this.plot = plot;
        this.ratings = ratings;
    }

    public String getWriters() {
        return writers;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public List<String> getRatings() {
        return ratings;
    }

    public void setRatings(List<String> ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "" +
                "movieName=" + movieName + "\n" +
                "release=" + release + "\n" +
                "runtime=" + runtime + "\n" +
                "genre=" + genre + "\n" +
                "directors=" + directors + "\n" +
                "writers=" + writers + "\n" +
                "actors=" + actors + "\n" +
                "plot=" + plot + "\n" +
                "ratings=" + ratings;
    }
}
