package com.movie.catalogservice.service;

import com.movie.catalogservice.model.Rating;
import com.movie.catalogservice.model.RatingList;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RatingService {
    private final RestTemplate template;

    public RatingService(RestTemplate template) {
        this.template = template;
    }

    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
    public RatingList ratingList(String userId){
        return template.getForObject("http://rating-service/ratings/users/"+userId,RatingList.class);
    }

    public RatingList getFallbackUserRating(String userId){
        RatingList ratingList=new RatingList();
        ratingList.setRatingList(Arrays.asList(new Rating("0",0)));
        return ratingList;
    }
}
