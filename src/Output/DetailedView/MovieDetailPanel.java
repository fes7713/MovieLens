/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Output.DetailedView;

import Data.Movie;
import Repository.Repository;
import Web.Scraping;
import java.awt.Dimension;
import java.util.Scanner;
import javax.swing.JFrame;
import org.jsoup.nodes.Element;

/**
 *
 * @author fes77
 */
public class MovieDetailPanel extends javax.swing.JPanel {

    /**
     * Creates new form MovieDetailPanel
     */
    private Movie movie;
    public MovieDetailPanel() {
        initComponents();
    }
    
    public MovieDetailPanel(Movie movie) {
        initComponents();
        this.movie = movie;
        prepareDetailedMovie();
    }
    
    public MovieDetailPanel(int movieId) {
        initComponents();
        
        if(Repository.isConnected() == false)
            Repository.connect(Repository.Driver.MySQL ,"movie-lens.cpzst9uo9qun.ap-northeast-1.rds.amazonaws.com", 3306, "mydb" , "root", "rsTTMA2sHyUL");
        movie = Repository.findMovieById(movieId);
        prepareDetailedMovie();
    }
    
    public void setMovie(Movie movie)
    {
        this.movie = movie;
        setTitle(movie.getTitle());
    }
    
    public void setTitle(String title)
    {
        titleLabel.setText(title);
        validate();
        repaint();
    }
    
    public void setPicture(String url)
    {
        imagePanel.setLink(url);
        validate();
        repaint();
    }
    
    public void setOverview(String overview)
    {
        overviewEditorPane.setText(overview);
        validate();
        repaint();
    }
    
    public void setYear(String year)
    {
        yearLabel.setText(year);
        validate();
        repaint();
    }
    
    public void setDuration(String duration)
    {
        durationLabel.setText(duration);
        validate();
        repaint();
    }
    
    public void setRating(float rating)
    {
        ratingCircle.setRating(rating);
        validate();
        repaint();
    }
    
    public void setRatingCount(int rating)
    {
        ratingLabel.setText(rating + " reviews");
        validate();
        repaint();
    }
    
    public void setLinks(String videoUrl, String imdbUrl, String tmdbUrl)
    {
        imdbLinkIcon.setLink(imdbUrl);
        tmdbLinkIcon.setLink(tmdbUrl);
        playIcon.setLink(videoUrl);
    }
    
    public void prepareDetailedMovie()
    {
        if(movie == null)
            return;
        int id = movie.getId();
        setTitle(movie.getTitle());
        
        
        Element body = Scraping.getTBDBBodyById(id);
        Thread thread1 = new Thread()
        {
            @Override
            public void run()
            {
                setPicture(Scraping.getPictureURL_HIGH(body, id));
            }
        };
        thread1.start();
        
        Thread thread2 = new Thread()
        {
            @Override
            public void run()
            {
                setYear(Scraping.getReleaseYearText(body, id));
                setDuration(Scraping.getDurationText(body, id));
                setOverview(Scraping.getOverview(body, id));
            }
        };
        thread2.start();
        Thread thread3 = new Thread()
        {
            @Override
            public void run()
            {
                setRating(Repository.findAverageRatingById(id));
                setRatingCount(Repository.findRateCountById(id));
                int imdbId = Repository.findImdbIdById(id);
                int tmdbId = Repository.findTmdbIdById(id);
                String videoLink = null, imdbLink = null, tmdbLink = null;
                videoLink = Scraping.getTrailerLink(id);
                if(imdbId != 0)
                    imdbLink = Scraping.IMDB_LINK + String.format("%07d", imdbId);
                if(tmdbId != 0)
                    tmdbLink = Scraping.TMDB_LINK + tmdbId;
                setLinks(videoLink, imdbLink, tmdbLink);
            }
        };
        thread3.start();
        
//        Thread thread4 = new Thread()
//        {
//            @Override
//            public void run()
//            {
//                
//            }
//        };
//        thread4.start();
        
        
        
        
        
        validate();
        repaint();
    }
    
