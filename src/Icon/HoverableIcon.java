/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Icon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author fes77
 */
public class HoverableIcon extends BaseIcon implements Hoverable{
    private boolean hover;
    private int zoomCounter;
    private AnimationType animationType;
    
    public static float MAX_SHIFT_PERCENTAGE = 0.02f;
    
    
    public HoverableIcon()
    {
        this(AnimationType.DESC_GROW);
    }
    
    public HoverableIcon(AnimationType type)
    {
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                onHoverEnterAction(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                onHoverExitAction(evt);
            }
        });
        
        hover = false;
        zoomCounter = 0;
        animationType = type;
        color = Color.WHITE;
    }
    
    public boolean isHover()
    {
        return hover;
    }
    
    private void setHover(boolean hover) {
        this.hover = hover;
        repaint();
    }

    public float getZoomRatio()
    {
        return zoomCounter / 1000f ;
    }
    
    protected void drawIcon(Graphics2D g2d, int width, int height, int  length, int  thickness, float zoomRatio)
    {
//        System.out.println("Empty icon");
    }
    
    protected void drawIconShadow(Graphics2D g2d, int width, int height, int  length, int  thickness, float zoomRatio)
    {
        drawIcon(g2d, width, height, length, thickness, zoomRatio);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        super.paintComponent(g); 
        int height = getHeight();
        int width = getWidth();
        int length = height < width ? height : width;
        int margin = (int)(length * marginPercentage);
        int thickness = (int)(length * lineThicknessPercentage);
        
        
        if(hover)
            g2d.setColor(color.darker().darker());
        else
            g2d.setColor(color);
        
        onHoverPaint(g2d);
        
        g2d.setColor(color.darker().darker());
        drawIconShadow(g2d, width, height,  length,  thickness, -getZoomRatio());
        g2d.setColor(color);
        drawIcon(g2d, width, height,  length,  thickness, getZoomRatio());
        
    } 
    
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


        g2d.drawOval(
                    (int)(bgStartX + thickness * 4 / 2 - thickness * getZoomRatio()), 
                    (int)(bgStartY + thickness * 4 / 2 - thickness * getZoomRatio()), 
                    (int)(bgSize - thickness * 4 + thickness * 2 * getZoomRatio()), 
                    (int)(bgSize - thickness * 4 + thickness * 2 * getZoomRatio()));
    }
                                                        

    @Override
    public void onHoverEnterAction(MouseEvent e) {
        setHover(true);
        if(thread.isAlive())
            try {
                thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(BaseIcon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        thread = new AnimationThread(animationType, new AnimationThread.AnimationUpdater(){
            @Override
            public void update(int returnValue) {
                zoomCounter = returnValue;
                repaint();
            }

            @Override
            public boolean isHover() {
                return HoverableIcon.this.isHover();
            }
        });
        
        thread.start();
    }

    @Override
    public void onHoverExitAction(MouseEvent e) {
        setHover(false);
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(BaseIcon.class.getName()).log(Level.SEVERE, null, ex);
        }
        zoomCounter = 0;
        repaint();
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 800));
        HoverableIcon movieListPanel = new HoverableIcon(AnimationType.DESC_GROW_SLOW);

        frame.add(movieListPanel);
        frame.setVisible(true);

    }
}


/*
case LINEAR_GROW_FIT ->{
                        start = 600;
                        target = 1200;
                        speed = 20;
                        acc = 1;
                        
                    }
                    case LINEAR_GROW_CLICK -> {
                        start = 400;
                        target = 1000;
                        speed = 30;
                        acc = 1;
                    }
                    case DESC_GROW_CLICK -> {
                                        // Grow click 37 frames Good
//                start = 400;
//                target = 1000;
//                speed = 70;
//                acc = 0.9f;
                
                // Grow click 30 frames
//                start = 400;
//                target = 1000;
//                speed = 100;
//                acc = 0.860989f;

                // Grow click 47 frames
//                start = 400;
//                target = 1000;
//                speed = 50;
//                acc = 0.9278f;
                        // Grow click 75 frames
                        start = 400;
                        target = 1000;
                        speed = 30;
                        acc = 0.95637d;
                    }
                    case LINEAR_SHRINK_CLICK -> {
                        //Shrink Click 30 frames
                        start = 1000;
                        target = 400;
                        start = 1000;
                        speed = -20;
                        acc = 1;
                    }
                    case LINEAR_SHRINK_FIT -> {
                        // Shrink fit 30 frames
                        start = 1200;
                        target = 600;
                        speed = -20;
                        acc = 1;
                    }
                    case LINEAR_GROW_FROM_0 -> {
                        // Grow water circle
                        start = 0;
                        target = 1000;
                        speed = 20;
                        acc = 1;
                    }
                    case DESC_GROW_FROM_0 -> {
                        start = 0;
                        target = 1000;
                        speed = 20;
                        acc = 0.99f;
*/