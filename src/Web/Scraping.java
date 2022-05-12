 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Web;

import java.io.IOException;
import movielens.Repository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraping {
    public static final String IMDB_LINK = "http://www.imdb.com/title/";
    public static final String TMDB_LINK = "https://www.themoviedb.org/movie/tt0";

    // Nuulable
    public static Element getIMDBBodyById(int movieId)
    {
        int imdbId = Repository.findImdbIdById(movieId);
        if(imdbId == 0)
            return null;
        
        Document document;
        try {
            document = Jsoup.connect(IMDB_LINK + imdbId).get();
        } catch (IOException ex) {
            return null;
        }

        return document.body();
    }
    
    public static Element getTBDBBodyById(int movieId)
    {
        int tmdbId = Repository.findTmdbIdById(movieId);
        if(tmdbId == 0)
            return null;
        
        Document document;
        try {
            document = Jsoup.connect(TMDB_LINK + tmdbId).get();
//            document = Jsoup.connect("https://www.themoviedb.org/movie/862").get();

        } catch (IOException ex) {
            return null;
        }

        return document.body();
    }
    
    
    public static int getReleaseYear(int movieId)
    {
        Element body = getTBDBBodyById(movieId);
        Elements courses = body.getElementsByClass("release");
//        for (Element course : courses) {
//            System.out.println(course.text());
//        }
        return extractYear(courses.get(0).text());
    }
    
    public static int getDuration(int movieId)
    {
        Element body = getTBDBBodyById(movieId);
        Elements courses = body.getElementsByClass("runtime");
//        for (Element course : courses) {
//            System.out.println(course.text());
//        }
        return conv2Mins(courses.get(0).text());
    }
    
    private static int extractYear(String mon_day_year)
    {
        return Integer.parseInt(mon_day_year.split("/")[2].split(" ")[0]);
    }
    
    private static int conv2Mins(String hours_mins)
    {
        return Integer.parseInt(hours_mins.split("h")[0]) * 60 + Integer.parseInt(hours_mins.split("h")[1].replace("m", "").replace(" ", ""));
    }
    
//    public static String getPictureURL(int movieId)
//    {
//        
//    }
    
    
    
    public static void main(String[] args) throws IOException {
//        Document document = Jsoup.connect("https://www.themoviedb.org/movie/862-toy-story").get();
//        Element body = document.body();
//        System.out.println(body.text());
//        Elements courses = body.getElementsByClass("release");
//        for (Element course : courses) {
//            System.out.println(course.text());
//        }
        Repository.connect(Repository.Driver.MySQL ,"movie-lens.cpzst9uo9qun.ap-northeast-1.rds.amazonaws.com", 3306, "mydb" , "root", "rsTTMA2sHyUL");

        System.out.println(Scraping.getDuration(1));
        System.out.println(Scraping.getReleaseYear(1));

        

    }
}