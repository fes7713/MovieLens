/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Icon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fes77
 */
public class ClickableIcon extends BaseIcon implements Clickable{
    private boolean hover;
    private int zoomCounter;
    
    
    @Override
    public void onHoverPaint(Graphics2D g2d) {
        int height = getHeight();
        int width = getWidth();
        int length = height < width ? height : width;
        int margin = (int)(length * marginPercentage);
        int thickness = (int)(length * lineThicknessPercentage);
        
        int bgSize = length - 2 *  margin;
        
        int bgStartX = width / 2 - length / 2 + margin;
        int bgStartY = height / 2 - length / 2 + margin;

        System.out.println("Hover");
        g2d.setColor(color.darker().darker());
//            g2d.setColor(color.darker().darker());

        g2d.drawOval(
                    bgStartX + (int)(thickness * 1000f / zoomCounter), 
                    bgStartY + (int)(thickness * 1000f / zoomCounter), 
                    bgSize - (int)(thickness * 2 * 1000f / zoomCounter), 
                    bgSize - (int)(thickness * 2 * 1000f / zoomCounter));
    }

    @Override
    public void onClickPaint(Graphics2D g2d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onHoverAction(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onClickAction(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public ClickableIcon()
    {
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });
        
        hover = false;
        zoomCounter = 1000;
    }
    
    private void setHover(boolean hover) {
        this.hover = hover;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        super.paintComponent(g); 
        int height = getHeight();
        int width = getWidth();
        int length = height < width ? height : width;
        int margin = (int)(length * marginPercentage);
        int padding = (int)(length * paddingPercentage);
        int thickness = (int)(length * lineThicknessPercentage);
        
        int outerSize = length - 2 * (padding + margin);

        int outerStartX = width / 2 - length / 2 + padding + margin;
        int outerStartY = height / 2 - length / 2 + padding + margin;
        
        if(hover)
        {
            onHoverPaint(g2d);
        }
        else
        {
            
            g2d.setColor(color);
//            g2d.setColor(color.darker().darker().darker());
            g2d.drawOval(
                    (int)(outerStartX + thickness / 2), 
                    (int)(outerStartY + thickness / 2), 
                    (int)(outerSize - thickness), 
                    (int)(outerSize - thickness));
            
        }
        g2d.setColor(color);
    } 
    
    
    
    
    private void formMouseEntered(java.awt.event.MouseEvent evt) {                                  
        // Fast shrink 200, > 50, 1, 40
        setHover(true);
        if(thread.isAlive())
            try {
                thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(BaseIcon.class.getName()).log(Level.SEVERE, null, ex);
        }
        thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                
                // Shrink Click 30 frames
//                int start = 1000;
//                int target = 400;
//                float speed = -20;
//                float acc = 1;
                
                // Shrink fit 30 frames
//                int start = 1200;
//                int target = 600;
//                float speed = -20;
//                float acc = 1;
                
                // Grow fit 30 frames
//                int start = 600;
//                int target = 1200;
//                float speed = 20;
//                float acc = 1;

                // Grow click 37 frames Good
//                int start = 400;
//                int target = 1000;
//                float speed = 70;
//                float acc = 0.9f;
                
                // Grow click 30 frames
//                int start = 400;
//                int target = 1000;
//                float speed = 100;
//                float acc = 0.860989f;

                // Grow click 47 frames
//                int start = 400;
//                int target = 1000;
//                float speed = 50;
//                float acc = 0.9278f;
                
                // Grow click 75 frames
                int start = 400;
                int target = 1000;
                double speed = 30;
                double acc = 0.95637d;

                // Grow water circle
//                int start = 0;
//                int target = 1000;
//                float speed = 20;
//                float acc = 0.99f;
            
                zoomCounter = start;
                int frames = 0;
                while(hover & zoomCounter * Integer.signum((int)(speed + 1)) < target * Integer.signum((int)(speed + 1)))
                {

                    try {
                        sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BaseIcon.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    speed *= acc;
                    
                    if((int)Math.abs(speed) < 1)
                        throw new IllegalArgumentException("Animation cannot complete [" + frames + "]");
                    repaint();
                    zoomCounter += (int)speed;
                    System.out.println("Counting [frame]" + zoomCounter + "[" + frames + "]");
                    frames ++ ;
                    if(frames > 200)
                        throw new IllegalArgumentException("Animation takes too many frames [" + frames + "]");
                }
                zoomCounter = target;
                System.out.println("Finished");
                
                
            }
            

        };
        thread.start();
    }                                 

    private void formMouseExited(java.awt.event.MouseEvent evt) {                                 
        setHover(false);
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(BaseIcon.class.getName()).log(Level.SEVERE, null, ex);
        }
        zoomCounter = 100;
        repaint();
        
    }                                

    private void formMouseClicked(java.awt.event.MouseEvent evt) {                                  
        
    }          
}
