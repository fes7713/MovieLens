/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Output;

import Data.Movie;
import Output.ListView.MovieListView;
import java.util.List;
import Repository.Repository;

/**
 *
 * @author fes77
 */
public class MovieCardProducer implements Runnable {
        private int movieId;
        List<MovieCard> rtnList;
        MovieListView container;
        boolean add2Container;
        
        public MovieCardProducer(Integer movieId, List<MovieCard> rtnList, MovieListView container, boolean add2Container) 
        { 
            this.movieId = movieId;
            this.rtnList = rtnList;
            this.container = container;
            this.add2Container = add2Container;
        }
        
        
        @Override
        public void run() {
            Movie movie = Repository.findMovieById(movieId);
            MovieCard card = new MovieCard();
            
            synchronized(rtnList)
            {
                rtnList.add(card);
            }
            
            if(add2Container)
            {
                synchronized(container)
                {
                    container.add(card);
                    container.updateMovies();
//                    container.validate();
//                    container.repaint();
    //                System.out.println("Container size : " + container.getComponentCount());
                }
            }
            card.setMovie(movie);
        }
}
    