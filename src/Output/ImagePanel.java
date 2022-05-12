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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    protected String link;
    protected Image image;

    public ImagePanel() {
        this("https://image.tmdb.org/t/p/w600_and_h900_bestv2/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg");
    }
    
    public ImagePanel(String link) {
        setLink(link);
        initComponents();
        
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
        
        image = loadImage();
        
        if(image == null)
            return;
        int imageHeight = image.getHeight(this);
        int imageWidth = image.getWidth(this);
        
        int width = getWidth();
        int height = getHeight();
        
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
        this.link = link;
        validate();
        repaint();
    }
    
    public Image loadImage()
    {
        URL url;
        try {
            url = new URL(link);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
            return null;
        }
        ImageIcon icon = new ImageIcon(url);
        return icon.getImage();
    }
    
    public static void main(String[] args)
    {
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500, 500));
        ImagePanel image = new ImagePanel("https://image.tmdb.org/t/p/w600_and_h900_bestv2/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg");
        
//            cell.setBackground(new Color(34, 34, 34));
        frame.setBackground(new Color(34, 34, 34));
        frame.add(image);
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
