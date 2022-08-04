package com.movie.movieinfo.controller;

import com.movie.movieinfo.model.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    private static List<Movie> movies = List.of(new Movie("123","Hızlı ve Öfkeli","Aksiyon"),
            new Movie("222","Harry Potter","Fantastik"),
            new Movie("333","Yüzüklerin Efendisi","Fantastik"));

    @GetMapping("/{movieId}")
    public Movie getMovie(@PathVariable("movieId") String movieId){
        return movies.stream().filter(e->e.getMovieId().equals(movieId)).findFirst().orElseThrow(()->new RuntimeException("Bu film bulunmamaktadır"));
    }
}
