/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Output.ListView;

/**
 *
 * @author fes77
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

import Data.Genre;
import Repository.Keys;
import Repository.Tables;
import java.util.List;
import movielens.Repository;

/**
 *
 * @author fes77
 */
public interface SearchMovies {
    public List<Integer> search(int start, int end);
    public static List<Integer> topRated(int start, int size)
    {
        String query = 
                "SELECT " 
                    + Keys.MOVIEID
                + " FROM " 
                    + Tables.RATING
                + " GROUP BY " 
                    + Keys.MOVIEID
                + " ORDER BY "
                    + "AVG(" + Keys.RATING + ") DESC, "
                    + "COUNT(*) DESC, "
                    + "AVG(" + Keys.TIMESTAMP + ") DESC";
        
        return Repository.findMovieByQuery(query, start, size);
    };
    public static List<Integer> leastRated(int start, int size)
    {
        String query = 
                "SELECT " 
                    + Keys.MOVIEID
                + " FROM " 
                    + Tables.RATING
                + " GROUP BY " 
                    + Keys.MOVIEID
                + " ORDER BY "
                    + "AVG(" + Keys.RATING + ") ASC, "
                    + "COUNT(*) DESC, "
                    + "AVG(" + Keys.TIMESTAMP + ") DESC";
        

        return Repository.findMovieByQuery(query, start, size);
    };
    
//    public static List<Movie> tagMostMatch(Map<String, Integer> tagMap, int start, int size)
//    {
//        String query = "";
//        return Repository.findMovieByQuery(query, start, size);
//    };
    public static List<Integer> tagMostMatch(int movieId, int start, int size)
    {
        String query = 
                " SELECT " 
                    + Keys.MOVIEID
                + " FROM "
                    + Tables.TAG
                + " WHERE " 
                    + Keys.TAG 
                + " IN " 
                + "("
                    + " SELECT " 
                        + Keys.TAG
                    + " FROM "
                        + Tables.TAG
                    + " WHERE " 
                        + Keys.MOVIEID + "=" + movieId
                + ")"
                + " GROUP BY " 
                    + Keys.MOVIEID
                + " HAVING "
                    + Keys.MOVIEID + "!=" + movieId
                + " ORDER BY "
                    + "COUNT(DISTINCT " + Keys.TAG + ") DESC, "
                    + "COUNT(" + Keys.TAG + ") DESC, "
                    + "AVG(" + Keys.TIMESTAMP + ") DESC";
        return Repository.findMovieByQuery(query, start, size);
    };
//    public static List<Movie> genreMostMatch(List<Genre> genres, int start, int size)
//    {
//        String query = "";
//        return Repository.findMovieByQuery(query, start, size);
//    };
    public static List<Integer> genreMostMatch(int movieId, int start, int size)
    {
        String query = 
                " SELECT " 
                    + Tables.GENRE + "." + Keys.MOVIEID
                + " FROM "
                    + Tables.GENRE
                    + " RIGHT OUTER JOIN "
                    + Tables.RATING
                    + " ON " 
                    + Tables.GENRE + "." + Keys.MOVIEID
                    + " = "
                    + Tables.RATING + "." + Keys.MOVIEID
                + " WHERE " 
                    + Keys.GENRE 
                + " IN " 
                + "("
                    + " SELECT " 
                        + Keys.GENRE
                    + " FROM "
                        + Tables.GENRE
                    + " WHERE " 
                        + Keys.MOVIEID + "=" + movieId
                + ")"
                + " GROUP BY " 
                    + Tables.GENRE + "." + Keys.MOVIEID
                + " HAVING "
                    + Tables.GENRE + "." + Keys.MOVIEID + "!=" + movieId
                + " ORDER BY "
                    + "COUNT(DISTINCT " + Keys.GENRE + ") DESC, "
                    + "AVG(" + Keys.RATING + ") DESC, "
                    + "AVG(" + Keys.TIMESTAMP + ") DESC";
        
//        SELECT Genre.movieId, COUNT(DISTINCT genre), AVG(rating), AVG(rating) FROM Genre RIGHT OUTER JOIN Rating ON Genre.movieId = Rating.movieId where genre IN 
//( SELECT genre FROM Genre WHERE movieId=1) 
//GROUP BY Genre.movieId HAVING Genre.movieId!=1 ORDER BY COUNT(DISTINCT genre) DESC, AVG(rating) DESC, AVG(rating) DESC LIMIT 10 OFFSET 0;
        return Repository.findMovieByQuery(query, start, size);
    };
    public static List<Integer> searchByGenre(Genre genre, int start, int size)
    {
        String query = "";
        return Repository.findMovieByQuery(query, start, size);
    };
    public static List<Integer> searchByTag(String tag, int start, int size)
    {
        String query = "";
        return Repository.findMovieByQuery(query, start, size);
    };
    public static List<Integer> searchByName(String name, int start, int size)
    {
        String query = "";
        return Repository.findMovieByQuery(query, start, size);
    };
    
    
}

