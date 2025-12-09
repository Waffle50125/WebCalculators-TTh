package edu.kirkwood.model.json;

import java.util.List;

public class TmdbMovieResponse {
    private List<MovieSearchResult> results;
    private int total_pages;

    public List<MovieSearchResult> getResults() {
        return results;
    }

    public int getTotal_pages() {
        return total_pages;
    }
}
