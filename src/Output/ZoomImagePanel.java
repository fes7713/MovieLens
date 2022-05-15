/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Output;

import Output.GridView.MovieCard;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author fes77
 */
public class ZoomImagePanel extends ImagePanel{
    private boolean zoomFlag;
    
    private final static float MARGIN_PERCENTAGE = 0.06f;
    
    public ZoomImagePanel() {
//        this("https://image.tmdb.org/t/p/w600_and_h900_bestv2/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg");
        super();
        zoomFlag = false;
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                formMouseClicked(evt);
            }
        });
    }
    
    public ZoomImagePanel(String link) {
        super(link);
        zoomFlag = false;
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                formMouseClicked(evt);
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        paintComponent_clear(g);
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
        System.out.println(zoomFlag);
        if(zoomFlag)
        {
            g2d.drawImage(image, 0, 0, getWidth(), getHeight(), sx1, sy1, sx2, sy2, this);
        }
        else
        {
            g2d.drawImage(image, 
                    (int)(width * MARGIN_PERCENTAGE), (int)(height * MARGIN_PERCENTAGE), 
                    (int)(width * (1 -  MARGIN_PERCENTAGE)), (int)(height * (1 - MARGIN_PERCENTAGE)), 
                    sx1, sy1, sx2, sy2, this);
            Point2D start = new Point2D.Float(
                    (int)(width * MARGIN_PERCENTAGE), 
                    (int)(height * MARGIN_PERCENTAGE));
            Point2D end = new Point2D.Float(
                    (int)(width * MARGIN_PERCENTAGE), 
                    (int)(height * (1 - MARGIN_PERCENTAGE)));
            float[] dist = {0.0f, 0.5f, 1f};
            Color[] colors = {new Color(0, 0, 0, 0), new Color(0, 0, 0, 150), new Color(0, 0, 0, 220)};
            LinearGradientPaint p =
                new LinearGradientPaint(start, end, dist, colors);
            g2d.setPaint(p);
            g2d.fillRect((int)(width * MARGIN_PERCENTAGE), (int)(height * MARGIN_PERCENTAGE), 
                    (int)(width * (1 - 2 * MARGIN_PERCENTAGE)), (int)(height * (1 - 2 * MARGIN_PERCENTAGE))); 
        }

        
    }
    
    public void setZoomFlag(boolean flag)
    {
        zoomFlag = flag;
        validate();
        repaint();
    }
    
    
    public static void main(String[] args)
    {
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500, 500));
        ImagePanel image = new ZoomImagePanel("https://image.tmdb.org/t/p/w600_and_h900_bestv2/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg");
        
//            cell.setBackground(new Color(34, 34, 34));
        frame.setBackground(new Color(34, 34, 34));
        frame.add(image);
        frame.setVisible(true);
        
        Scanner sk = new Scanner(System.in);
    }     

    private void formMouseEntered(java.awt.event.MouseEvent evt) {                                  
        setZoomFlag(true);
    }                                 

    private void formMouseExited(java.awt.event.MouseEvent evt) {                                 
        setZoomFlag(false);
    }
    
    private void formMouseClicked(java.awt.event.MouseEvent evt) {                                 
        
        Component parent = getParent();
        if(parent instanceof MovieCard)
        {
            parent.dispatchEvent(
                    new MouseEvent(
                            parent,
                            evt.getID(),
                            evt.getWhen(),
                            evt.getModifiers(),
                            evt.getX(),
                            evt.getY(),
                            evt.getClickCount(),
                            evt.isPopupTrigger()));
        }
    }
}
