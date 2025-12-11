package edu.kirkwood.dao.impl;

import edu.kirkwood.dao.MovieDAO;
import edu.kirkwood.model.Movie;
import edu.kirkwood.model.xml.MovieSearchResult;
import edu.kirkwood.model.xml.OmdbMovieResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class XmlMovieDAO implements MovieDAO {
    private String apiURL;

    public XmlMovieDAO(String apiURL) {
        this.apiURL = apiURL;
    }

    /**
     * Retrieves all movies from the data source that matches the title
     * @param title String The movie title a user is searching for
     * @return List<Movie> A list of movies that matches the search title
     */
    @Override
    public List<Movie> search(String title) {
        List<MovieSearchResult> results = fetch(title);
        List<Movie> movies = new ArrayList<>();

        results.forEach(result -> {
            Movie movie = new Movie();
            movie.setId(result.getId());
            movie.setTitle(result.getTitle());
            movie.setYear(result.getYear());
            movies.add(movie);
        });

        return movies;
    }

    /**
     * Retrieves all movies from the data source that matches the title
     * @param title String The movie title a user is searching for
     * @return List<MovieSearchResult> A list of movies that matches the search title
     */
    public List<MovieSearchResult> fetch(String title) {
        List<MovieSearchResult> result = new ArrayList<MovieSearchResult>();

        if (apiURL == null || apiURL.isEmpty()) {
            throw new IllegalArgumentException("apiURL cannot be null or empty");
        }
        String encodedSearch = URLEncoder.encode(title, StandardCharsets.UTF_8);
        boolean finished = false;
        String fullURL = "";
        int i = 1;
        while (!finished) {
            fullURL = String.format("%s&s=%s&page=%s", apiURL, encodedSearch, i);
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(fullURL)).build();
                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String body = response.body();
                OmdbMovieResponse omdbMovieResponse = parseXML(body);
                boolean dataReturned = omdbMovieResponse.getResponse().equalsIgnoreCase("true");
                if (!dataReturned) {
                    finished = true;
                } else {
                    result.addAll(omdbMovieResponse.getSearchResults());
                    i++;
                }
            } catch (IOException | InterruptedException | JAXBException e) {
                throw new RuntimeException(e);
            }
        }



        return result;
    }

    /**
     * Parse an XML file into a list of movies
     * @param xml The raw String data
     * @return a List of MovieSearchResult objects
     */
    public OmdbMovieResponse parseXML(String xml) throws JAXBException {
        OmdbMovieResponse result = null;

        JAXBContext context = JAXBContext.newInstance(OmdbMovieResponse.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(xml);
        result = (OmdbMovieResponse)unmarshaller.unmarshal(reader);

        return result;
    }
}
