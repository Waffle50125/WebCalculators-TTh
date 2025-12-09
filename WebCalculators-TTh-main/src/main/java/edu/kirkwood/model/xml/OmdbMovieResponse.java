package edu.kirkwood.model.xml;

import jakarta.xml.bind.annotation.*;

import java.util.List;

/**
 * This class handles the root element from the OMDB API
 */
@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class OmdbMovieResponse {
    @XmlAttribute(name = "totalResults")
    private int totalResults;
    @XmlAttribute(name = "response")
    private String response;
    @XmlElement(name = "result")
    List<MovieSearchResult> searchResults;

    public int getTotalResults() {
        return totalResults;
    }

    public String getResponse() {
        return response;
    }

    public List<MovieSearchResult> getSearchResults() {
        return searchResults;
    }
}
