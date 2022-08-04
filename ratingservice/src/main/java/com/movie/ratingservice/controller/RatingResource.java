package com.movie.ratingservice.controller;

import com.movie.ratingservice.model.Rating;
import com.movie.ratingservice.repo.RatingList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingResource {
    private List<Rating> ratingList;

    public RatingResource() {
        this.ratingList = List.of(new Rating("123",5),new Rating("222",6),new Rating("333",7));
    }

    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return ratingList.stream().filter(e->e.getMovieId().equals(movieId)).findFirst().orElseThrow(()->new RuntimeException("bu film bulunamadı"));
    }

    @GetMapping("/users/{userId}")
    public RatingList getRatingList(@PathVariable("userId")String userId){
        RatingList list=new RatingList();
        list.getRatingList().addAll(ratingList);
        return list;
    }
}
