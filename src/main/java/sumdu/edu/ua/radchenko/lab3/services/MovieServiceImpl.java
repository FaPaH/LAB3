package sumdu.edu.ua.radchenko.lab3.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sumdu.edu.ua.radchenko.lab3.connection.ConnectionImpl;
import sumdu.edu.ua.radchenko.lab3.model.Movie;

@Service
public class MovieServiceImpl implements MovieService{

    private final static Logger logger = Logger.getLogger(MovieServiceImpl.class);

    private ConnectionImpl connection;

    @Autowired
    public void setConnection(ConnectionImpl connection) {
        this.connection = connection;
    }

    public Movie getMovieByName(String movieName) {
        logger.info("Call getMovieByName for: " + movieName);
        return connection.getMovieByName(movieName);
    }

    public Movie getMovieById(String movieId) {
        logger.info("Call getMovieByName for: " + movieId);
        return connection.getMovieById(movieId);
    }
}