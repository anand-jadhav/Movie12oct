package com.stackroute.movie.repository;

import com.stackroute.movie.domain.Movie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@DataMongoTest
public class MovieRepositoryTest {
    @Autowired
    MovieRepository movieRepository;
    Movie movie;

    @Before
    public void setUp() {
        movie = new Movie();
        movie.setImdbId(61725);
        movie.setMovieTitle("The Graduate");
        movie.setPosterUrl("http://ia.media-imdb.com/images/M/MV5BMTQ0ODc4MDk4Nl5BMl5BanBnXkFtZTcwMTEzNzgzNA@@._V1_SX300.jpg");
        movie.setRating(8);
        movie.setYearOfRelease(1967);
    }

//    @After
//    public void tearDown(){
//        movieRepository.deleteAll();
//    }
    @Test
    public void testSaveMovie() {
        movieRepository.save(movie);
        Movie fetchMovie = movieRepository.findById(movie.getImdbId()).get();
        Assert.assertEquals(61725, fetchMovie.getImdbId());
    }
    @Test
    public void testSaveMovieFailure() {
        Movie testMovie = new Movie(61729, "Spiderman",
                "http://ia.media-imdb.com/images/M/MV5BMTQ0ODc4MDk4Nl5BMl5BanBnXkFtZTcwMTEzNzgzNA@@._V1_SX300.jpg",
                8, 201);
        movieRepository.save(movie);
        Movie fetchMovie = movieRepository.findById(movie.getImdbId()).get();
        Assert.assertNotSame(testMovie.getImdbId(), fetchMovie.getImdbId());
    }


    @Test
    public void testSearch() {
        movieRepository.save(movie);
        Movie movie2 = movieRepository.findById(movie.getImdbId()).get();
        Assert.assertEquals(movie.toString(), movie2.toString());
    }

    @Test
    public void testDeleteMovie() {
        movieRepository.save(movie);
        movieRepository.delete(movie);
        Assert.assertEquals(Optional.empty(), movieRepository.findById(movie.getImdbId()));
    }
}
