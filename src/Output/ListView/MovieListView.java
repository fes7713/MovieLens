/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Output.ListView;

import Repository.SearchMovies;
import Data.Movie;
import Output.GridView.MovieCard;
import Output.GridView.MovieCardProducer;
import Output.GridView.MovieGridPanel;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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
public abstract class MovieListView  extends javax.swing.JPanel{

    /**
     * Creates new form MovieGridPanel
     */
    List<MovieCard> movies;
    ExecutorService executor;
    int unit;
    int size;
    int nCols;
    int nRows;
    SearchMovies searchAction;
    
    boolean debug = false;
    
    public MovieListView()
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
        validate();
        repaint();
    }
    
    public MovieListView(int size, int threadSize) {
        this(size, threadSize, (int start, int unitSize)->{
            return SearchMovies.topRated(start, size);
        });
    }
    
    public MovieListView(int size, int threadSize, SearchMovies sm) {
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
        searchAction = sm;
        loadMovieCards(sm);
        validate();
        repaint();
    }
    
    
    public void setParent(Container parent)
    {
//        this.parent = parent;
    }
    
    public boolean isIdle()
    {
        return size == movies.size();
    }
    
    public void clearList(){
        removeAll();
        movies.clear();
        
    }
    public void loadMovieCards()
    {
        if(searchAction != null)
        {
            loadMovieCards(searchAction);
            return;
        }
        
        // TODO May need to rtemove this latyer
        setPreferredSize(null);
        int listSize = movies.size();
//        List<Integer> addingMovies = search(size, unit);
        List<Integer> addingMovies = Repository.findTopRatedMovieIds(size, unit);
        size += unit;
        
        
        
        for (int i = 0; i < addingMovies.size(); i++) {

            executor.execute(new MovieCardProducer(addingMovies.get(i), movies, this, true));
            System.out.println("Adding[" + i +  "] into list");
        }
    }
    
    public void loadMovieCards(SearchMovies sm) {
        searchAction = sm;
        int listSize = movies.size();
        List<Integer> addingMovies = sm.search(size, unit);
//        List<Integer> addingMovies = Repository.findTopRatedMovieIds(size, unit);
        size += unit;
        
        for (int i = 0; i < addingMovies.size(); i++) {

            executor.execute(new MovieCardProducer(addingMovies.get(i), movies, this, true));
            System.out.println("Adding[" + i +  "] into list");
        }
    }
    
    public void updateMovies() {
        // This is very important code to fix the issue where new loaded movies do not show on screen when screen is scrolled to the end.
        setPreferredSize(new Dimension(size * 230, 400));
//       
//            
//            nCols = Math.max(Math.min(getWidth(), getParent().getWidth()) / 230, 1);
//
//            
////        int nCols = 2;
//        nRows = size / nCols + (size % nCols == 0 ? 0 : 1);
//
//
////        int totalSize = nCols * nRows;
//        int totalSize = unit;
//        int currentSize = getComponentCount();
//        GridLayout layout = (GridLayout) getLayout();
//        layout.setColumns(Math.max(nCols, 1));
//        layout.setRows(Math.max(nRows, 1));
//        setPreferredSize(new Dimension(230 * nCols, 250 * nRows));
////        setSize(new Dimension(230 * nCols, 250 * nRows));
//
//        
////        if(parent != null)
////            parent.setPreferredSize(new Dimension(230 * nCols, 250 * nRows + 100));
//        System.out.println(getPreferredSize().toString());
//        System.out.println(getSize().toString());
//        System.out.println("[B]Total ( " + nCols + ", " + nRows + ") " + totalSize);
//        System.out.println("[B]Current " + currentSize);
//        
//        if(debug)
//            return;
//        
//        
//        if(currentSize == totalSize)
//        {
//            System.out.println("Return");
//            return;
//        }
//        
//        System.out.println("[R]Current " + getComponentCount() + "\n\n\n");

        validate();
        repaint();
    }
    
    protected abstract void initComponents();
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 300));
        MovieGridPanel movieListPanel = new MovieGridPanel(10, 5, (int start, int size)->{
            return Repository.findTopRatedMovieIds(start, size);
        });
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
}
