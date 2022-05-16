/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Output;

import Data.Movie;
import Output.StackView.MovieStackedPanel;
import Repository.Repository;
import Web.Scraping;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import org.jsoup.nodes.Element;

/**
 *
 * @author fes77
 */
public class MovieCard extends javax.swing.JPanel {

    /**
     * Creates new form MovieCard
     */
    private Movie movie;
    private Element body;
    
    public MovieCard() {
        super();
        initComponents();
    }
    
    public MovieCard(int movieId) {
        this(Repository.findMovieById(movieId));
    }
    
    public MovieCard(Movie m) {
        super();
        initComponents();
        
        setMovie(m);
    }

    public void setMovie(Movie m)
    {
        movie = m;
        
        setPreferredSize(new Dimension(230, 500));
        if(movie != null)
        {
            
            body = Scraping.getTBDBBodyById(movie.getId());
            imagePanel.setLink(Scraping.getPictureURL(body, movie.getId()));
            String title = movie.getTitle();
            if(title.length() > 23)
                title = title.substring(0, 23) + "...";
            titleLabel.setText(title);  
            yearLabel.setText(Scraping.getReleaseYearText(body, movie.getId()));  
            durationLabel.setText(Scraping.getDurationText(body, movie.getId()));  
            ratingCircle.setRating(Repository.findAverageRatingById(movie.getId()));
        }
    }
    
    public static void main(String[] args)
    {
        Repository.connect(Repository.Driver.MySQL ,"movie-lens.cpzst9uo9qun.ap-northeast-1.rds.amazonaws.com", 3306, "mydb" , "root", "rsTTMA2sHyUL");
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(100, 200));
        MovieCard movieCard = new MovieCard(2075);
        frame.setBackground(new Color(34, 34, 34));
        frame.add(movieCard);
        frame.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        titleLabel = new javax.swing.JLabel();
        imagePanel = new Output.ZoomImagePanel();
        ratingCircle = new Icon.RatingCircle();
        yearLabel = new javax.swing.JLabel();
        durationLabel = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(51, 51, 51));
        setMinimumSize(new java.awt.Dimension(230, 250));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        titleLabel.setBackground(new java.awt.Color(255, 0, 51));
        titleLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 153, 51));
        titleLabel.setText("Loading Movie Card");
        titleLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        ratingCircle.setMaximumSize(new java.awt.Dimension(150, 150));

        javax.swing.GroupLayout ratingCircleLayout = new javax.swing.GroupLayout(ratingCircle);
        ratingCircle.setLayout(ratingCircleLayout);
        ratingCircleLayout.setHorizontalGroup(
            ratingCircleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 104, Short.MAX_VALUE)
        );
        ratingCircleLayout.setVerticalGroup(
            ratingCircleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 108, Short.MAX_VALUE)
        );

        yearLabel.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        yearLabel.setForeground(new java.awt.Color(255, 255, 255));
        yearLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        yearLabel.setText("10/17/2014 (US) ");

        durationLabel.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        durationLabel.setForeground(new java.awt.Color(255, 255, 255));
        durationLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        durationLabel.setText("123 mins");

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(yearLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(durationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ratingCircle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePanelLayout.createSequentialGroup()
                .addContainerGap(275, Short.MAX_VALUE)
                .addComponent(yearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(durationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ratingCircle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if(MovieStackedPanel.STACK_PANEL != null && movie != null)
        {
            System.out.println("Adding movie detail pane to stack");
            MovieStackedPanel.STACK_PANEL.addMovie(movie.getId());
        }
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel durationLabel;
    private Output.ImagePanel imagePanel;
    private javax.swing.JDialog jDialog1;
    private Icon.RatingCircle ratingCircle;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel yearLabel;
    // End of variables declaration//GEN-END:variables
}
