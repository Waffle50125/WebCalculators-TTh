package edu.kirkwood.controller;

import edu.kirkwood.dao.MovieDAO;
import edu.kirkwood.dao.MovieDAOFactory;
import edu.kirkwood.dao.impl.JsonMovieDAO;
import edu.kirkwood.dao.impl.MySQLMovieDAO;
import edu.kirkwood.dao.impl.XmlMovieDAO;
import edu.kirkwood.model.Movie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/movies")
public class MovieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/movies.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");

        req.setAttribute("search", search);

        List<Movie> movies = null;
        String searchError = null;

        if (search != null && !search.trim().isEmpty()) {
            try {
                movies = getResults(search);
            } catch (RuntimeException e) {
                if (movies == null) {
                    searchError = "No results found";
                }
            }
        }
        else {
            searchError = "Please enter a search term";
        }

        req.setAttribute("movies", movies);
        req.setAttribute("searchError", searchError);

        req.getRequestDispatcher("WEB-INF/movies.jsp").forward(req,resp);
    }

    private List<Movie> getResults(String title){

        MovieDAO movieDAO = MovieDAOFactory.getMovieDAO();
        List<Movie> results = new ArrayList<>();
        try {
            if (movieDAO instanceof XmlMovieDAO) {
                XmlMovieDAO xmlMovieDAO = (XmlMovieDAO) movieDAO;
                results = xmlMovieDAO.search(title);

            } else if (movieDAO instanceof MySQLMovieDAO) {
                MySQLMovieDAO mySQLMovieDAO = (MySQLMovieDAO) movieDAO;
                results = mySQLMovieDAO.search(title);
            } else if (movieDAO instanceof JsonMovieDAO) {
                JsonMovieDAO jsonMovieDAO = (JsonMovieDAO) movieDAO;
                results = jsonMovieDAO.search(title);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }
}
