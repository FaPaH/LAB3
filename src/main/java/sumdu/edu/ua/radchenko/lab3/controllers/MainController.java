package sumdu.edu.ua.radchenko.lab3.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sumdu.edu.ua.radchenko.lab3.model.DocCreator;
import sumdu.edu.ua.radchenko.lab3.model.Movie;
import sumdu.edu.ua.radchenko.lab3.services.MovieService;

@RestController
public class MainController {

    private final static Logger logger = Logger.getLogger(MainController.class);

    private MovieService movieServiceImpl;

    private DocCreator docCreator;

    @Autowired
    public void setDocCreator(DocCreator docCreator) {
        this.docCreator = docCreator;
    }

    @Autowired
    public void setMovieService(MovieService movieServiceImpl) {
        this.movieServiceImpl = movieServiceImpl;
    }

    @RequestMapping("/findByName")
    public ResponseEntity<?> findByName(@RequestParam(value = "movieName", defaultValue = "No value") String movieName){
        Movie movie = movieServiceImpl.getMovieByName(movieName);
        if (movie == null){
            return ResponseEntity.ok("Cant find this film by name: " + movieName);
        }
        logger.info("calling find by name movie:" + movieName);
        return getResponseEntity(movie);
    }

    @RequestMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam(value = "movieId", defaultValue = "No value") String movieId){
        Movie movie = movieServiceImpl.getMovieById(movieId);
        if (movie == null){
            return ResponseEntity.ok("Cant find this film by this id: " + movieId);
        }
        logger.info("calling find by id movie:" + movieId);
        return getResponseEntity(movie);
    }

    private ResponseEntity<?> getResponseEntity(Movie movie) {
        docCreator.generateDoc(movie);
        return ResponseEntity.ok("movieName=" + movie.getMovieName() + "<br>" +
                "release=" + movie.getRelease() + "<br>" +
                "runtime=" + movie.getRuntime() + "<br>" +
                "genre=" + movie.getGenre() + "<br>" +
                "directors=" + movie.getDirectors() + "<br>" +
                "writers=" + movie.getWriters() + "<br>" +
                "actors=" + movie.getActors() + "<br>" +
                "plot=" + movie.getPlot() + "<br>" +
                "ratings=" + movie.getRatings());
    }
}

