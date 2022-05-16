/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Output.DetailedView;

import Data.Movie;
import Output.ListView.GridView.MovieGridPanel;
import Output.MovieCard;
import Repository.Repository;
import java.util.List;

/**
 *
 * @author fes77
 */
public class AnimationCounter implements Runnable {
        private final int movieId;
        private final List<MovieCard> rtnList;
        private final MovieGridPanel container;
        boolean add2Container;
        
        public AnimationCounter(Integer movieId, List<MovieCard> rtnList, MovieGridPanel container, boolean add2Container) 
        { 
            this.movieId = movieId;
            this.rtnList = rtnList;
            this.container = container;
            this.add2Container = add2Container;
        }
        
        @Override
        public void run() {
            Movie movie = Repository.findMovieById(movieId);
            MovieCard card = new MovieCard(movie);
            
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
                }
            }
        }
}
    