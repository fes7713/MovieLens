/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Icon;

import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.swing.JFrame;

/**
 *
 * @author fes77
 */
public class RectBaseIcon extends HoverableIcon{

    public static final float ROUND_ARC_RATIO = 0.5f;
    public static final float EXTENDED = 0.16f;
    
    public RectBaseIcon() {
    }

    public RectBaseIcon(AnimationType type) {
        super(type);
    }

    

    @Override
    public void onHoverPaint(Graphics2D g2d) {
        int height = getHeight();
        int width = getWidth();
        int length = height < width ? height : width;
        int margin = (int)(length * marginPercentage);
        int thickness = (int)(length * lineThicknessPercentage);
        int extension = (int)(length * EXTENDED);
        
        int bgSize = length - 2 *  margin;
        int bgStartX = width / 2 - length / 2 + margin;
        int bgStartY = height / 2 - length / 2 + margin;
        int arcLength = (int)(length * ROUND_ARC_RATIO);

        g2d.drawRoundRect(
                    (int)(bgStartX + thickness * 4 / 2 - thickness * getZoomRatio() - extension), 
                    (int)(bgStartY + thickness * 4 / 2 - thickness * getZoomRatio() + extension), 
                    (int)(bgSize - thickness * 4 + thickness * 2 * getZoomRatio() + extension * 2), 
                    (int)(bgSize - thickness * 4 + thickness * 2 * getZoomRatio() - extension * 2),
                    (int)(arcLength - thickness * 4 + thickness * 2 * getZoomRatio()),
                    (int)(arcLength - thickness * 4 + thickness * 2 * getZoomRatio()));
    }

    @Override
    protected void drawBackground(Graphics2D g2d, int width, int height, int length, int margin) {
        int bgSize = length - 2 *  margin;
        
        int bgStartX = (int)(width / 2f - length / 2f + margin);
        int bgStartY = (int)(height / 2f - length / 2f + margin);
        int arcLength = (int)(length * ROUND_ARC_RATIO);
        int extension = (int)(length * EXTENDED);
        
        g2d.setColor(bgColor);
        g2d.fillRoundRect(
                bgStartX - extension, 
                bgStartY + extension, 
                bgSize + extension * 2, 
                bgSize - extension * 2,
                arcLength,
                arcLength);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 800));
        RectBaseIcon movieListPanel = new RectBaseIcon();

        frame.add(movieListPanel);
        frame.setVisible(true);

    }
    
}
