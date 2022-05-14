/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Icon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.swing.JFrame;

/**
 *
 * @author fes77
 */
public class FavoriteIcon extends ClickableIcon{

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
    protected void drawIcon(Graphics2D g2d, int width, int height, int length, int thickness, float zoomRatio) {

        int lengthCircle = (int)(length * (1d - PADDING_CIRCLE_PERCENTAGE));
        int triangleHeight = (int)(lengthCircle / 3d  * Math.sqrt(3));
        float shift = length * MAX_SHIFT_PERCENTAGE * zoomRatio;
         

        g2d.fillPolygon(
                new int[]{
                    (int)(width / 2d + lengthCircle / 2d - shift),
                    (int)(width / 2d - lengthCircle / 2d - shift),
                    (int)(width / 2d - shift)
                }
                ,             
                new int[]{
                    (int)(height / 2d - shift),
                    (int)(height / 2d - shift),
                    (int)(height / 2d + triangleHeight - shift)},
                3);
        
        g2d.drawPolygon(
                new int[]{
                    (int)(width / 2d + lengthCircle / 2d - shift),
                    (int)(width / 2d - lengthCircle / 2d - shift),
                    (int)(width / 2d - shift)
                }
                ,             
                new int[]{
                    (int)(height / 2d - shift),
                    (int)(height / 2d - shift),
                    (int)(height / 2d + triangleHeight - shift)},
                3);
        
        g2d.fillOval(
                (int)(Math.round(width / 2d - lengthCircle * 0.025d) - shift), 
                (int)(height / 2d - lengthCircle / 2d - shift), 
                (int)(lengthCircle * 0.7d  + lengthCircle * 0.025d), 
                (int)(lengthCircle * 0.7d  + lengthCircle * 0.025d));
        g2d.fillOval(
                (int)(Math.round(width / 2d - lengthCircle  * 0.7d  - lengthCircle * 0.025d + lengthCircle * 0.025d) - shift), 
                (int)(height / 2d - lengthCircle / 2d - shift), 
                (int)(lengthCircle * 0.7d  + lengthCircle * 0.025d), 
                (int)(lengthCircle * 0.7d  + lengthCircle * 0.025d));
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

