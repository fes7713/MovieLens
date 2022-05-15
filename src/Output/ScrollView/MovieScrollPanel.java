/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Output.ScrollView;

import Output.ListView.MovieListView;
import Output.ListView.SearchMovies;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author fes77
 */
public class MovieScrollPanel extends javax.swing.JPanel {

    /**
     * Creates new form MovieScrollPanel
     */
    
    public MovieScrollPanel() {
        this(5, 4, Scroll.GRID, (int start, int size)->{
            return SearchMovies.topRated(start, size);
        });
    }
    
    public MovieScrollPanel(Scroll type) {
        this(type, (int start, int size)->{
            return SearchMovies.topRated(start, size);
        });
    }
    
    public MovieScrollPanel(Scroll type, SearchMovies sm) {
        this(20, 4, type, sm);
    }
    
    public MovieScrollPanel(int size, int threadSize, Scroll type, SearchMovies sm) {
        switch(type)
        {
            case GRID->
            {
                initComponents(new Output.GridView.MovieGridPanel(size, threadSize, sm));
                movieScrollPane.getVerticalScrollBar().addAdjustmentListener((e) -> {
                    int extent = movieScrollPane.getVerticalScrollBar().getModel().getExtent();
                    int value = movieScrollPane.getVerticalScrollBar().getValue()+extent;
                    int max = movieScrollPane.getVerticalScrollBar().getMaximum();

                    System.out.println("Value: " + value + " Max: " + max);

                    if(max == value && movieListPanel.isIdle())
                        movieListPanel.loadMovieCards();
                }); 
            }
            case LINEAR -> {
                initComponents(new Output.ListView.MovieSingleListPanel(20, 4, sm));
                movieListPanel.setPreferredSize(new Dimension(20 * 230, 400));
                movieScrollPane.getHorizontalScrollBar().addAdjustmentListener((e) -> {
                    int extent = movieScrollPane.getHorizontalScrollBar().getModel().getExtent();
                    int value = movieScrollPane.getHorizontalScrollBar().getValue()+extent;
                    int max = movieScrollPane.getHorizontalScrollBar().getMaximum();

                    System.out.println("Value: " + value + " Max: " + max);

                    if(max == value && movieListPanel.isIdle())
                        movieListPanel.loadMovieCards();
                }); 
            }
            default -> throw new AssertionError(type.name());
            
        }
        
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 500));
        MovieScrollPanel movieListPanel = new MovieScrollPanel(Scroll.LINEAR);
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
    
    

//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
//        
//        setPreferredSize(new Dimension(movieListPanel.getCols() * 230, movieListPanel.getRows() * 250));
//    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents(MovieListView view) {

        movieScrollPane = new javax.swing.JScrollPane();
        movieListPanel = view;

        movieScrollPane.getVerticalScrollBar().setUnitIncrement(25);
        movieScrollPane.getHorizontalScrollBar().setUnitIncrement(25);
        movieScrollPane.setViewportView(movieListPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(movieScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(movieScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
        );
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private Output.ListView.MovieListView movieListPanel;
    private javax.swing.JScrollPane movieScrollPane;
    // End of variables declaration                   
}