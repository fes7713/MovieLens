/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Output.GridView;

import Data.Movie;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import movielens.Repository;

/**
 *
 * @author fes77
 */
public class MovieListPanel3 extends javax.swing.JPanel {

    /**
     * Creates new form MovieListPanel
     */
    List<Movie> movies;
      List<MovieCard> cards;
    ExecutorService executor;

    boolean debug = false;
    
    public MovieListPanel3()
    {
        initComponents();
        removeAll();
        if(Repository.isConnected() == false)
            Repository.connect(Repository.Driver.MySQL ,"movie-lens.cpzst9uo9qun.ap-northeast-1.rds.amazonaws.com", 3306, "mydb" , "root", "rsTTMA2sHyUL");
        
        debug = true;
        for(int i = 0; i < 4; i++)
        {
            add(new MovieCard(new Movie(0, "Sample Movie")));
        }
        
        
        BoxLayout layout = (BoxLayout) getLayout();
        
    }
    
    public MovieListPanel3(int start, int size, int threadSize)
    {
        initComponents();
        removeAll();

        if(Repository.isConnected() == false)
            Repository.connect(Repository.Driver.MySQL ,"movie-lens.cpzst9uo9qun.ap-northeast-1.rds.amazonaws.com", 3306, "mydb" , "root", "rsTTMA2sHyUL");
        
        movies = Repository.findTopRatedMovies(start, size);
        executor = Executors.newFixedThreadPool(threadSize);
    }
    
    public MovieListPanel3(List<Movie> movies, int threadSize) {
        initComponents();
        removeAll();
        if(Repository.isConnected() == false)
            Repository.connect(Repository.Driver.MySQL ,"movie-lens.cpzst9uo9qun.ap-northeast-1.rds.amazonaws.com", 3306, "mydb" , "root", "rsTTMA2sHyUL");
        
        movies = movies;
        executor = Executors.newFixedThreadPool(threadSize);
    }

    private void updateMovies() {

//        for (int i = 0; i < addingMovies.size(); i++) {
////            MovieCard card = new MovieCard(addingMovies.get(i));
////            movies.add(card);
//            executor.execute(new ThreadPoolSample.MovieCardProducer(addingMovies.get(i), movies, this, true));
//            currentSize++;
////            executor.execute(new ThreadPoolSample.MovieCardProducer(addingMovies.get(addingMovies.size() - i - 1), movies, this, false));
//            System.out.println("Adding[" + addingMovies.get(i).getId() +  "] into list");
//        }
//
////        for (int i = addingMovies.size() / 2; i < addingMovies.size(); i++) {
////            
////            executor.execute(new ThreadPoolSample.MovieCardProducer(addingMovies.get(i), movies, this, false));
////            System.out.println("Extra[" + addingMovies.get(i).getId() +  "] into list");
////        }
//        
//        for (int i = currentSize; i < totalSize; i++) {
//            add(movies.get(i));
//        }
//        
//        for(int i = totalSize; i < currentSize; i++)
//        {
//            remove(movies.get(i));
//        }
//        
        System.out.println("[R]Current " + getComponentCount() + "\n\n\n");

        
        validate();
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 300));
        MovieListPanel3 movieListPanel = new MovieListPanel3();
//            cell.setBackground(new Color(34, 34, 34));
        frame.setBackground(new Color(34, 34, 34));
        frame.add(movieListPanel);
        frame.setVisible(true);
//        Scanner sk = new Scanner(System.in);
//        while(true)
//        {
//            if(sk.nextInt() == 1)
//                frame.repaint();
//        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        movieCard1 = new Output.GridView.MovieCard();
        movieCard3 = new Output.GridView.MovieCard();
        movieCard2 = new Output.GridView.MovieCard();
        movieCard4 = new Output.GridView.MovieCard();

        setBackground(new java.awt.Color(51, 51, 51));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));
        add(movieCard1);
        add(movieCard3);
        add(movieCard2);
        add(movieCard4);
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        updateMovies();
    }//GEN-LAST:event_formComponentResized


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Output.GridView.MovieCard movieCard1;
    private Output.GridView.MovieCard movieCard2;
    private Output.GridView.MovieCard movieCard3;
    private Output.GridView.MovieCard movieCard4;
    // End of variables declaration//GEN-END:variables
}