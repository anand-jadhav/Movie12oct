package com.stackroute.movie.service;

import com.stackroute.movie.domain.Movie;
import com.stackroute.movie.exceptions.MovieAlreadyExistsException;
import com.stackroute.movie.exceptions.MovieNotFoundException;
import java.util.List;
public interface MovieService {
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException;
    public List<Movie> getAllMovies();
    public Movie updateMovie(int id, Movie movie) throws MovieNotFoundException;
    public Movie deleteMovie(int id) throws MovieNotFoundException;
    public Movie getMovieByName(String movieTitle) throws MovieNotFoundException;
//   public List<Movie> searchByMovieQuery(String movieTitle);
//
}
