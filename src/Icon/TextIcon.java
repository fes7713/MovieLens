/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Icon;

import Data.Genre;
import Output.ListView.MovieTopicListPanel;
import Output.ScrollView.Scroll;
import Output.StackView.MovieStackedPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

/**
 *
 * @author fes77
 */
public class TextIcon extends RectBaseIcon implements Clickable{

    private final static float INITIAL_FONT_PERCENTAGE = .47f;
    
    public final static Color INITAL_RATE_START_COLOR = new Color(147, 33, 208);
    public final static Color INITAL_RATE_END_COLOR = new Color(35, 219, 194);
    
    protected String text;
    private Genre genre;
    public TextIcon() {
        this("Action", Color.WHITE);
    }
    
    public TextIcon(Genre genre)
    {
        this(genre, new Color(212, 212, 212));

    }
    public TextIcon(String text, Color color) {
        this.text = text;
        this.color = color;
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                onHoverEnterAction(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                onHoverExitAction(evt);
            }
            public void mouseClicked(MouseEvent e) {
                onClickAction(e);
            }
        });
    }
    
    public TextIcon(Genre genre, Color color) {
        this(genre.toString(), color);
        this.genre = genre;
        
    }

    public TextIcon(AnimationType type, String text, Color color) {
        super(type);
        this.text = text;
        this.color = color;
        setPreferredSize(new Dimension(80, 50));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                onHoverEnterAction(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                onHoverExitAction(evt);
            }
            public void mouseClicked(MouseEvent e) {
                onClickAction(e);
            }
        });
    }
    
    public static Color evalRateColor(float rateOutOf10)
    {
        float[] startColorHSV = Color.RGBtoHSB(INITAL_RATE_START_COLOR.getRed(), INITAL_RATE_START_COLOR.getGreen(), INITAL_RATE_START_COLOR.getBlue(), null);
        float[] endColorHSV = Color.RGBtoHSB(INITAL_RATE_END_COLOR.getRed(), INITAL_RATE_END_COLOR.getGreen(), INITAL_RATE_END_COLOR.getBlue(), null);
        float h = (1 - startColorHSV[0] + endColorHSV[0]) * Math.max(Math.min(rateOutOf10, 8), 2)/ 10 - 1 + startColorHSV[0];
        if(h < 0)
            h += 1;
        
        return Color.getHSBColor(h, startColorHSV[1], startColorHSV[2]);
    }
    
    public void drawStringCenter(Graphics g,String text,int x,int y){
        FontMetrics fm = g.getFontMetrics();
        Rectangle rectText = fm.getStringBounds(text, g).getBounds();
        x=x-rectText.width/2;
        y=y-rectText.height/2+fm.getMaxAscent();
		
        g.drawString(text, x, y);
    }
      
    @Override
    protected void drawIcon(Graphics2D g2d, int width, int height, int length, int thickness, float zoomRatio) {
       // Rating strings
        int fontSize = (int)(length * INITIAL_FONT_PERCENTAGE);
        int margin = (int)(length * marginPercentage);
        float shift = length * MAX_SHIFT_PERCENTAGE * zoomRatio;
        
        int bgSize = length - 2 *  margin;
        
        int bgStartX = (int)(width / 2f - length / 2f + margin);
        int bgStartY = (int)(height / 2f - length / 2f + margin);
        
        Font fm = new Font("caribri" , Font.BOLD , (int)(length / 5f));
        int widthStr = g2d.getFontMetrics().stringWidth("Action");
        g2d.setFont(fm);
        drawStringCenter(
                g2d, 
                text, 
                (int)(bgStartX + bgSize / 2f - shift), 
                (int)(bgStartY + bgSize / 2f - shift));

    
    }
    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 800));
        TextIcon movieListPanel = new TextIcon();

        frame.add(movieListPanel);
        frame.setVisible(true);

    }

    @Override
    public void onClickPaint(Graphics2D g2d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onClickAction(MouseEvent e) {
//        super.onClickAction(e);
//         System.out.println("nmnnn");
        if(MovieStackedPanel.STACK_PANEL != null)
        {
                
//            Component parent = getParent().getParent().getParent().getParent().getParent();
//            System.out.println(parent.getClass().getSimpleName());
            
//            if(parent instanceof MovieScrollDetailPanel)
//            {
                if(genre != null)
                    MovieStackedPanel.STACK_PANEL.reset(new MovieTopicListPanel(Scroll.GRID, genre, 22));
                else{
                    MovieStackedPanel.STACK_PANEL.reset(new MovieTopicListPanel(Scroll.GRID, text, 22));
                }
//            }
                
                
        }
    
    }
    
}
