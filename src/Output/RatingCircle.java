/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Output;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author fes77
 */
public class RatingCircle extends javax.swing.JPanel {
    private float rateOutOf10;
    
    protected float cornerRadiusPercentage;
    protected float marginPercentage;
    protected float paddingPercentage;
    protected float lineThickness;
    protected float fontPercentage;

    
    public Color bgColor;
    public Color rateColor;

    public final static Color INITIAL_BG_COLOR = new Color(8,28,34);
    public final static Color INITAL_RATE_START_COLOR = new Color(147, 33, 208);
    public final static Color INITAL_RATE_END_COLOR = new Color(35, 219, 194);

    private final static Color INITIAL_CROSS_COLOR = new Color(67, 167, 193);
    private final static Color INITIAL_CIRCLE_COLOR = new Color(213, 82, 54);
    private final static float INITIAL_LINE_THICKNESS_PERCENTAGE = .07f;
    private final static float INITIAL_MARGINE_PERCENTAGE = .01f;
    private final static float INITIAL_PADDING_PERCENTAGE = .08f;
    private final static float INITIAL_CORNER_RADIUS_PERCENTAGE = .1f;
    private final static float INITIAL_FONT_PERCENTAGE = .47f;
    private final static float INITIAL_FONT_PERCENTAGE_10 = .35f;
    
    /**
     * Creates new form RatingCircle
     */
    public RatingCircle() {
        this(7);
    }
    
    public RatingCircle(float rateOutOf10) {
        super();
        initComponents();
        cornerRadiusPercentage = INITIAL_CORNER_RADIUS_PERCENTAGE;
        marginPercentage = INITIAL_MARGINE_PERCENTAGE;
        paddingPercentage = INITIAL_PADDING_PERCENTAGE;
        lineThickness = INITIAL_LINE_THICKNESS_PERCENTAGE;
        fontPercentage = INITIAL_FONT_PERCENTAGE;
        bgColor = INITIAL_BG_COLOR;
        rateColor = INITAL_RATE_END_COLOR; 
        
        setRating(rateOutOf10);
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
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int height = getHeight();
        int width = getWidth();
        int length = height < width ? height : width;
        int margin = (int)(length * marginPercentage);
        int padding = (int)(length * paddingPercentage);
        int thickness = (int)(length * lineThickness);
        int fontSize = (int)(length * fontPercentage);
        
        int bgSize = length - 2 *  margin;
        int outerSize = length - 2 * (padding + margin);
        int innerSize = length - 2 * (padding + margin + thickness);
        
        int bgStartX = width / 2 - length / 2 + margin;
        int bgStartY = height / 2 - length / 2 + margin;
        int outerStartX = width / 2 - length / 2 + padding + margin;
        int outerStartY = height / 2 - length / 2 + padding + margin;
        int innerStartX = width / 2 - length / 2 + padding + margin + thickness;
        int innerStartY = height / 2 - length / 2 + padding + margin + thickness;
        
        int rateAngle = (int)(-360 * rateOutOf10 / 10);
        
        
//        g2d.setStroke(new BasicStroke((int)(length * lineThickness)));
        g2d.setColor(bgColor);
        g2d.fillOval(bgStartX, bgStartY, bgSize, bgSize);
        
        g2d.setColor(rateColor.darker().darker().darker());
        g2d.fillOval(outerStartX, outerStartY, outerSize, outerSize);
        g2d.setColor(bgColor);
        g2d.fillOval(innerStartX, innerStartY, innerSize, innerSize);
        g2d.setColor(rateColor);
        g2d.fillArc(outerStartX, outerStartY, outerSize, outerSize, 90, rateAngle);
        g2d.setColor(bgColor);
        g2d.fillOval(innerStartX, innerStartY, innerSize, innerSize);
        
        
        
        
//        setRateColor();
        g2d.setColor(rateColor);
        g2d.fillOval(
                (int)(bgStartX + bgSize / 2f + Math.cos(Math.toRadians(-rateAngle - 90)) * (innerSize + thickness) / 2 - thickness / 2f), 
                (int)(bgStartY + bgSize / 2f + Math.sin(Math.toRadians(-rateAngle - 90)) * (innerSize + thickness) / 2 - thickness / 2f), 
                thickness, 
                thickness
        );
        g2d.fillOval(
                (int)(bgStartX + bgSize / 2f + Math.cos(Math.toRadians(- 90)) * (innerSize + thickness) / 2 - thickness / 2f), 
                (int)(bgStartY + bgSize / 2f + Math.sin(Math.toRadians(- 90)) * (innerSize + thickness) / 2 - thickness / 2f), 
                thickness, 
                thickness
        );
        
        Font fm = new Font("caribri" , Font.PLAIN , fontSize);
        g2d.setFont(fm);
        g2d.setColor(Color.WHITE);
        drawStringCenter(g, (int)rateOutOf10 + "", bgStartX + (int)(bgSize / 80f * 29), bgStartY + bgSize / 2);

        fm = new Font("caribri" , Font.PLAIN , fontSize/ 3);
        g2d.setFont(fm);
        drawStringCenter(g, "/ 10", bgStartX + (int)(bgSize / 20f * 13), bgStartY + (int)(bgSize / 20f * 9));
        
        // Guid Lines/
        // Uncomment it to show lines
//        g2d.setColor(Color.BLACK);
//        g2d.drawLine(
//                bgStartX + bgSize / 2 + (int)(Math.cos(Math.toRadians(-rateAngle - 90)) * (innerSize + thickness) / 2), 
//                0, 
//                bgStartX + bgSize / 2 + (int)(Math.cos(Math.toRadians(-rateAngle - 90)) * (innerSize + thickness) / 2), 
//                height
//        );
//        
//        g2d.drawLine(
//                0, 
//                bgStartY + bgSize / 2 + (int)(Math.sin(Math.toRadians(-rateAngle - 90)) * (innerSize + thickness) / 2), 
//                width, 
//                bgStartY + bgSize / 2 + (int)(Math.sin(Math.toRadians(-rateAngle - 90)) * (innerSize + thickness) / 2)
//        );

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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(51, 51, 51));
        setOpaque(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 166, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 177, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
