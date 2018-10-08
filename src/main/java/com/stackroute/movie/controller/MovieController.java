package com.stackroute.movie.controller;

import com.stackroute.movie.domain.Movie;
import com.stackroute.movie.exceptions.MovieAlreadyExistsException;
import com.stackroute.movie.exceptions.MovieNotFoundException;
import com.stackroute.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class MovieController {
    @Autowired
    @Qualifier("MovieServiceImpl2")
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("movie")
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {
        ResponseEntity responseEntity;
        try {
            movieService.saveMovie(movie);
            responseEntity = new ResponseEntity<Movie>(movie, HttpStatus.OK);
        } catch (MovieAlreadyExistsException ex) {
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("view")
    public ResponseEntity<?> getAllMovies() {
        return new ResponseEntity<List<Movie>>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable("id") int id, @RequestBody Movie movie) {
        ResponseEntity responseEntity;
        try {
            movieService.updateMovie(id, movie);
            responseEntity = new ResponseEntity<>("Updated", HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable("id") int id) {
        ResponseEntity responseEntity;
        try {
            movieService.deleteMovie(id);
            responseEntity = new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("movies/{movieTitle}")
    public ResponseEntity<?> getMovieByName(@PathVariable("movieTitle") String movieTitle) {
        ResponseEntity responseEntity;
        try {
            Movie movie = movieService.getMovieByName(movieTitle);
            responseEntity = new ResponseEntity<Movie>(movie, HttpStatus.OK);
        } catch (MovieNotFoundException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;
    }
}
//    @GetMapping("/query/{movieTitle}")
//    public ResponseEntity<?> searchByMovieQuery(@PathVariable("movieTitle") String movieTitle){
//        ResponseEntity responseEntity;
//        List<Movie> listMovies = movieService.searchByMovieQuery(movieTitle);
//        responseEntity = new ResponseEntity<List<Movie>>(listMovies,HttpStatus.OK);
//        return responseEntity;
//    }

