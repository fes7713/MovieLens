/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Output;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author fes77
 */
public class ImagePanel extends javax.swing.JPanel {

    /**
     * Creates new form ImagePanel
     */
    
    protected URL url;
    protected Image image;
    protected Thread loadingAnimation;
    protected int loadingCounter;
    boolean loading = false;

    public final static Color INITAL_CIRCLE_COLOR = new Color(38, 166, 209);
    
    public ImagePanel() {
//        try {
//            //        this("data/Sample movie.jpg");
//            image = ImageIO.read(new File("data/Sample movie.jpg"));
//        } catch (IOException ex) {
//            Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
    
        initComponents();
        loadingAnimation = new Thread();
    }
    
    public ImagePanel(String link) {
        setLink(link);
        initComponents();
        loadImage();
        loadingAnimation = new Thread();
    }
    
    public ImagePanel(Image image) {
        this.image = image;
        loadingAnimation = new Thread();
    }
    
    protected void paintComponent_clear(Graphics g)
    {
        super.paintComponent(g);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int width = getWidth();
        int height = getHeight();
        int length = height < width ? height : width;
        
        
        if(image == null || loadingAnimation.isAlive())
        {
//            loadImage();
//            if(image == null)
//                throw new NullPointerException("Could not get image");
            
            System.out.println("Hello");
            Color color = INITAL_CIRCLE_COLOR;
            float outerRadius = length / 3f;
            float circleRadius = length / 4f;
            for(int i = 2; i < 5; i++)
            {
                int angle = 5 * i + 1 * loadingCounter / 2;
                int x = (int)(width / 2 + outerRadius * Math.cos(Math.toRadians(angle * i)));
                int y = (int)(height / 2 + outerRadius * Math.sin(Math.toRadians(angle * i)));
                int radius = (int)(circleRadius /  i);
                g2d.setColor(color);
                g2d.fillOval(x - radius / 2, y - radius / 2, radius, radius);
                color = color.brighter().brighter();
            }
            return;
            
        }
        int imageHeight = image.getHeight(this);
        int imageWidth = image.getWidth(this);
        setPreferredSize(new Dimension(imageWidth, imageHeight));
        
        
        float aspectRatio = imageHeight /(float)imageWidth;
        float targetRatio = getHeight() / (float)getWidth();
        int sx1 = 0;
        int sy1 = 0;
        int sx2 = imageWidth;
        int sy2 = imageHeight;

        if(aspectRatio > targetRatio)
        {
//            System.out.println("Up");
            sy1 = (int)(imageHeight - imageWidth * targetRatio) / 2;
            sy2 = (int)(imageWidth * targetRatio) + sy1;
        }
        else{
//            System.out.println("Down");
            sx1 = (int)(imageWidth - imageHeight / targetRatio) / 2;
            sx2 = (int)(imageHeight / targetRatio) + sx1;
        }
        
//        System.out.println("PicWidth:" + image.getWidth(this) + " PicHeight: " + image.getHeight(this));
//        System.out.println("Width:" + getWidth() + " Height: " + getHeight());
//        
//        System.out.println("sx1:" + sx1 + " sy1:" + sy1 + " sx2:" + sx2 + " sy2:" + sy2);
//        System.out.println("Diff sx:" + (sx2 - sx1) + " sy:" + (sy2 - sy1));
//        System.out.println("Aspect sy/sx:" + ((sy2 - sy1)/(float)(sx2 - sx1)));
//        System.out.println("Aspect Ratio:" + getHeight() / (float)getWidth());
//        System.out.println("Ratio:" + getHeight() / (float)getWidth() / ((sy2 - sy1)/(float)(sx2 - sx1)));
////        
//        
//        if(zoomFlag)
            g2d.drawImage(image, 0, 0, getWidth(), getHeight(), sx1, sy1, sx2, sy2, this);
//        else
//            g2d.drawImage(image, 
//                    (int)(width * MARGIN_PERCENTAGE), (int)(height * MARGIN_PERCENTAGE), 
//                    (int)(width * (1 -  MARGIN_PERCENTAGE)), (int)(height * (1 - MARGIN_PERCENTAGE)), 
//                    sx1, sy1, sx2, sy2, this);
//        
//        if(zoomFlag == false)
//        {
//            Point2D start = new Point2D.Float(
//                    (int)(width * MARGIN_PERCENTAGE), 
//                    (int)(height * MARGIN_PERCENTAGE));
//            Point2D end = new Point2D.Float(
//                    (int)(width * MARGIN_PERCENTAGE), 
//                    (int)(height * (1 - MARGIN_PERCENTAGE)));
//            float[] dist = {0.0f, 0.5f, 1f};
//            Color[] colors = {new Color(0, 0, 0, 0), new Color(0, 0, 0, 150), new Color(0, 0, 0, 220)};
//            LinearGradientPaint p =
//                new LinearGradientPaint(start, end, dist, colors);
//            g2d.setPaint(p);
//            g2d.fillRect((int)(width * MARGIN_PERCENTAGE), (int)(height * MARGIN_PERCENTAGE), 
//                    (int)(width * (1 - 2 * MARGIN_PERCENTAGE)), (int)(height * (1 - 2 * MARGIN_PERCENTAGE))); 
//        }
    }
    
    public void setLink(String link)
    {
        try {
            url = new URL(link);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
            throw new IllegalArgumentException("Invalid link: " + link);
        }
        loadImage();
        validate();
        repaint();
    }
    
    public void setLink(URL url)
    {
        this.url = url;
        loadImage();
        validate();
        repaint();
    }
    
    public void loadImage()
    {
        loading = true;
        loadingAnimation = new Thread()
        {
            @Override
            public void run()
            {
                loadingCounter = 0;
                while(loading)
                {
                    loadingCounter ++;
                    try {
                        sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    repaint();
                }
                System.out.println("Animation end");
                loadingCounter = 0;
            }
        };
        loadingAnimation.start();
        
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                ImageIcon icon;
                if(url != null)
                    icon = new ImageIcon(url);
                else
                {
                    try {
                        icon = new ImageIcon(new URL("data/Sample movie.jpg"));
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
                        throw new IllegalArgumentException("Invalid link: " + "data/Sample movie.jpg");
                    }
                }
                image = icon.getImage();
                loading = false;
                System.out.println("Finished loading pic");
                try {
                    loadingAnimation.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        thread.start();
        
    }
    
    public static void main(String[] args)
    {
        Image image;
                try {
            //        this("data/Sample movie.jpg");
            image = ImageIO.read(new File("data/Sample movie.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500, 500));
        ImagePanel imagePanel = new ImagePanel(image);
        
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

        setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 299, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 243, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
