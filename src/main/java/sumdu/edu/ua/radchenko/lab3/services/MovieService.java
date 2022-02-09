package sumdu.edu.ua.radchenko.lab3.services;

import sumdu.edu.ua.radchenko.lab3.model.Movie;

public interface MovieService {

    Movie getMovieByName(String movieName);

    Movie getMovieById(String movieId);
}
