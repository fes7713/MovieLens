/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Icon;

import static Icon.BaseIcon.INITIAL_LINE_THICKNESS_PERCENTAGE;
import Resource.Message;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author fes77
 */
public class RatingCircle extends HoverableIcon {
    private float rateOutOf10;
    
    protected float lineThickness;
    protected float fontPercentage;
    protected float ratingAnimationRatio;
    private Thread ratingAnimationThread;
    
    public Color rateColor;

    public final static Color INITAL_RATE_START_COLOR = new Color(147, 33, 208);
    public final static Color INITAL_RATE_END_COLOR = new Color(35, 219, 194);

    private final static float INITIAL_FONT_PERCENTAGE = .47f;
    private final static float INITIAL_FONT_PERCENTAGE_10 = .35f;
    
    /**
     * Creates new form RatingCircle
     */
    public RatingCircle() {
        this(7);
    }
    
    public RatingCircle(float rateOutOf10) {
        super(AnimationType.DESC_GROW);
        fontPercentage = INITIAL_FONT_PERCENTAGE;
        lineThickness = INITIAL_LINE_THICKNESS_PERCENTAGE;
        
        rateColor = INITAL_RATE_END_COLOR; 
        color = rateColor.darker().darker();
        setRating(rateOutOf10);
        ratingAnimationRatio = 0;
        ratingAnimationThread = new Thread();
    }
    
    private Color evalRateColor()
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
        int margin = (int)(length * marginPercentage);
        int fontSize = (int)(length * fontPercentage);
        
        int bgSize = length - 2 *  margin;
        int outerSize = length - 2 * (thickness + margin);
        
        int bgStartX = width / 2 - length / 2 + margin;
        int bgStartY = height / 2 - length / 2 + margin;
        int outerStartX = width / 2 - length / 2 + thickness + margin;
        int outerStartY = height / 2 - length / 2 + thickness + margin;
        
        int rateAngle = (int)(-360 * rateOutOf10 / 10);
        
        // Rating arc
        g2d.setColor(rateColor);
        if(isHover())
        {
            g2d.drawArc(
                    (int)(outerStartX + thickness / 2f + thickness / 2f * ratingAnimationRatio), 
                    (int)(outerStartY + thickness / 2f + thickness / 2f * ratingAnimationRatio), 
                    (int)(outerSize - thickness - thickness * ratingAnimationRatio), 
                    (int)(outerSize - thickness - thickness * ratingAnimationRatio), 
                    90, (int)(-360 * rateOutOf10 * ratingAnimationRatio / 10));
        }
        else{
            g2d.drawArc(
                    (int)(outerStartX + thickness / 2), 
                    (int)(outerStartY + thickness / 2), 
                    (int)(outerSize - thickness), 
                    (int)(outerSize - thickness), 90,  rateAngle );
        }

        // Rating strings
        Font fm = new Font("caribri" , Font.PLAIN , fontSize);
        g2d.setFont(fm);
        g2d.setColor(Color.WHITE);
        drawStringCenter(g2d, Math.round(rateOutOf10) + "", bgStartX + (int)(bgSize / 80f * 29), bgStartY + bgSize / 2);

        fm = new Font("caribri" , Font.PLAIN , fontSize/ 3);
        g2d.setFont(fm);
        drawStringCenter(g2d, "/ 10", bgStartX + (int)(bgSize / 20f * 13), bgStartY + (int)(bgSize / 20f * 9));
    }
    
    
    public void setRating(float rateOutOf10)
    {
        this.rateOutOf10 = rateOutOf10;
        rateColor = evalRateColor();
        if(rateOutOf10 >= 10)
            fontPercentage = INITIAL_FONT_PERCENTAGE_10;
        else
            fontPercentage = INITIAL_FONT_PERCENTAGE;
        repaint();
    }

    @Override
    public void onHoverEnterAction(MouseEvent e) {
        
        if(ratingAnimationThread.isAlive())
            try {
                ratingAnimationThread.join();
        } catch (InterruptedException ex) {
            Message.ERROR.printMessage(ex);
        }
        
        super.onHoverEnterAction(e);
        
        ratingAnimationThread = new AnimationThread(AnimationType.DESC_GROW_SLOW, new AnimationThread.AnimationUpdater(){
            @Override
            public void update(int returnValue) {
                ratingAnimationRatio = returnValue / 1000f;
                repaint();
            }

            @Override
            public boolean isHover() {
                return RatingCircle.this.isHover();
            }
        });
        
        ratingAnimationThread.start();
    }

    @Override
    public void onHoverExitAction(MouseEvent e) {
        super.onHoverExitAction(e);
        try {
            ratingAnimationThread.join();
        } catch (InterruptedException ex) {
            Message.ERROR.printMessage(ex);
        }
        ratingAnimationRatio = 0;
        repaint();
        
    }
    
    
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(100, 200));
        RatingCircle rateCircle = new RatingCircle(7);
//            cell.setBackground(new Color(34, 34, 34));
        frame.setBackground(new Color(34, 34, 34));
        frame.add(rateCircle);
        frame.setVisible(true);
        Scanner sk = new Scanner(System.in);
        while(true)
        {
            rateCircle.setRating(sk.nextInt());
        }
        
        
    }
}
