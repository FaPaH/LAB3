package sumdu.edu.ua.radchenko.lab3.services;

import sumdu.edu.ua.radchenko.lab3.model.Movie;

import java.util.concurrent.CompletableFuture;

public interface MovieService {

    CompletableFuture<Movie> getMovieByName(String movieName);

    CompletableFuture<Movie> getMovieById(String movieId);
}
