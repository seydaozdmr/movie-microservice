package com.movie.catalogservice.model;

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
