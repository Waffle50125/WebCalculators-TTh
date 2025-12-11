package edu.kirkwood.model.xml;

import edu.kirkwood.model.Movie;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

/**
 * This class handles the result element from the OMDB API
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MovieSearchResult extends Movie {
    @XmlAttribute(name = "imdbID")
    private String id;
    @XmlAttribute(name = "title")
    private String title;
    @XmlAttribute(name = "year")
    private int year;


    public String getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public int getYear() {
        return year;
    }


    @Override
    public String toString() {
        return "MovieSearchResult{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                '}';
    }
}
