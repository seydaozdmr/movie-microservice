package com.movie.ratingservice.repo;

import com.movie.ratingservice.model.Rating;

import java.util.ArrayList;
import java.util.List;

public class RatingList {
    private List<Rating> ratingList;

    public RatingList() {
        ratingList=new ArrayList<>();
    }

    public RatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }
}
