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
        
        return Repository.findTopRatedMovieIds(start, size);
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
                    + "AVG(" + Keys.RATING + ") DESC, "
                    + Keys.TIMESTAMP + " DESC";
        return Repository.findMovieByQuery(query, start, size);
    };
    
//    public static List<Movie> tagMostMatch(Map<String, Integer> tagMap, int start, int size)
//    {
//        String query = "";
//        return Repository.findMovieByQuery(query, start, size);
//    };
    public static List<Integer> tagMostMatch(int movieId, int start, int size)
    {
        String query = "";
        return Repository.findMovieByQuery(query, start, size);
    };
//    public static List<Movie> genreMostMatch(List<Genre> genres, int start, int size)
//    {
//        String query = "";
//        return Repository.findMovieByQuery(query, start, size);
//    };
    public static List<Integer> genreMostMatch(int movieId, int start, int size)
    {
        String query = "";
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

