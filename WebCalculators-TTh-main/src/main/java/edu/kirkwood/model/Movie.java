package edu.kirkwood.model;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class Movie implements Comparable<Movie> {
    private String id;
    private String title;
    private int year;
    private String plot;
    private String poster;

    public Movie(){}

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Movie(String id, String title, int year, String plot,  String poster) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.poster = poster;
    }

    public String getPoster(){
        return poster;
    }

    public void setPoster(String poster){
        this.poster = poster;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPlot() {return plot;}

    public void setPlot(String plot) {this.plot = plot;}

    @Override
    public String toString() {
        return "ID: " + id + " | Title: " + title + " | Year: " + year + " | Plot: " + plot;
    }

    @Override
    public int compareTo(@NotNull Movie o) {
        if (this.getId().length() != o.getId().length()){
            // sort by id length
            return this.getId().length() - o.getId().length();
        }
        // if lengths are the same, sort alphabetically
        return this.getId().compareTo(o.getId());
    }

    public static Comparator<Movie> compareTitle = (m1, m2) -> m1.getTitle().compareToIgnoreCase(m2.getTitle());

    public static Comparator<Movie> compareYear = Comparator.comparingInt(Movie::getYear);
}
