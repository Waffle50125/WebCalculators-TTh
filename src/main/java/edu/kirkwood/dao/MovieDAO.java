package edu.kirkwood.dao;

import edu.kirkwood.model.Movie;

import java.util.List;

/**
 * The data access interface for MovieMySQL object
 * Defines the methods needed to get data
 */
public interface MovieDAO {
    /**
     * Retrieves all movies from the data source that matches the title
     * @param title String The movie title a user is searching for
     * @return List<MovieMySQL> A list of movies that matches the search title
     */
    List<Movie> search(String title);
}
