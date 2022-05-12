 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Web;

import Resource.Message;
import java.io.IOException;
import movielens.Repository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraping {
    public static final String IMDB_LINK = "http://www.imdb.com/title/tt0";
    public static final String TMDB_LINK = "https://www.themoviedb.org/movie/";
    public static final String TMDB_BASELINK = "https://www.themoviedb.org";

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
            Message.ERROR.printMessage(ex);
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
            Message.ERROR.printMessage(ex);
            return null;
        }

        return document.body();
    }
    
    
    public static String getReleaseYearText(int movieId)
    {
        Element body = getTBDBBodyById(movieId);
        if(body == null)
        {
            Message.NO_MATCH_ERROR.printMessage("Release Year for id[" + movieId +"] not found");
            return "";
        }
        
        Elements release = body.getElementsByClass("release");

        return release.get(0).text();
    }
    
    public static int getReleaseYear(int movieId)
    {
        return extractYear(getReleaseYearText(movieId));
    }
    
    public static String getDurationText(int movieId)
    {
        Element body = getTBDBBodyById(movieId);
        if(body == null)
        {
            Message.NO_MATCH_ERROR.printMessage("Duration for id[" + movieId +"] not found");
            return "0 m";
        }
        return  body.getElementsByClass("runtime").get(0).text();
    }
    
    public static int getDuration(int movieId)
    {
        return conv2Mins(getDurationText(movieId));
    }
    
    private static int extractYear(String mon_day_year)
    {
        try
        {
            return Integer.parseInt(mon_day_year.split("/")[2].split(" ")[0]);
        }
        catch(IllegalArgumentException e){
            Message.ERROR.printMessage(e);
            return 0;
        }
    }
    
    private static int conv2Mins(String hours_mins)
    {
        try
        {
            return Integer.parseInt(hours_mins.split("h")[0]) * 60 + Integer.parseInt(hours_mins.split("h")[1].replace("m", "").replace(" ", ""));
        }
        catch(IllegalArgumentException e){
            Message.ERROR.printMessage(e);
            return 0;
        }
    }
    
   
    
    public static String getPictureURL(int movieId)
    {
        Element body = getTBDBBodyById(movieId);
        if(body == null)
        {
            Message.NO_MATCH_ERROR.printMessage("Duration for id[" + movieId +"] not found");
            return "https://www.lwf.org/images/emptyimg.png";
        }
        
        Element poster = body.getElementsByClass("poster").first();
        String url = poster.getElementsByTag("img").first().attr("data-src");
        
        Message.WEBSCRAPING.printMessage("PICTURE[" + TMDB_BASELINK + url + "]");
        return TMDB_BASELINK + url;
        
    }
    
    
    
    public static void main(String[] args) throws IOException {
//        Document document = Jsoup.connect("https://www.themoviedb.org/movie/228150-fury").get();
//        Element body = document.body();
//        System.out.println(body.text());
//        Elements courses = body.getElementsByClass("release");
//        for (Element course : courses) {
//            System.out.println(course.text());
//        }
        Repository.connect(Repository.Driver.MySQL ,"movie-lens.cpzst9uo9qun.ap-northeast-1.rds.amazonaws.com", 3306, "mydb" , "root", "rsTTMA2sHyUL");
//
//        System.out.println(Scraping.getDuration(1));
//        System.out.println(Scraping.getReleaseYear(1));
        
        System.out.println(Scraping.getPictureURL(2075));
    }
}