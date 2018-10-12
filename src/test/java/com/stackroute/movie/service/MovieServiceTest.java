package com.stackroute.movie.service;


import com.stackroute.movie.domain.Movie;
import com.stackroute.movie.exceptions.MovieAlreadyExistsException;
import com.stackroute.movie.exceptions.MovieNotFoundException;
import com.stackroute.movie.repository.MovieRepository;
import com.stackroute.movie.service.MovieServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class MovieServiceTest {
    Movie movie;
    //Create a mock for UserRepository
    @Mock//test double
            MovieRepository movieRepository;
    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    MovieServiceImpl movieService;
    List<Movie> list= null;

    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        movie = new Movie();
        movie.setImdbId(61725);
        movie.setMovieTitle("The Graduate");
        movie.setPosterUrl("http://ia.media-imdb.com/images/M/MV5BMTQ0ODc4MDk4Nl5BMl5BanBnXkFtZTcwMTEzNzgzNA@@._V1_SX300.jpg");
        movie.setRating(8);
        movie.setYearOfRelease(1967);
        list = new ArrayList<>();
        list.add(movie);
    }
    @Test
    public void saveMovieTestSuccess() throws MovieAlreadyExistsException {
        when(movieRepository.save((Movie) any())).thenReturn(movie);
        Movie savedMovie = movieService.saveMovie(movie);
        Assert.assertEquals(movie,savedMovie);
        //verify here verifies that userRepository save method is only called once
        verify(movieRepository,times(1)).save(movie);

    }

    @Test(expected = MovieAlreadyExistsException.class)
    public void saveMovieTestFailure() throws MovieAlreadyExistsException {
        when(movieRepository.save((Movie) any())).thenReturn(null);
        Movie savedMovie = movieService.saveMovie(movie);
        System.out.println("savedUser" + savedMovie);
        Assert.assertEquals(movie,savedMovie);
        //add verify
        verify(movieRepository,times(1)).save(movie);
       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/
    }
    @Test
    public void getAllMovies(){

        movieRepository.save(movie);
        //stubbing the mock to return specific data
        when(movieRepository.findAll()).thenReturn(list);
        List<Movie> userlist = movieService.getAllMovies();
        Assert.assertEquals(list,userlist);
        //add verify
    }
    @Test
    public void testUpdateById() throws MovieNotFoundException {

        Movie mov1 = new Movie(1,"dhoni","fgfhgfhgfh",9,2016);
        Movie mov2 = new Movie(2,"Kalho na ho","fgfhgfhgfh",9,2002);
        Movie mov3 = new Movie(3,"Dhoom","fgfhgfhgfh",9,2002);
        movieRepository.save(mov1);
        movieRepository.save(mov2);
        movieRepository.save(mov3);
        mov1.setMovieTitle("Intern");
        when(movieRepository.existsById(mov1.getImdbId())).thenReturn(true);
        when(movieRepository.findById(mov1.getImdbId())).thenReturn(Optional.ofNullable(mov1));
        Movie updateById = movieService.updateMovie(mov1.getImdbId() , mov1);
        System.out.println("sds" + updateById);
        Assert.assertEquals(mov1 , updateById);
    }
    @Test
    public void deleteMovieTestSuccess() throws MovieNotFoundException {
        Movie mov1 = new Movie(2, "dhoni", "fgfhgfhgfh", 9, 2222);
        Movie mov2 = new Movie(3, "dhoni2", "fgfhgfhgfh", 9, 2222);
        movieRepository.save(mov1);
        movieRepository.save(mov2);
        when(movieRepository.existsById(mov2.getImdbId())).thenReturn(true);
        when(movieRepository.findById(mov2.getImdbId())).thenReturn(Optional.of(mov2));
        Movie deleteMovieById = movieService.deleteMovie(3);
        System.out.println("sds" + deleteMovieById);
        Assert.assertEquals(mov2, deleteMovieById);
    }
}
