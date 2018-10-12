package com.stackroute.movie.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
import javax.validation.constraints.*;

//@Entity----Done for Working of MongoDB
@Document(collection = "movies")//COmponent added for working of MongoDB
public class Movie {
    @Id
        @NotNull  private int imdbId;
        @NotNull private String movieTitle;
        @NotNull private String posterUrl;
        @PositiveOrZero private int rating;
        @NotNull @Min(1900) @PositiveOrZero private double yearOfRelease;

    public Movie() {
    }

    public Movie(int imdbId, String movieTitle, String posterUrl, int rating, int yearOfRelease) {
        this.imdbId = imdbId;
        this.movieTitle = movieTitle;
        this.posterUrl = posterUrl;
        this.rating = rating;
        this.yearOfRelease = yearOfRelease;
    }

    public int getImdbId() {
        return imdbId;
    }

    public void setImdbId(int imdbId) {
        this.imdbId = imdbId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(double yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "imdbId=" + imdbId +
                ", movieTitle='" + movieTitle + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", rating=" + rating +
                ", yearOfRelease=" + yearOfRelease +
                '}';
    }
}

