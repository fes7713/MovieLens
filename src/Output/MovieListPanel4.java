/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Output;

import Data.Movie;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFrame;
import movielens.Repository;

/**
 *
 * @author fes77
 */
public class MovieListPanel4 extends javax.swing.JPanel {

    /**
     * Creates new form MovieListPanel
     */
    Container parent;
    List<MovieCard> movies;
    ExecutorService executor;
    int unit;
    int size;
    int nCols;
    int nRows;
    
    
    boolean debug = false;
    
    public MovieListPanel4()
    {
        initComponents();
        removeAll();
        if(Repository.isConnected() == false)
            Repository.connect(Repository.Driver.MySQL ,"movie-lens.cpzst9uo9qun.ap-northeast-1.rds.amazonaws.com", 3306, "mydb" , "root", "rsTTMA2sHyUL");
        
        debug = true;
        for(int i = 0; i < 20; i++)
        {
            add(new MovieCard(new Movie(0, "Sample Movie")));
        }
        
        
//        GridLayout layout = (GridLayout) getLayout();
//        layout.setColumns(Math.max(getWidth() / 230, 1));
//        layout.setRows(Math.max(getHeight() / 250, 1));
        unit = 20;
    }
    
    public MovieListPanel4(int size, int threadSize) {
        initComponents();
        removeAll();

//        GridLayout layout = (GridLayout) getLayout();
//        layout.setColumns(Math.max(getWidth() / 230, 1));
//        layout.setRows(Math.max(getHeight() / 250, 1));
        
        if(Repository.isConnected() == false)
            Repository.connect(Repository.Driver.MySQL ,"movie-lens.cpzst9uo9qun.ap-northeast-1.rds.amazonaws.com", 3306, "mydb" , "root", "rsTTMA2sHyUL");
        
        movies = new LinkedList();
        executor = Executors.newFixedThreadPool(threadSize);
        this.unit = size;
        loadMovieCards();
        validate();
        repaint();
    }
    
    public int getRows()
    {
        return nRows;
    }
    
    public int getCols()
    {
        return nCols;
    }
    
    public void setParent(Container parent)
    {
        this.parent = parent;
    }
    
    private void loadMovieCards()
    {
        int listSize = movies.size();
        List<Integer> addingMovies = Repository.findTopRatedMovieIds(size, unit);
        size += unit;
        System.out.println(addingMovies.size() +"adasdasdasdasd");
        
        for (int i = 0; i < addingMovies.size(); i++) {

            executor.execute(new MovieCardProducer(addingMovies.get(i), movies, null, true));
            System.out.println("Adding[" + i +  "] into list");
        }
        
    }
    
    public void updateMovies() {
        
        if(parent == null)
            
            nCols = Math.max(Math.min(getWidth(), getParent().getWidth()) / 230, 1);
        else
        {
            nCols = Math.max(Math.min(getWidth(), parent.getWidth()) / 230, 1);
            if(getWidth() > parent.getWidth())
            {
                System.out.println("Smaller : " + parent.getWidth() / 230);
            }
            else{
                System.out.println("Biigger : " + parent.getWidth() / 230);
            }
        }
            
//        int nCols = 2;
        nRows = size / nCols + (size % nCols == 0 ? 0 : 1);


//        int totalSize = nCols * nRows;
        int totalSize = unit;
        int currentSize = getComponentCount();
        GridLayout layout = (GridLayout) getLayout();
        layout.setColumns(Math.max(nCols, 1));
        layout.setRows(Math.max(nRows, 1));
        setPreferredSize(new Dimension(230 * nCols, 250 * nRows));
        if(parent == null)
            getParent().setMaximumSize(new Dimension(parent.getWidth(), nRows * 250));
//        setSize(new Dimension(230 * nCols, 250 * nRows));

        
//        if(parent != null)
//            parent.setPreferredSize(new Dimension(230 * nCols, 250 * nRows + 100));
        System.out.println(getPreferredSize().toString());
        System.out.println(getSize().toString());
        System.out.println("[B]Total ( " + nCols + ", " + nRows + ") " + totalSize);
        System.out.println("[B]Current " + currentSize);
        
        if(debug)
            return;
        
        
        if(currentSize == totalSize)
        {
            System.out.println("Return");
            return;
        }
        
        System.out.println("[R]Current " + getComponentCount() + "\n\n\n");

        validate();
        repaint();
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 300));
        MovieListPanel4 movieListPanel = new MovieListPanel4(10, 5);
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

        movieCard1 = new Output.MovieCard();
        movieCard3 = new Output.MovieCard();
        movieCard2 = new Output.MovieCard();
        movieCard4 = new Output.MovieCard();

        setBackground(new java.awt.Color(51, 51, 51));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        setLayout(new java.awt.GridLayout(1, 0));
        add(movieCard1);
        add(movieCard3);
        add(movieCard2);
        add(movieCard4);
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        updateMovies();
    }//GEN-LAST:event_formComponentResized


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Output.MovieCard movieCard1;
    private Output.MovieCard movieCard2;
    private Output.MovieCard movieCard3;
    private Output.MovieCard movieCard4;
    // End of variables declaration//GEN-END:variables
}
