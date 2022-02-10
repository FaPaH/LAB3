package sumdu.edu.ua.radchenko.lab3.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sumdu.edu.ua.radchenko.lab3.model.DocCreator;
import sumdu.edu.ua.radchenko.lab3.model.docImpl.DocCreatorE;
import sumdu.edu.ua.radchenko.lab3.model.Movie;
import sumdu.edu.ua.radchenko.lab3.model.docImpl.POIDocCreator;
import sumdu.edu.ua.radchenko.lab3.services.MovieService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class MainController {

    private final static Logger logger = Logger.getLogger(MainController.class);

    private MovieService movieServiceImpl;

//    private DocCreatorE docCreatorE;
//    private POIDocCreator poiDocCreator;

    private List<DocCreator> docCreatorList;

    public MainController(List<DocCreator> docCreatorList){
        this.docCreatorList = docCreatorList;
    }

//    @Autowired
//    public void setPoiDocCreator(POIDocCreator poiDocCreator) {
//        this.poiDocCreator = poiDocCreator;
//    }
//
//    @Autowired
//    public void setDocCreator(DocCreatorE docCreatorE) {
//        this.docCreatorE = docCreatorE;
//    }

    @Autowired
    public void setMovieService(MovieService movieServiceImpl) {
        this.movieServiceImpl = movieServiceImpl;
    }

    @RequestMapping("/findByName")
    @Cacheable
    public ResponseEntity<?> findByName(@RequestParam(value = "movieName", defaultValue = "No value") String movieName){
        logger.info("calling find by name movie:" + movieName);

        CompletableFuture<Movie> movie = movieServiceImpl.getMovieByName(movieName);
        if (movie == null){
            return ResponseEntity.ok("Cant find this film by name: " + movieName);
        }

        try {
            return getResponseEntity(movie.get());
        } catch (InterruptedException | ExecutionException e){
            logger.error("Error in executing thread in findById: " + e.getMessage());
        }

        return ResponseEntity.ok("Error with executing");
    }

    @RequestMapping("/findById")
    @Cacheable
    public ResponseEntity<?> findById(@RequestParam(value = "movieId", defaultValue = "No value") String movieId){
        logger.info("calling find by id movie:" + movieId);

        CompletableFuture<Movie> movie = movieServiceImpl.getMovieById(movieId);
        if (movie == null){
            return ResponseEntity.ok("Cant find this film by this id: " + movieId);
        }

        try {
            return getResponseEntity(movie.get());
        } catch (InterruptedException | ExecutionException e){
            logger.error("Error in executing thread in findById: " + e.getMessage());
        }

        return ResponseEntity.ok("Error with executing");
    }

    private ResponseEntity<?> getResponseEntity(Movie movie) {

        for (DocCreator docCreator : docCreatorList) {
            docCreator.generateDoc(movie);
        }
        
//        docCreatorE.generateDoc(movie);
//        poiDocCreator.generateDoc(movie);
        return ResponseEntity.ok("movieName = " + movie.getMovieName() + "<br>" +
                "release = " + movie.getRelease() + "<br>" +
                "runtime = " + movie.getRuntime() + "<br>" +
                "genre = " + movie.getGenre() + "<br>" +
                "directors = " + movie.getDirectors() + "<br>" +
                "writers = " + movie.getWriters() + "<br>" +
                "actors = " + movie.getActors() + "<br>" +
                "plot = " + movie.getPlot() + "<br>" +
                "ratings = " + movie.getRatings());
    }
}

