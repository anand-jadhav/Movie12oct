package com.stackroute.movie.service;

import com.stackroute.movie.domain.Movie;
import com.stackroute.movie.exceptions.MovieAlreadyExistsException;
import com.stackroute.movie.exceptions.MovieNotFoundException;
import com.stackroute.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Primary
@Qualifier("MovieServiceImpl2")
public class MovieServiceImpl2 implements MovieService{
    @Autowired
    MovieRepository movieRepository;
    @Override
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException {
        if(movieRepository.existsById(movie.getImdbId())){
            throw new MovieAlreadyExistsException("Movie Already Exists");
        }
        Movie saveMovie=movieRepository.save(movie);
        if(saveMovie==null){
            throw new MovieAlreadyExistsException("Movie Already Exists");
        }
        return saveMovie;
    }

    @Override
    public List<Movie> getAllMovies(){
        return (List) movieRepository.findAll();
    }

    @Override
    public Movie updateMovie(int id, Movie movie) throws MovieNotFoundException{
        Movie  updateMovie=movieRepository.findById(id).get();
        if(movie==null)
        {
            throw new MovieNotFoundException("Movie not Found");
        }

        movieRepository.save(movie);
        return updateMovie;
    }
    @Override
    public Movie deleteMovie(int id) throws MovieNotFoundException{
        Movie deleteMovie=movieRepository.findById(id).get();
        movieRepository.delete(deleteMovie);
        if(deleteMovie==null)
        {
            throw new MovieNotFoundException("Movie not Found");
        }
        return deleteMovie;
    }
    @Override
    public Movie getMovieByName(String movieTitle) throws MovieNotFoundException {
        Movie movie=movieRepository.findByMovieTitle(movieTitle);
        if(movie==null)
        {
            throw new MovieNotFoundException("Movie not Found");
        }
        return movie;
    }




//    public List<Movie> searchByMovieQuery(String movieTitle){
//        return (List)movieRepository.getMovieByTitle(movieTitle);
//    }
}
