/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Web;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraping {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://www.themoviedb.org/movie/862-toy-story").get();
        Element body = document.body();
        System.out.println(body.text());
        Elements courses = body.getElementsByClass("release");
        for (Element course : courses) {
            System.out.println(course.text());
        }
        

    }
}