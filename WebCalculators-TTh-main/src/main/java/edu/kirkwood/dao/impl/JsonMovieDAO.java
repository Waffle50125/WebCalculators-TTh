package edu.kirkwood.dao.impl;

import com.google.gson.*;
import edu.kirkwood.dao.JsonTypeAdapter;
import edu.kirkwood.dao.MovieDAO;
import edu.kirkwood.model.Movie;
import edu.kirkwood.model.json.TmdbMovieResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JsonMovieDAO implements MovieDAO {
    private String apiURL;
    private String apiToken;
    public JsonMovieDAO(String apiURL, String apiToken) {
        this.apiURL = apiURL;
        this.apiToken = apiToken;
    }

    /**
     * Get raw data from themoviedb API
     * @param title Movie to search for
     * @return JSON String containing all matching movies
     */
    public String getRawData(String title, int page) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(apiURL + "query=" + title + "&page=" + page)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + apiToken)
                .build();
        Response response = null;
        String responseBody = "";
        try {
            response = client.newCall(request).execute();
            responseBody = response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return responseBody;
    }

    @Override
    public List<Movie> search(String title) {
        List<Movie> movies = new ArrayList<>();
        int currentPage = 1;
        while (true) {
            String rawData = getRawData(title, currentPage);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new JsonTypeAdapter.LocalDateDeserializer())
                    .create();
            TmdbMovieResponse tmdbMovieResponse = null;

            try {
                tmdbMovieResponse = gson.fromJson(rawData, TmdbMovieResponse.class);
            } catch (JsonSyntaxException e) {
                throw new RuntimeException(e);
            }
            tmdbMovieResponse.getResults().forEach(result -> {
                Movie movie = new Movie();
                movie.setId(result.getId());
                movie.setTitle(result.getTitle());
                if (result.getRelease_date() != null) {
                    movie.setYear(result.getRelease_date().getYear());
                }
                else {
                    movie.setYear(0);
                }
                movie.setPlot(result.getOverview());
                movies.add(movie);
                movie.setPoster(result.getPoster_path());
            });
            if (tmdbMovieResponse.getTotal_pages() > currentPage) {
                currentPage++;
            } else {
                break;
            }
        }
        return movies;
    }

    /**
     * Takes in a JSON string and changes it into a human-readable format
     * @param rawData String the raw JSON
     * @return String the formatted JSON string
     */
    public String formatData(String rawData) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = new JsonParser().parse(rawData);
        return gson.toJson(jsonElement);
    }
}
