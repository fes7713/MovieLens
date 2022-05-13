/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Icon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;

/**
 *
 * @author fes77
 */
public class FavoriteIcon extends BaseIcon{

    /**
     * Creates new form FavoriteIcon
     */

    public static float PADDING_CIRCLE_PERCENTAGE = 0.75f;
    public final static Color INITAL_FOVORITE_COLOR = new Color(209, 38, 104);
    
    public FavoriteIcon() {
        super();
        color = INITAL_FOVORITE_COLOR;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2d = (Graphics2D)g;
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int height = getHeight();
        int width = getWidth();
        int length = height < width ? height : width;
        int thickness = (int)(length * INITIAL_LINE_THICKNESS_PERCENTAGE);

        int lengthCircle = (int)(length * (1d - PADDING_CIRCLE_PERCENTAGE));
        int triangleHeight = (int)(lengthCircle / 3d  * Math.sqrt(3));
        
        g2d.setColor(color);

        g2d.fillPolygon(
                new int[]{
                    (int)(width / 2d + lengthCircle / 2d),
                    (int)(width / 2d - lengthCircle / 2d),
                    (int)(width / 2d)
                }
                ,             
                new int[]{
                    (int)(height / 2d),
                    (int)(height / 2d),
                    (int)(height / 2d + triangleHeight)},
                3);
        
        g2d.drawPolygon(
                new int[]{
                    (int)(width / 2d + lengthCircle / 2d),
                    (int)(width / 2d - lengthCircle / 2d),
                    (int)(width / 2d)
                }
                ,             
                new int[]{
                    (int)(height / 2d),
                    (int)(height / 2d),
                    (int)(height / 2d + triangleHeight)},
                3);
        
        g2d.fillOval(
                (int)Math.round(width / 2d - lengthCircle * 0.025d), 
                (int)(height / 2d - lengthCircle / 2d), 
                (int)(lengthCircle * 0.7d  + lengthCircle * 0.025d), 
                (int)(lengthCircle * 0.7d  + lengthCircle * 0.025d));
        g2d.fillOval(
                (int)Math.round(width / 2d - lengthCircle  * 0.7d  - lengthCircle * 0.025d + lengthCircle * 0.025d), 
                (int)(height / 2d - lengthCircle / 2d), 
                (int)(lengthCircle * 0.7d  + lengthCircle * 0.025d), 
                (int)(lengthCircle * 0.7d  + lengthCircle * 0.025d));
        
//        g2d.setStroke(new BasicStroke(1));
//        g2d.drawLine(0, height / 2, width, height / 2);
//        g2d.drawLine(width / 2, 0, width / 2, height);
        } 

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 800));
        FavoriteIcon movieListPanel = new FavoriteIcon();

        frame.add(movieListPanel);
        frame.setVisible(true);

    }
}

