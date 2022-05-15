/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Output.ListView;

import Data.Movie;
import Output.GridView.MovieCard;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;

/**
 *
 * @author fes77
 */
public class RelatedMoviePanel1 extends javax.swing.JPanel {

    /**
     * Creates new form RelatedMoviePanel
     */
    public RelatedMoviePanel1() {
        initComponents();
//        relatedMoviesScrollPanel.scrollRectToVisible(focused);

    }
    
    public RelatedMoviePanel1(Movie m) {
        initComponents();
        
        DefaultListModel<MovieCard> model = new DefaultListModel();
        model.addElement(new MovieCard());
        model.addElement(new MovieCard());
        model.addElement(new MovieCard());
        model.addElement(new MovieCard());
        model.addElement(new MovieCard());
        model.addElement(new MovieCard());
        model.addElement(new MovieCard());
        model.addElement(new MovieCard());
        
        

        jList1.setModel(model);
//        relatedMoviesScrollPanel.scrollRectToVisible(focused);
        

    }
    
    public static void main(String[] args)
    {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500, 500));
        RelatedMoviePanel1 imagePanel = new RelatedMoviePanel1();
        
//            cell.setBackground(new Color(34, 34, 34));
        frame.setBackground(new Color(34, 34, 34));
        frame.add(imagePanel);
        frame.setVisible(true);
        
        Scanner sk = new Scanner(System.in);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        crossIcon2 = new Icon.CrossIcon();
        jScrollPane1 = new javax.swing.JScrollPane();
        controlePanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = jList1 = new javax.swing.JList<>();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout crossIcon2Layout = new javax.swing.GroupLayout(crossIcon2);
        crossIcon2.setLayout(crossIcon2Layout);
        crossIcon2Layout.setHorizontalGroup(
            crossIcon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        crossIcon2Layout.setVerticalGroup(
            crossIcon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout controlePanelLayout = new javax.swing.GroupLayout(controlePanel);
        controlePanel.setLayout(controlePanelLayout);
        controlePanelLayout.setHorizontalGroup(
            controlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 531, Short.MAX_VALUE)
        );
        controlePanelLayout.setVerticalGroup(
            controlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );

        jList1.setModel(new javax.swing.AbstractListModel<MovieCard>() {

            public int getSize() { return 0; }
            public MovieCard getElementAt(int i) { return null; }
        });
        jScrollPane2.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(controlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(controlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel controlePanel;
    private Icon.CrossIcon crossIcon2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JList<MovieCard> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
