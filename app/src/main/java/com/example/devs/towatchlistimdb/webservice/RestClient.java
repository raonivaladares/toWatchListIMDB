package com.example.devs.towatchlistimdb.webservice;

import com.example.devs.towatchlistimdb.models.Movie;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

@Rest(rootUrl = "http://www.omdbapi.com", converters = { GsonHttpMessageConverter.class })
public interface RestClient {

    @Get("/?t={movie_name}")
    Movie searchMovie(final String movie_name);

}
