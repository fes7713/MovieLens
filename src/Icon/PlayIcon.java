/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Icon;

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
public class PlayIcon extends ClickableIcon {

    /**
     * Creates new form PlayIcon
     */
    
    
    public static float PADDING_CIRCLE_PERCENTAGE = 0.75f;
    public final static Color INITAL_CIRCLE_COLOR = new Color(38, 166, 209);

    
    
    public PlayIcon() {
        super();
        color = INITAL_CIRCLE_COLOR;
    }
    
    public void setColor(Color color)
    {
        this.color = color;
    }

   @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int height = getHeight();
        int width = getWidth();
        int length = height < width ? height : width;

        int lengthCircle = (int)(length * (1 - PADDING_CIRCLE_PERCENTAGE));
        int triangleHeight = (int)(lengthCircle / 3  * 1.732f);

        
        g2d.fillPolygon(
                new int[]{
                    (int)(width / 2 - lengthCircle / 3),
                    (int)(width / 2 - lengthCircle / 3),
                    (int)(width / 2 + triangleHeight)}
                ,             
                new int[]{
                    (int)(height / 2 - lengthCircle / 2),
                    (int)(height / 2 + lengthCircle / 2),
                    (int)(height / 2)},
                3);
        
        g2d.drawPolygon(
                new int[]{
                    (int)(width / 2 - lengthCircle / 3),
                    (int)(width / 2 - lengthCircle / 3),
                    (int)(width / 2 + triangleHeight)}
                ,             
                new int[]{
                    (int)(height / 2 - lengthCircle / 2),
                    (int)(height / 2 + lengthCircle / 2),
                    (int)(height / 2)},
                3);
       
        } 

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 800));
        PlayIcon movieListPanel = new PlayIcon();

        frame.add(movieListPanel);
        frame.setVisible(true);

    }
}