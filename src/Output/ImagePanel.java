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
    
    private String link;
    private Image image;
    
    public ImagePanel() {
        initComponents();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        image = loadImage("https://image.tmdb.org/t/p/w600_and_h900_bestv2/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg");
        
        int height = image.getHeight(this);
        int width = image.getWidth(this);
        int length = Math.min(height, width);
        float aspectRatio = height /(float)width;
        float targetRatio = getHeight() / (float)getWidth();
        int sx1 = 0;
        int sy1 = 0;
        int sx2 = width;
        int sy2 = height;

        Image cropped;
//                if(aspectRatio > targetRatio)
//        {
//            sy1 = (int)(height - width * targetRatio) / 2;
//            sy2 = (int)(sy2 + width * targetRatio);
//        }
//        else{
//            sx1 = (int)(width - height / targetRatio) / 2;
//            sx2 = (int)(sx2 + height / targetRatio);
//        }
        if(aspectRatio > targetRatio)
        {
            System.out.println("Up");
            sy1 = (int)(height - width * targetRatio) / 2;
            sy2 = (int)(width * targetRatio) + sy1;
        }
        else{
            System.out.println("Down");
            sx1 = (int)(width - height / targetRatio) / 2;
            sx2 = (int)(height / targetRatio) + sx1;
        }
        
        System.out.println("PicWidth:" + image.getWidth(this) + " PicHeight: " + image.getHeight(this));
        System.out.println("Width:" + getWidth() + " Height: " + getHeight());
        
        System.out.println("sx1:" + sx1 + " sy1:" + sy1 + " sx2:" + sx2 + " sy2:" + sy2);
        System.out.println("Diff sx:" + (sx2 - sx1) + " sy:" + (sy2 - sy1));
        System.out.println("Aspect sy/sx:" + ((sy2 - sy1)/(float)(sx2 - sx1)));
        System.out.println("Aspect Ratio:" + getHeight() / (float)getWidth());
        System.out.println("Ratio:" + getHeight() / (float)getWidth() / ((sy2 - sy1)/(float)(sx2 - sx1)));
        
        g2d.drawImage(image, 0, 0, getWidth(), getHeight(), sx1, sy1, sx2, sy2, this);
        g2d.drawOval(0, 0, width, height);
    }
    
    public Image loadImage(String link)
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
//            Image imageTrans = imageIcon.getImage(); // transform it 
//        int height = icon.getIconHeight();
//        int width = icon.getIconWidth();
//        int length = Math.min(height, width);
//        float aspectRatio = height /(float)width;
//        float targetRatio = getHeight() / (float)getWidth();
//        System.out.println(targetRatio);
////            float targetRatio = 1.5f;
//
//
//        Image cropped;
//        if(aspectRatio > targetRatio)
//        {
//            System.out.println("Up");
//            cropped = 
//                    createImage(
//                            new FilteredImageSource(
//                                    icon.getImage().getSource(),
//                                    new CropImageFilter(0, (int)(height - width * targetRatio) / 2, width, (int)(width * targetRatio))
//                            )
//                    );
//        }
//        else{
//            System.out.println("Down");
//            cropped = 
//                    createImage(
//                        new FilteredImageSource(
//                                icon.getImage().getSource(),
//                                new CropImageFilter((int)(width - height / targetRatio) / 2, 0, (int)(height / targetRatio), height)
//                            )
//                    );
//
//        }
//
//        System.out.println("Check");
//        System.out.println(getWidth());
//        System.out.println(getHeight());
////        try {
////            ImageIO.write((BufferedImage)cropped, "png", new File("new File"));
////        } catch (IOException ex) {
////            Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
////        }
    }
    
    public static void main(String[] args)
    {
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(100, 200));
        ImagePanel image = new ImagePanel();
        
//            cell.setBackground(new Color(34, 34, 34));
        frame.setBackground(new Color(34, 34, 34));
        frame.add(image);
        frame.setVisible(true);
        image.loadImage("https://image.tmdb.org/t/p/w600_and_h900_bestv2/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
