package com.stackroute.movie;

import com.stackroute.movie.domain.Movie;
import com.stackroute.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.logging.Logger;

@SpringBootApplication
public class MovieApplication {
    MovieRepository movieRepository;

//    @Autowired
//    public MovieApplication(MovieRepository movieRepository) {
//        this.movieRepository = movieRepository;
//    }

//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        Movie movie = new Movie(5474036, "Manmarziyaan", "https://m.media-amazon.com/images/M/MV5BNTU3ZjEzMTYtYThjMC00ZjljLWJjYjEtZGU5M2U5ODcwNTY4XkEyXkFqcGdeQXVyNTE4ODU0NzA@._V1_QL50_SY1000_CR0,0,666,1000_AL_.jpg", 4, 2018);
//        movieRepository.save(movie);
//        movie = new Movie(547403612, "October", "https://m.media-amazon.com/images/M/MV5BNTU3ZjEzMTYtYThjMC00ZjljLWJjYjEtZGU5M2U5ODcwNTY4XkEyXkFqcGdeQXVyNTE4ODU0NzA@._V1_QL50_SY1000__.jpg", 4, 2017);
//        movieRepository.save(movie);
//        movie = new Movie(342311212, "Dangal", "https://m.media-amazon.com/images/M/MV5BNTU3ZjEzMTYtYThjMC00ZjljLNzA@._V1_QL50_SY1000__.jpg", 4, 2017);
//        movieRepository.save(movie);
//
//    }

    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }


}
