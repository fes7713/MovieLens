/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Output.Old;

import Data.Movie;
import Output.GridView.MovieCard;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JPanel;
import movielens.Repository;

/**
 *
 * @author fes77
 */
public class ThreadPoolSample {
    static class MovieCardProducer implements Runnable {
        private Movie movie;
        List<MovieCard> rtnList;
        JPanel container;
        boolean add2Container;
        String a;
        public MovieCardProducer(Movie movie, List<MovieCard> rtnList, JPanel container, boolean add2Container) 
        { 
            this.movie = movie; 
            this.rtnList = rtnList;
            this.container = container;
            this.add2Container = add2Container;
            a = "a";
        }
        
        @Override
        public void run() {
            MovieCard card = new MovieCard(movie);
            
            synchronized(rtnList)
            {
                rtnList.add(card);
            }
            
            if(add2Container)
            {
                System.out.println(a.hashCode());
                
                synchronized(container)
                {
                    container.add(card);
                    container.validate();
                    container.repaint();
    //                System.out.println("Container size : " + container.getComponentCount());
                }
            }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Repository.connect(Repository.Driver.MySQL ,"movie-lens.cpzst9uo9qun.ap-northeast-1.rds.amazonaws.com", 3306, "mydb" , "root", "rsTTMA2sHyUL");

        JPanel panel = new JPanel();
        List<MovieCard> rtnList = new ArrayList();
        executor.execute(new MovieCardProducer(Repository.findMovieById(1), rtnList, panel, true));
        executor.execute(new MovieCardProducer(Repository.findMovieById(2), rtnList, panel, false));
        executor.execute(new MovieCardProducer(Repository.findMovieById(3), rtnList, panel, true));
        executor.execute(new MovieCardProducer(Repository.findMovieById(4), rtnList, panel, true));
        executor.execute(new MovieCardProducer(Repository.findMovieById(5), rtnList, panel, true));
        executor.execute(new MovieCardProducer(Repository.findMovieById(6), rtnList, panel, false));
        executor.execute(new MovieCardProducer(Repository.findMovieById(7), rtnList, panel, true));
        executor.execute(new MovieCardProducer(Repository.findMovieById(8), rtnList, panel, true));
        executor.execute(new MovieCardProducer(Repository.findMovieById(9), rtnList, panel, true));
        executor.execute(new MovieCardProducer(Repository.findMovieById(10), rtnList, panel, false));
        executor.execute(new MovieCardProducer(Repository.findMovieById(11), rtnList, panel, false));
        executor.execute(new MovieCardProducer(Repository.findMovieById(12), rtnList, panel, false));
        
        while(true)
        {
            Thread.sleep(1000);
            System.out.println("Container size : " + panel.getComponentCount());
            System.out.println("Array size : " + rtnList.size());
        }
    }
    
    }
}