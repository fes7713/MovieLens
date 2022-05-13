/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Icon;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;

/**
 *
 * @author fes77
 */
public class LinkIcon extends BaseIcon{
    
    public static final Color INITIAL_LINK_COLOR = new Color(209, 169, 38);
    public static float PADDING_RECT_PERCENTAGE = 0.7f;
    public static float ARROW_PERCENTAGE = 0.22f;
    
    public LinkIcon()
    {
        color = INITIAL_LINK_COLOR;
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int height = getHeight();
        int width = getWidth();
        int length = height < width ? height : width;
        int lengthRect = (int)(length * (1 - PADDING_RECT_PERCENTAGE));
        int lengthArrow = (int)(length * ARROW_PERCENTAGE);
        int lengthHead = (int)(lengthArrow / 2d);
        int thickness = (int)(length * lineThicknessPercentage);
        
        BasicStroke wideStroke = new BasicStroke((int)(thickness / 4f * 3), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2d.setStroke(wideStroke);
        
//        g2d.drawLine(width / 2 - lengthRect / 2, height / 2 + lengthRect / 2, width / 2 - lengthRect / 2, height / 2 - lengthRect / 2);
//        g2d.drawLine(width / 2 + lengthRect / 2, height / 2 + lengthRect / 2, width / 2 + lengthRect / 2, height / 2 + lengthRect / 6);
//        
//        g2d.drawLine(width / 2 + lengthRect / 2, height / 2 + lengthRect / 2, width / 2 - lengthRect / 2, height / 2 + lengthRect / 2);
//        g2d.drawLine(width / 2 - lengthRect / 6, height / 2 - lengthRect / 2, width / 2 - lengthRect / 2, height / 2 - lengthRect / 2);
        
        
        // Top and right
        g2d.drawLine(width / 2 + lengthRect / 2, height / 2 + lengthRect / 2, width / 2 + lengthRect / 2, height / 2 - lengthRect / 2);
        g2d.drawLine(width / 2 + lengthRect / 2, height / 2 - lengthRect / 2, width / 2 - lengthRect / 2, height / 2 - lengthRect / 2);
        
        // Diagonal center with bg color
        g2d.setStroke(new BasicStroke((int)(thickness * 2.5f), BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
        g2d.setColor(bgColor);
        g2d.drawLine(
                (int)(width / 2d - lengthRect / 2d), 
                (int)(height / 2d + lengthRect / 2d), 
                (int)(width / 2d + lengthArrow / 2d + lengthHead / Math.sqrt(2)), 
                (int)(height / 2d - lengthArrow / 2d - lengthHead / Math.sqrt(2)));
        g2d.setColor(color);
        
        // bottom and left
        g2d.setStroke(new BasicStroke((int)(thickness / 4f * 3), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(
                (int)(width / 2d - lengthRect / 2d), 
                (int)(height / 2d + lengthRect / 2d),
                (int)(width / 2d - lengthRect / 2d),
                (int)(height / 2d - lengthRect / 2d));
        g2d.drawLine(
                (int)(width / 2d + lengthRect / 2d),
                (int)(height / 2d + lengthRect / 2d),
                (int)(width / 2d - lengthRect / 2d), 
                (int)(height / 2d + lengthRect / 2d));
        
        
        
        
        
        
//        g2d.drawPolygon(new int[]{
//                    (int)(width / 2d - lengthRect / 2),
//                    (int)(width / 2d + lengthArrow),
//                    (int)(width / 2d + lengthArrow),
//                    (int)(width / 2d + lengthArrow - lengthHead),
//                    (int)(width / 2d + lengthArrow)
//                }
//                ,             
//                new int[]{
//                    (int)(height / 2d + lengthRect / 2),
//                    (int)(height / 2d - lengthArrow),
//                    (int)(height / 2d - lengthArrow + lengthHead),
//                    (int)(height / 2d - lengthArrow),
//                    (int)(height / 2d - lengthArrow)
//                },
//                5);
        
        // Triangle on top right
        g2d.fillPolygon(new int[]{
                    (int)(width / 2d + lengthArrow),
                    (int)(width / 2d + lengthArrow),
                    (int)(width / 2d + lengthArrow - lengthHead)
                }
                ,             
                new int[]{
                    (int)(height / 2d - lengthArrow),
                    (int)(height / 2d - lengthArrow + lengthHead),
                    (int)(height / 2d - lengthArrow),
                },
                3);
        
        // Border for triangle
        g2d.setStroke(new BasicStroke((int)(thickness / 4f), BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
        g2d.drawPolygon(new int[]{
                    (int)(width / 2d + lengthArrow),
                    (int)(width / 2d + lengthArrow),
                    (int)(width / 2d + lengthArrow - lengthHead)
                }
                ,             
                new int[]{
                    (int)(height / 2d - lengthArrow),
                    (int)(height / 2d - lengthArrow + lengthHead),
                    (int)(height / 2d - lengthArrow),
                },
                3);
        
        // Diagonal center with color 
        g2d.setStroke(new BasicStroke((int)(thickness / 5f * 4), BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
        g2d.drawLine(
                (int)(width / 2d - lengthRect / 8d), 
                (int)(height / 2d + lengthRect / 8d), 
                (int)(width / 2d + lengthArrow / 2d + lengthHead / Math.sqrt(2)), 
                (int)(height / 2d - lengthArrow / 2d - lengthHead / Math.sqrt(2)));
        
    }
    
    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 800));
        LinkIcon movieListPanel = new LinkIcon();

        frame.add(movieListPanel);
        frame.setVisible(true);

    }
}
