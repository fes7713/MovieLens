/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Output.StackView;

import Output.DetailedView.MovieDetailPanel;
import Output.DetailedView.MovieScrollDetailPanel;
import Output.ListView.MovieTopicListPanel;
import Output.ScrollView.Scroll;
import Repository.SearchMovies;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author fes77
 */
public class MovieStackedPanel extends javax.swing.JPanel {
    public static MovieStackedPanel STACK_PANEL = null; 
    /**
     * Creates new form MovieStackedPanel
     */
    CardLayout layout;
    Stack<JPanel> movieStack;
    
    public MovieStackedPanel() {
        this(Scroll.GRID);
    }
    
    public MovieStackedPanel(Scroll type) {
        this(type, (int start, int size)->{
            return SearchMovies.tagMostMatch(1, start, size);
        });
    }
    
    public MovieStackedPanel(Scroll type, SearchMovies sm) {
        initComponents();
//        add(new MovieScrollPanel(type, sm));
        add(new MovieTopicListPanel(Scroll.GRID, 22));
        layout = (CardLayout)getLayout();
        movieStack = new Stack<>();
        STACK_PANEL = this;
    }
    
//    
//    
//     public MovieScrollPanel(MovieScrollPanel.Scroll type, SearchMovies sm) {
//        switch(type)
//        {
//            case GRID->
//            {
//                initComponents(new Output.GridView.MovieGridPanel(20, 4, sm));
//                movieScrollPane.getVerticalScrollBar().addAdjustmentListener((e) -> {
//                    int extent = movieScrollPane.getVerticalScrollBar().getModel().getExtent();
//                    int value = movieScrollPane.getVerticalScrollBar().getValue()+extent;
//                    int max = movieScrollPane.getVerticalScrollBar().getMaximum();
//
//                    System.out.println("Value: " + value + " Max: " + max);
//
//                    if(max == value && movieListPanel.isIdle())
//                        movieListPanel.loadMovieCards();
//                }); 
//            }
//            case LINEAR -> {
//                initComponents(new Output.ListView.MovieSingleListPanel(20, 4, sm));
//                movieScrollPane.getHorizontalScrollBar().addAdjustmentListener((e) -> {
//                    int extent = movieScrollPane.getHorizontalScrollBar().getModel().getExtent();
//                    int value = movieScrollPane.getHorizontalScrollBar().getValue()+extent;
//                    int max = movieScrollPane.getHorizontalScrollBar().getMaximum();
//
//                    System.out.println("Value: " + value + " Max: " + max);
//
//                    if(max == value && movieListPanel.isIdle())
//                        movieListPanel.loadMovieCards();
//                }); 
//            }
//            default -> throw new AssertionError(type.name());
//            
//        }
//        
//    }
//    
//    public MovieGridPanel(int size, int threadSize, SearchMovies sm) {
//        initComponents();
//        removeAll();
//
////        GridLayout layout = (GridLayout) getLayout();
////        layout.setColumns(Math.max(getWidth() / 230, 1));
////        layout.setRows(Math.max(getHeight() / 250, 1));
//        
//        if(Repository.isConnected() == false)
//            Repository.connect(Repository.Driver.MySQL ,"movie-lens.cpzst9uo9qun.ap-northeast-1.rds.amazonaws.com", 3306, "mydb" , "root", "rsTTMA2sHyUL");
//        
//        movies = new LinkedList();
//        executor = Executors.newFixedThreadPool(threadSize);
//        this.unit = size;
//        searchAction = sm;
//        loadMovieCards(sm);
//        validate();
//        repaint();
//    }
    
    public void addMovie(int movieId)
    {
        MovieScrollDetailPanel panel = new MovieScrollDetailPanel(movieId);
        movieStack.push(panel);
//        layout.addLayoutComponent(panel, panel.toString());
////        layout.show(this, panel.toString());
//        layout.next(this);
        add(panel);
        layout.last(this);
    }
    
    public void previous()
    {
        if(movieStack.size() <= 1)
        {
            System.out.println("Cannot pop last element ");
            return;
        }
        JPanel tagFirst = movieStack.pop();
        layout.removeLayoutComponent(tagFirst);
        
    }
    
    public void removeComponent()
    {
        layout.removeLayoutComponent(this);
    }
    
    public void removeTop()
    {
        JPanel panel = movieStack.pop();
        MovieStackedPanel.STACK_PANEL.remove(panel);
        System.out.println("Removing top");
    }
    
    public void reset()
    {
        removeAll();
        add(new MovieTopicListPanel(Scroll.GRID, 22));
        validate();
        repaint();
    }
    
    public void reset(JPanel panel)
    {
        removeAll();
        add(panel);
        validate();
        repaint();
    }
    
    public void backHome()
    {
//        while(movieStack.size() > 1)
//            removeTop();
        layout.first(this);
    }
    
    public Component peekTop()
    {
        return movieStack.peek();
        
        
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 800));
        MovieStackedPanel stackPanel = new MovieStackedPanel(Scroll.GRID);
//            cell.setBackground(new Color(34, 34, 34));
//        frame.setBackground(new Color(34, 34, 34));
        frame.add(stackPanel);
        frame.setVisible(true);
        Scanner sk = new Scanner(System.in);
        while(true)
        {
            int i;
            if((i = sk.nextInt()) != 0)
            {
                stackPanel.add(new MovieDetailPanel(i));
                ((CardLayout)stackPanel.getLayout()).next(stackPanel);
                System.out.println(stackPanel.getComponentCount());
            }
            else{
//                ((CardLayout)stackPanel.getLayout()).previous(stackPanel);
                stackPanel.remove(stackPanel.getComponentCount() - 1);
                System.out.println(stackPanel.getComponentCount());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.CardLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
