package edu.kirkwood.model.json;

import java.time.LocalDate;

public class MovieSearchResult {
    private String id;
    private String title;
    private LocalDate release_date;
    private String overview;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public String getOverview() {
        return overview;
    }

    @Override
    public String toString() {
        return "MovieSearchResult{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", release_date=" + release_date +
                ", overview='" + overview + '\'' +
                '}';
    }
}