    public void prepareDetailedMovie(int movieId)
    {
        if(Repository.isConnected() == false)
            Repository.connect(Repository.Driver.MySQL ,"movie-lens.cpzst9uo9qun.ap-northeast-1.rds.amazonaws.com", 3306, "mydb" , "root", "rsTTMA2sHyUL");
        movie = Repository.findMovieById(movieId);
        prepareDetailedMovie();
    }
    
    
    

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 800));
        frame.setVisible(true);
        MovieDetailPanel detailPanel = new MovieDetailPanel(2);

        frame.add(detailPanel);
        
        detailPanel.prepareDetailedMovie();
        Scanner sk = new Scanner(System.in);
        while(true)
        {
            detailPanel.prepareDetailedMovie(sk.nextInt());
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

        jMenuItem1 = new javax.swing.JMenuItem();
        imagePanel = new Output.ImagePanel();
        titleLabel = new javax.swing.JLabel();
        actionPanel = new javax.swing.JPanel();
        trailerPanel = new javax.swing.JPanel();
        trailerLabel = new javax.swing.JLabel();
        playIcon = new Icon.PlayIcon();
        favoritePanel = new javax.swing.JPanel();
        favoriteLabel = new javax.swing.JLabel();
        favoriteIcon = new Icon.FavoriteIcon();
        imdbPanel = new javax.swing.JPanel();
        imdbLabel = new javax.swing.JLabel();
        imdbLinkIcon = new Icon.LinkIcon();
        tmdbPanel = new javax.swing.JPanel();
        tmdbLabel = new javax.swing.JLabel();
        tmdbLinkIcon = new Icon.LinkIcon();
        overviewPanel = new javax.swing.JPanel();
        overviewEditorPane = new javax.swing.JEditorPane();
        overviewTitleLabel = new javax.swing.JLabel();
        crossIcon1 = new Icon.CrossIcon();
        homeIcon2 = new Icon.HomeIcon();
        jPanel1 = new javax.swing.JPanel();
        durationLabel = new javax.swing.JLabel();
        ratingCircle = new Icon.RatingCircle();
        yearLabel = new javax.swing.JLabel();
        ratingLabel = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 565, Short.MAX_VALUE)
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        titleLabel.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setText("Shawshanks Redemption");

        actionPanel.setLayout(new java.awt.GridLayout(2, 2));

        trailerPanel.setBackground(new java.awt.Color(51, 51, 51));
        trailerPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(8, 28, 34), 2, true));

        trailerLabel.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        trailerLabel.setForeground(new java.awt.Color(255, 255, 255));
        trailerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        trailerLabel.setText("Play Trailer");

        playIcon.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout playIconLayout = new javax.swing.GroupLayout(playIcon);
        playIcon.setLayout(playIconLayout);
        playIconLayout.setHorizontalGroup(
            playIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        playIconLayout.setVerticalGroup(
            playIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 141, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout trailerPanelLayout = new javax.swing.GroupLayout(trailerPanel);
        trailerPanel.setLayout(trailerPanelLayout);
        trailerPanelLayout.setHorizontalGroup(
            trailerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(trailerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(playIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
        );
        trailerPanelLayout.setVerticalGroup(
            trailerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trailerPanelLayout.createSequentialGroup()
                .addComponent(playIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(trailerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        actionPanel.add(trailerPanel);

        favoritePanel.setBackground(new java.awt.Color(51, 51, 51));
        favoritePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(8, 28, 34), 2, true));

        favoriteLabel.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        favoriteLabel.setForeground(new java.awt.Color(255, 255, 255));
        favoriteLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        favoriteLabel.setText("Fovorite");

        favoriteIcon.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout favoriteIconLayout = new javax.swing.GroupLayout(favoriteIcon);
        favoriteIcon.setLayout(favoriteIconLayout);
        favoriteIconLayout.setHorizontalGroup(
            favoriteIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        favoriteIconLayout.setVerticalGroup(
            favoriteIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 141, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout favoritePanelLayout = new javax.swing.GroupLayout(favoritePanel);
        favoritePanel.setLayout(favoritePanelLayout);
        favoritePanelLayout.setHorizontalGroup(
            favoritePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(favoriteLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
            .addComponent(favoriteIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
        );
        favoritePanelLayout.setVerticalGroup(
            favoritePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, favoritePanelLayout.createSequentialGroup()
                .addComponent(favoriteIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(favoriteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        actionPanel.add(favoritePanel);

        imdbPanel.setBackground(new java.awt.Color(51, 51, 51));
        imdbPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(8, 28, 34), 2, true));

        imdbLabel.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        imdbLabel.setForeground(new java.awt.Color(255, 255, 255));
        imdbLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imdbLabel.setText("IMDB");

        javax.swing.GroupLayout imdbLinkIconLayout = new javax.swing.GroupLayout(imdbLinkIcon);
        imdbLinkIcon.setLayout(imdbLinkIconLayout);
        imdbLinkIconLayout.setHorizontalGroup(
            imdbLinkIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        imdbLinkIconLayout.setVerticalGroup(
            imdbLinkIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 141, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout imdbPanelLayout = new javax.swing.GroupLayout(imdbPanel);
        imdbPanel.setLayout(imdbPanelLayout);
        imdbPanelLayout.setHorizontalGroup(
            imdbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imdbLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
            .addComponent(imdbLinkIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        imdbPanelLayout.setVerticalGroup(
            imdbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imdbPanelLayout.createSequentialGroup()
                .addComponent(imdbLinkIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imdbLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        actionPanel.add(imdbPanel);

        tmdbPanel.setBackground(new java.awt.Color(51, 51, 51));
        tmdbPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(8, 28, 34), 2, true));

        tmdbLabel.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        tmdbLabel.setForeground(new java.awt.Color(255, 255, 255));
        tmdbLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tmdbLabel.setText("TMDB");

        javax.swing.GroupLayout tmdbLinkIconLayout = new javax.swing.GroupLayout(tmdbLinkIcon);
        tmdbLinkIcon.setLayout(tmdbLinkIconLayout);
        tmdbLinkIconLayout.setHorizontalGroup(
            tmdbLinkIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        tmdbLinkIconLayout.setVerticalGroup(
            tmdbLinkIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 141, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout tmdbPanelLayout = new javax.swing.GroupLayout(tmdbPanel);
        tmdbPanel.setLayout(tmdbPanelLayout);
        tmdbPanelLayout.setHorizontalGroup(
            tmdbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tmdbLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
            .addComponent(tmdbLinkIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tmdbPanelLayout.setVerticalGroup(
            tmdbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tmdbPanelLayout.createSequentialGroup()
                .addComponent(tmdbLinkIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tmdbLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        actionPanel.add(tmdbPanel);

        overviewPanel.setOpaque(false);

        overviewEditorPane.setEditable(false);
        overviewEditorPane.setBackground(new java.awt.Color(51, 51, 51));
        overviewEditorPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        overviewEditorPane.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        overviewEditorPane.setForeground(new java.awt.Color(255, 255, 255));
        overviewEditorPane.setMargin(new java.awt.Insets(20, 20, 20, 20));

        overviewTitleLabel.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        overviewTitleLabel.setForeground(new java.awt.Color(255, 255, 255));
        overviewTitleLabel.setText("Overview");

        javax.swing.GroupLayout overviewPanelLayout = new javax.swing.GroupLayout(overviewPanel);
        overviewPanel.setLayout(overviewPanelLayout);
        overviewPanelLayout.setHorizontalGroup(
            overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overviewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(overviewTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                    .addComponent(overviewEditorPane))
                .addContainerGap())
        );
        overviewPanelLayout.setVerticalGroup(
            overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overviewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(overviewTitleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(overviewEditorPane, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout crossIcon1Layout = new javax.swing.GroupLayout(crossIcon1);
        crossIcon1.setLayout(crossIcon1Layout);
        crossIcon1Layout.setHorizontalGroup(
            crossIcon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        crossIcon1Layout.setVerticalGroup(
            crossIcon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 59, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout homeIcon2Layout = new javax.swing.GroupLayout(homeIcon2);
        homeIcon2.setLayout(homeIcon2Layout);
        homeIcon2Layout.setHorizontalGroup(
            homeIcon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        homeIcon2Layout.setVerticalGroup(
            homeIcon2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 59, Short.MAX_VALUE)
        );

        jPanel1.setOpaque(false);

        durationLabel.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        durationLabel.setForeground(new java.awt.Color(255, 255, 255));
        durationLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        durationLabel.setText("123 mins");

        javax.swing.GroupLayout ratingCircleLayout = new javax.swing.GroupLayout(ratingCircle);
        ratingCircle.setLayout(ratingCircleLayout);
        ratingCircleLayout.setHorizontalGroup(
            ratingCircleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ratingCircleLayout.setVerticalGroup(
            ratingCircleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );

        yearLabel.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        yearLabel.setForeground(new java.awt.Color(255, 255, 255));
        yearLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        yearLabel.setText("10/17/2014 (US) ");

        ratingLabel.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        ratingLabel.setForeground(new java.awt.Color(255, 255, 255));
        ratingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ratingLabel.setText("100 Reviews");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ratingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(yearLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                        .addComponent(durationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ratingCircle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(253, 253, 253)
                .addComponent(ratingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(77, 77, 77))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(ratingCircle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(50, 50, 50)
                    .addComponent(yearLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(durationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(homeIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(crossIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(actionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(overviewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(homeIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titleLabel)
                    .addComponent(crossIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(actionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(6, 6, 6)))
                        .addGap(12, 12, 12)
                        .addComponent(overviewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionPanel;
    private Icon.CrossIcon crossIcon1;
    private javax.swing.JLabel durationLabel;
    private Icon.FavoriteIcon favoriteIcon;
    private javax.swing.JLabel favoriteLabel;
    private javax.swing.JPanel favoritePanel;
    private Icon.HomeIcon homeIcon2;
    private Output.ImagePanel imagePanel;
    private javax.swing.JLabel imdbLabel;
    private Icon.LinkIcon imdbLinkIcon;
    private javax.swing.JPanel imdbPanel;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JEditorPane overviewEditorPane;
    private javax.swing.JPanel overviewPanel;
    private javax.swing.JLabel overviewTitleLabel;
    private Icon.PlayIcon playIcon;
    private Icon.RatingCircle ratingCircle;
    private javax.swing.JLabel ratingLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel tmdbLabel;
    private Icon.LinkIcon tmdbLinkIcon;
    private javax.swing.JPanel tmdbPanel;
    private javax.swing.JLabel trailerLabel;
    private javax.swing.JPanel trailerPanel;
    private javax.swing.JLabel yearLabel;
    // End of variables declaration//GEN-END:variables
}
