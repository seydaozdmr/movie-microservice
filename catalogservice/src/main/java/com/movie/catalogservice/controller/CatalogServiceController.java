package com.movie.catalogservice.controller;

import com.movie.catalogservice.model.CatalogItem;
import com.movie.catalogservice.model.RatingList;
import com.movie.catalogservice.service.CatalogService;
import com.movie.catalogservice.service.RatingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogServiceController {

    private final RestTemplate template;

    private final RatingService ratingService;
    private final CatalogService catalogService;

    public CatalogServiceController(RestTemplate template, RatingService ratingService, CatalogService catalogService) {
        this.template = template;
        this.ratingService = ratingService;
        this.catalogService = catalogService;
    }



//    @GetMapping("/{userId}")
//    @HystrixCommand(fallbackMethod = "getFallbackCatalog")
//    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
//        RatingList ratingList=template.getForObject("http://rating-service/ratings/users/"+userId,RatingList.class);
//
//        return ratingList.getRatingList()
//                .stream()
//                .map(rating->{
//                    Movie movie = template.getForObject("http://movie-info/movies/"+rating.getMovieId(),Movie.class);
//                    return new CatalogItem(movie.getName(),movie.getDescription(),rating.getRate());
//                }).collect(Collectors.toList());
//    }
//
//    public List<CatalogItem> getFallbackCatalog(@PathVariable("userId")String userId){
//        return Arrays.asList(new CatalogItem("No movie","",0));
//    }

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
        RatingList list=ratingService.ratingList(userId);
        return list.getRatingList()
                .stream()
                .map(rating->catalogService.getCatalogItem(rating))
                .collect(Collectors.toList());
    }
}
