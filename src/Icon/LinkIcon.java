/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Icon;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author fes77
 */
public class LinkIcon extends ClickableIcon{
    
    public static final Color INITIAL_LINK_COLOR = new Color(209, 169, 38);
    public static float PADDING_RECT_PERCENTAGE = 0.7f;
    public static float ARROW_PERCENTAGE = 0.22f;
    private URL url;
    
    public LinkIcon()
    {
        super();
        color = INITIAL_LINK_COLOR;

            color = color.darker();
//        }
    }
    
    public LinkIcon(URL url)
    {
        super();
        color = INITIAL_LINK_COLOR;
        this.url = url;
        if(url == null)
        {
            try {
                url = new URL("https://www.google.co.jp/");
            } catch (MalformedURLException ex) {
                Logger.getLogger(LinkIcon.class.getName()).log(Level.SEVERE, null, ex);
                color = color.darker();
            }
        }
    }
    
    public LinkIcon(String url)
    {
        super();
        color = INITIAL_LINK_COLOR;
        try {
            this.url = new URL(url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(LinkIcon.class.getName()).log(Level.SEVERE, null, ex);
            color = color.darker();
        }
    }
    
    public void setLink(String url)
    {
        try {
            this.url = new URL(url);
            color = INITIAL_LINK_COLOR;
        } catch (MalformedURLException ex) {
            Logger.getLogger(LinkIcon.class.getName()).log(Level.SEVERE, null, ex);
            color = INITIAL_LINK_COLOR.darker();
        }
        validate();
        repaint();

    }
    
    public void setLink(URL url)
    {
        this.url = url;
        color = INITIAL_LINK_COLOR;
        validate();
        repaint();
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
                    (int)(width / 2d + thickness / 4d * 3- shift), // bottom middle
                    (int)(width / 2d - shift), // bottom right
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
//        g2d.setColor(Color.BLACK);
//        g2d.drawLine(0, height / 2, width, height / 2);
//        g2d.drawLine(width / 2, 0, width / 2, height );
    }

    @Override
    public void onClickAction(MouseEvent e) {
        Browser.BrowserSupport.openWebpage(url);
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
