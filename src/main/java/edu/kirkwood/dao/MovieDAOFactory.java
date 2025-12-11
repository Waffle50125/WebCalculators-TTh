package edu.kirkwood.dao;

import edu.kirkwood.dao.impl.JsonMovieDAO;
import edu.kirkwood.dao.impl.MySQLMovieDAO;
import edu.kirkwood.dao.impl.XmlMovieDAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MovieDAOFactory {
    private static Properties properties = new Properties();
    static {
        try(
                InputStream in = MovieDAOFactory.class.getClassLoader()
                    .getResourceAsStream("application.properties");
            ) {
            if (in == null) {
                throw new FileNotFoundException("application.properties not found");
            }
            properties.load(in);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * To retrieve a MovieDAO based on application.properties settings
     * @return A MovieDAO (XMLMovieDAO, MySQLMovieDAO, JSONMovieDAO, etc.)
     */
    public static MovieDAO getMovieDAO() {
        MovieDAO result = null;

        String datasourceType = properties.getProperty("datasource.type");
        if (datasourceType == null || datasourceType.isEmpty()) {
            throw new IllegalArgumentException("datasource is missing");
        }
        String apiURL = "";
        String accessToken = "";
        switch (datasourceType.toLowerCase()) {
            case "xml":
                apiURL = properties.getProperty("xml.apiURL");
                if (apiURL == null || apiURL.isEmpty()) {
                    throw new IllegalArgumentException("xml.apiURL is missing");
                }
                result = new XmlMovieDAO(apiURL);
                break;
            case "json":
                apiURL = properties.getProperty("json.apiURL");
                if (apiURL == null || apiURL.isEmpty()) {
                    throw new IllegalArgumentException("json.apiURL is missing");
                }
                accessToken = properties.getProperty("json.apiReadAccessToken");
                if (accessToken == null || accessToken.isEmpty()) {
                    throw new IllegalArgumentException("json.apiReadAccessToken");
                }

                result = new JsonMovieDAO(apiURL, accessToken);
                break;
            case "mysql":
                result = new MySQLMovieDAO();

            case "mongodb":
                break;
            default:
                throw new RuntimeException("Unknown datasource type: " + datasourceType);
        }

        return result;
    }
}
