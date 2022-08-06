package com.movie.catalogservice.service;

import com.movie.catalogservice.model.CatalogItem;
import com.movie.catalogservice.model.Movie;
import com.movie.catalogservice.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatalogService {

    private final RestTemplate restTemplate;

    public CatalogService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating){
        Movie movie=restTemplate.getForObject("http://movie-info/movies/"+rating.getMovieId(),Movie.class);
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRate());
    }

    public CatalogItem getFallbackCatalogItem(Rating rating){
        return new CatalogItem("Movie not found","", rating.getRate());
    }
}
