/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Icon;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.swing.JFrame;

/**
 *
 * @author fes77
 */
public class LinkIcon extends ClickableIcon{
    
    public static final Color INITIAL_LINK_COLOR = new Color(209, 169, 38);
    public static float PADDING_RECT_PERCENTAGE = 0.7f;
    public static float ARROW_PERCENTAGE = 0.22f;
    public static float MAX_SHIFT_PERCENTAGE = 0.03f;
    
    public LinkIcon()
    {
        super();
        color = INITIAL_LINK_COLOR;
        
    }

    @Override
    protected void drawIconShadow(Graphics2D g2d, int width, int height, int length, int thickness, float zoomRatio) {
       float lengthRect = (length * (1 - PADDING_RECT_PERCENTAGE));
        float lengthArrow =(length * ARROW_PERCENTAGE);
        float shift = length * MAX_SHIFT_PERCENTAGE * zoomRatio;
        
        BasicStroke wideStroke = new BasicStroke((int)(thickness / 4f * 3), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2d.setStroke(wideStroke);

        // Top and right
        g2d.drawLine(
                (int)(width / 2 + lengthRect / 2 - shift), 
                (int)(height / 2 + lengthRect / 2 - shift), 
                (int)(width / 2 + lengthRect / 2 - shift), 
                (int)(height / 2 - lengthRect / 2 - shift)
        );
        g2d.drawLine(
                (int)(width / 2 + lengthRect / 2 - shift), 
                (int)(height / 2 - lengthRect / 2 - shift), 
                (int)(width / 2 - lengthRect / 2 - shift), 
                (int)(height / 2 - lengthRect / 2 - shift)
        );
        
        // bottom and left
        g2d.setStroke(new BasicStroke((int)(thickness / 4f * 3), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(
                (int)(width / 2d - lengthRect / 2d - shift), 
                (int)(height / 2d + lengthRect / 2d - shift),
                (int)(width / 2d - lengthRect / 2d - shift),
                (int)(height / 2d - lengthRect / 2d - shift)
        );
        g2d.drawLine(
                (int)(width / 2d + lengthRect / 2d - shift),
                (int)(height / 2d + lengthRect / 2d - shift),
                (int)(width / 2d - lengthRect / 2d - shift), 
                (int)(height / 2d + lengthRect / 2d - shift)
        );
        
        

    }
    
    

    @Override
    protected void drawIcon(Graphics2D g2d, int width, int height, int  length, int  thickness, float zoomRatio)
    {
        float lengthRect = (length * (1 - PADDING_RECT_PERCENTAGE));
        float lengthArrow =(length * ARROW_PERCENTAGE);
        float lengthHead = (lengthArrow / 2f);
        float shift = length * MAX_SHIFT_PERCENTAGE * zoomRatio;
        
        BasicStroke wideStroke = new BasicStroke((int)(thickness / 4f * 3), BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);
        g2d.setStroke(wideStroke);
        
//        g2d.drawLine(width / 2 - lengthRect / 2, height / 2 + lengthRect / 2, width / 2 - lengthRect / 2, height / 2 - lengthRect / 2);
//        g2d.drawLine(width / 2 + lengthRect / 2, height / 2 + lengthRect / 2, width / 2 + lengthRect / 2, height / 2 + lengthRect / 6);
//        
//        g2d.drawLine(width / 2 + lengthRect / 2, height / 2 + lengthRect / 2, width / 2 - lengthRect / 2, height / 2 + lengthRect / 2);
//        g2d.drawLine(width / 2 - lengthRect / 6, height / 2 - lengthRect / 2, width / 2 - lengthRect / 2, height / 2 - lengthRect / 2);
        

        // right
//        g2d.drawLine(
//                (int)(width / 2 + lengthRect / 2 - shift), 
//                (int)(height / 2 + lengthRect / 2 - shift), 
//                (int)(width / 2 + lengthRect / 2 - shift), 
//                (int)(height / 2 - shift)
//        );
        
        // right 
        g2d.fillPolygon(
                new int[]{
                    (int)(width / 2 + lengthRect / 2 + thickness / 8f * 3 - shift), // top right
                    (int)(width / 2 + lengthRect / 2 - thickness / 8f * 3 - shift), // bottom middle
                    (int)(width / 2 + lengthRect / 2 - thickness / 8f * 3 - shift), // bottom left
                    (int)(width / 2 + lengthRect / 2 + thickness / 8f * 3 - shift), // bottom right
                }
                ,             
                new int[]{
                    (int)(height / 2 - thickness / 4f * 3- shift), // top right
                    (int)(height / 2d - shift), // bottom middle
                    (int)(height / 2d + lengthRect / 2d - shift), // bottom left
                    (int)(height / 2d + lengthRect / 2d - shift), // bottom right
                },
                4
        );
        
        // top 
        g2d.fillPolygon(       
                new int[]{
                    (int)(width / 2d - lengthRect / 2d - shift), // bottom left
                    (int)(width / 2d - lengthRect / 2d - shift), // bottom right
                    (int)(width / 2d - shift), // bottom middle
                    (int)(width / 2d + thickness / 4d * 3 - shift), // bottom right
                }
                ,
                new int[]{
                    (int)(height / 2d - lengthRect / 2d + thickness / 8f * 3 - shift), // top right
                    (int)(height / 2d - lengthRect / 2d - thickness / 8f * 3 - shift), // bottom middle
                    (int)(height / 2d - lengthRect / 2d - thickness / 8f * 3 - shift), // bottom middle
                    (int)(height / 2d - lengthRect / 2d + thickness / 8f * 3 - shift), // bottom right
                },
                4
        );
        
        // Top
//        g2d.drawLine(
//                (int)(width / 2 - shift), 
//                (int)(height / 2 - lengthRect / 2 - shift), 
//                (int)(width / 2 - lengthRect / 2 - shift), 
//                (int)(height / 2 - lengthRect / 2 - shift)
//        );
        
        // Diagonal center with bg color
//        g2d.setStroke(new BasicStroke((int)(thickness * 2.5f), BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
//        g2d.setColor(bgColor);
//        g2d.drawLine(
//                (int)(width / 2d - lengthRect / 2d - shift + thickness * 2.5f), 
//                (int)(height / 2d + lengthRect / 2d - shift - thickness * 2.5f), 
//                (int)(width / 2d + lengthArrow / 2d + lengthHead / Math.sqrt(2) - shift), 
//                (int)(height / 2d - lengthArrow / 2d - lengthHead / Math.sqrt(2) - shift)
//        );
        
        g2d.setColor(color);
        
        wideStroke = new BasicStroke((int)(thickness / 4f * 3), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2d.setStroke(wideStroke);
        
//         bottom and left
        g2d.setStroke(new BasicStroke((int)(thickness / 4f * 3), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(
                (int)(width / 2d - lengthRect / 2d - shift), 
                (int)(height / 2d + lengthRect / 2d - shift),
                (int)(width / 2d - lengthRect / 2d - shift),
                (int)(height / 2d - lengthRect / 2d - shift)
        );
        g2d.drawLine(
                (int)(width / 2d + lengthRect / 2d - shift),
                (int)(height / 2d + lengthRect / 2d - shift),
                (int)(width / 2d - lengthRect / 2d - shift), 
                (int)(height / 2d + lengthRect / 2d - shift)
        );
        
        
        
        
        
        
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
                    (int)(width / 2d + lengthArrow - shift),
                    (int)(width / 2d + lengthArrow - shift),
                    (int)(width / 2d + lengthArrow - lengthHead - shift)
                }
                ,             
                new int[]{
                    (int)(height / 2d - lengthArrow - shift),
                    (int)(height / 2d - lengthArrow + lengthHead - shift),
                    (int)(height / 2d - lengthArrow - shift),
                },
                3);
        
        // Border for triangle
        g2d.setStroke(new BasicStroke((int)(thickness / 4f), BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
        g2d.drawPolygon(new int[]{
                    (int)(width / 2d + lengthArrow - shift),
                    (int)(width / 2d + lengthArrow - shift),
                    (int)(width / 2d + lengthArrow - lengthHead - shift)
                }
                ,             
                new int[]{
                    (int)(height / 2d - lengthArrow - shift),
                    (int)(height / 2d - lengthArrow + lengthHead - shift),
                    (int)(height / 2d - lengthArrow - shift),
                },
                3);
        
        // Diagonal center with color 
        g2d.setStroke(new BasicStroke((int)(thickness / 5f * 4), BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
        g2d.drawLine(
                (int)(width / 2d - lengthRect / 8d - shift), 
                (int)(height / 2d + lengthRect / 8d - shift), 
                (int)(width / 2d + lengthArrow / 2d + lengthHead / Math.sqrt(2) - shift), 
                (int)(height / 2d - lengthArrow / 2d - lengthHead / Math.sqrt(2) - shift)
        );
        
        g2d.setStroke(new BasicStroke((int)(1), BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
        // Uncomment it to show lines
        g2d.setColor(Color.BLACK);
        g2d.drawLine(0, height / 2, width, height / 2);
        g2d.drawLine(width / 2, 0, width / 2, height );
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
