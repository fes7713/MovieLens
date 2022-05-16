/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Icon;

import Resource.Message;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
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
    private URL url;
    
    
    public PlayIcon() {
        super();
        color = INITAL_CIRCLE_COLOR;
    }
    
    public void setLink(String url)
    {
        try {
            this.url = new URL(url);
            color = INITAL_CIRCLE_COLOR;
        } catch (MalformedURLException ex) {
            Message.ERROR.printMessage(ex);
            color = INITAL_CIRCLE_COLOR.darker();
        }
        validate();
        repaint();

    }
    
    public void setLink(URL url)
    {
        this.url = url;
        color = INITAL_CIRCLE_COLOR;
        validate();
        repaint();
    }
    
    public void setColor(Color color)
    {
        this.color = color;
    }

    @Override
    protected void drawIcon(Graphics2D g2d, int width, int height, int length, int thickness, float zoomRatio) {
        int lengthCircle = (int)(length * (1 - PADDING_CIRCLE_PERCENTAGE));
        int triangleHeight = (int)(lengthCircle / 3  * 1.732f);
        float shift = length * MAX_SHIFT_PERCENTAGE * zoomRatio;
        
        g2d.fillPolygon(
                new int[]{
                    (int)(width / 2 - lengthCircle / 3 - shift),
                    (int)(width / 2 - lengthCircle / 3 - shift),
                    (int)(width / 2 + triangleHeight - shift)}
                ,             
                new int[]{
                    (int)(height / 2 - lengthCircle / 2 - shift),
                    (int)(height / 2 + lengthCircle / 2 - shift),
                    (int)(height / 2 - shift)},
                3);
        
        g2d.drawPolygon(
                new int[]{
                    (int)(width / 2 - lengthCircle / 3 - shift),
                    (int)(width / 2 - lengthCircle / 3 - shift),
                    (int)(width / 2 + triangleHeight - shift)}
                ,             
                new int[]{
                    (int)(height / 2 - lengthCircle / 2 - shift),
                    (int)(height / 2 + lengthCircle / 2 - shift),
                    (int)(height / 2 - shift)},
                3);
    }
    
    @Override
    public void onClickAction(MouseEvent e) {
        Browser.BrowserSupport.openWebpage(url);
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