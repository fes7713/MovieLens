/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Output.Old;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author fes77
 */
public class MovieGridScrollPanel1 extends javax.swing.JPanel {

    /**
     * Creates new form MovieScrollPanel
     */
    

    public MovieGridScrollPanel1() {
        initComponents();
        movieScrollPane.getVerticalScrollBar().addAdjustmentListener((e) -> {
            int extent = movieScrollPane.getVerticalScrollBar().getModel().getExtent();
            int value = movieScrollPane.getVerticalScrollBar().getValue()+extent;
            int max = movieScrollPane.getVerticalScrollBar().getMaximum();
            
            System.out.println("Value: " + value + " Max: " + max);
            
            if(max == value && movieListPanel.isIdle())
                movieListPanel.loadMovieCards();
        }); 
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 600));
        MovieGridScrollPanel1 movieListPanel = new MovieGridScrollPanel1();
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        movieScrollPane = new javax.swing.JScrollPane();
        movieListPanel = new Output.GridView.MovieGridPanel(20, 4);

        movieScrollPane.getVerticalScrollBar().setUnitIncrement(25);
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
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Output.GridView.MovieGridPanel movieListPanel;
    private javax.swing.JScrollPane movieScrollPane;
    // End of variables declaration//GEN-END:variables
}
