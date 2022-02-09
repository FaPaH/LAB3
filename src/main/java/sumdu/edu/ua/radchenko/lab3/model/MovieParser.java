package sumdu.edu.ua.radchenko.lab3.model;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieParser {

    private final static Logger logger = Logger.getLogger(MovieParser.class);

    public Movie movieParser(JSONObject JSONMovie){

        String movieName = JSONMovie.getString("Title");
        String release = JSONMovie.getString("Released");
        String runtime = JSONMovie.getString("Runtime");
        String genre = JSONMovie.getString("Genre");
        String directors = JSONMovie.getString("Director");
        String writers = JSONMovie.getString("Writer");
        String actors = JSONMovie.getString("Actors");
        String plot = JSONMovie.getString("Plot");

        JSONArray ratingsJSON = JSONMovie.getJSONArray("Ratings");
        List<String> ratings = new ArrayList<>();
        for (int i=0; i<ratingsJSON.length(); i++) {
            ratings.add(ratingsJSON.get(i).toString());
        }

        LocalDate releaseDate = null;
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
            releaseDate = LocalDate.parse(release, dateTimeFormatter);
        }catch (DateTimeParseException e){
            logger.error("Error in data parse: " + e.getMessage());
        }

        return new Movie(movieName, releaseDate, runtime, genre, directors, writers, actors, plot, ratings);
    }
}
