package sumdu.edu.ua.radchenko.lab3.connection;

import org.json.JSONObject;
import sumdu.edu.ua.radchenko.lab3.model.Movie;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public interface Connection {

//    JSONObject parseStream(InputStream stream);

//    HttpURLConnection getConnection(URL url);

    Movie getMovieByName(String movieName);

    Movie getMovieById(String movieId);
}
