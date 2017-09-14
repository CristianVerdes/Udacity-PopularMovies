package com.example.cristianv.popularmovies.display.movie;

/**
 * Created by cristianv on 9/14/17.
 */

public class MovieModel {
    private String title;
    private String poster;
    private String overview;
    private String voteAverage;
    private String releaseDate;

    public MovieModel() {
    }

    public MovieModel(String title, String poster, String overview, String voteAverage, String releaseDate) {
        this.title = title;
        this.poster = poster;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
    }

    // Getters

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getOverview() {
        return overview;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    // Setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
