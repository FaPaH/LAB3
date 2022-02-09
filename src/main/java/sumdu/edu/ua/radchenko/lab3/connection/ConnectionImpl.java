package sumdu.edu.ua.radchenko.lab3.connection;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sumdu.edu.ua.radchenko.lab3.model.Movie;
import sumdu.edu.ua.radchenko.lab3.model.MovieParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
@Scope
@PropertySource("classpath:application.properties")
public class ConnectionImpl implements Connection{

    private final static Logger logger = Logger.getLogger(ConnectionImpl.class);

    private MovieParser movieParser;

    private static String urlString;

    @Autowired
    public void setUrl(@Value("${omdb.url}") String urlString) {
        this.urlString = urlString;
    }

    @Autowired
    public void setMovieParser(MovieParser movieParser) {
        this.movieParser = movieParser;
    }

    public HttpURLConnection getConnection(URL url){
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
        } catch (IOException e){
            logger.error("Error in getConnection: " + e.getMessage());
        }
        return connection;
    }

    private String parseStream(InputStream stream){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder response = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                response.append(line);
                response.append("\r");
            }
            reader.close();
            return response.toString();
        } catch (IOException e){
            logger.error("Error in parseStream: " + e.getMessage());
        }
        return null;
    }


    @Override
    public Movie getMovieByName(String movieName) {
        try {
            URL url = new URL(urlString + "t=" + movieName);
            HttpURLConnection connection = getConnection(url);
            JSONObject jsonObject = new JSONObject(parseStream(connection.getInputStream()));
            return movieParser.movieParser(jsonObject);

        } catch (IOException e){
            logger.error("Error in getMovieByName: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Movie getMovieById(String movieId) {
        try {
            URL url = new URL(urlString + "i=" + movieId);
            HttpURLConnection connection = getConnection(url);
            JSONObject jsonObject = new JSONObject(parseStream(connection.getInputStream()));
            return movieParser.movieParser(jsonObject);
        } catch (IOException e){
            logger.error("Error in getMovieById: " + e.getMessage());
        }
        return null;
    }
}
