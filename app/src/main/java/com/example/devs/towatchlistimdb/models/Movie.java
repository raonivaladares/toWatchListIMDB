package com.example.devs.towatchlistimdb.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class Movie implements Serializable {

    public static final String NONE = "N/A";

    public static final String TITLE = "title";

    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField(index = true)
    @SerializedName("Title")
    @Expose
    private String title;

    @DatabaseField
    @SerializedName("Year")
    @Expose
    private String year;

    @DatabaseField
    @SerializedName("Rated")
    @Expose
    private String rated;

    @DatabaseField
    @SerializedName("Released")
    @Expose
    private String released;

    @DatabaseField
    @SerializedName("Runtime")
    @Expose
    private String runtime;

    @DatabaseField
    @SerializedName("Genre")
    @Expose
    private String genre;

    @DatabaseField
    @SerializedName("Director")
    @Expose
    private String director;

    @DatabaseField
    @SerializedName("Writer")
    @Expose
    private String writer;

    @DatabaseField
    @SerializedName("Actors")
    @Expose
    private String actors;

    @DatabaseField
    @SerializedName("Plot")
    @Expose
    private String plot;

    @DatabaseField
    @SerializedName("Language")
    @Expose
    private String language;

    @DatabaseField
    @SerializedName("Country")
    @Expose
    private String country;

    @DatabaseField
    @SerializedName("Awards")
    @Expose
    private String awards;

    @DatabaseField
    @SerializedName("Poster")
    @Expose
    private String poster;

    @DatabaseField
    @SerializedName("Metascore")
    @Expose
    private String metaScore;

    @DatabaseField
    @Expose
    private String imdbRating;

    @DatabaseField
    @Expose
    private String imdbVotes;

    @DatabaseField
    @Expose
    private String imdbID;

    @DatabaseField
    @SerializedName("Type")
    @Expose
    private String type;

    @SerializedName("Response")
    @Expose
    private String response;

    @SerializedName("Error")
    @Expose
    private String error;


    Movie() {
        // needed by ormlite
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster() {
        return NONE.equals(poster) ? null : poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getMetaScore() {
        return metaScore;
    }

    public void setMetaScore(String metaScore) {
        this.metaScore = metaScore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}