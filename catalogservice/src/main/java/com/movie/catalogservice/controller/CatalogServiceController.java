package com.movie.catalogservice.controller;

import com.movie.catalogservice.model.CatalogItem;
import com.movie.catalogservice.model.Movie;
import com.movie.catalogservice.model.RatingList;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogServiceController {

    private final RestTemplate template;

    public CatalogServiceController(RestTemplate template) {
        this.template = template;
    }



    @GetMapping("/{userId}")
    @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
        RatingList ratingList=template.getForObject("http://rating-service/ratings/users/"+userId,RatingList.class);

        return ratingList.getRatingList()
                .stream()
                .map(rating->{
                    Movie movie = template.getForObject("http://movie-info/movies/"+rating.getMovieId(),Movie.class);
                    return new CatalogItem(movie.getName(),movie.getDescription(),rating.getRate());
                }).collect(Collectors.toList());
    }

    public List<CatalogItem> getFallbackCatalog(@PathVariable("userId")String userId){
        return Arrays.asList(new CatalogItem("No movie","",0));
    }
}
