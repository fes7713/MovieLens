/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Icon;

import Output.DetailedView.MovieScrollDetailPanel;
import Output.StackView.MovieStackedPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

/**
 *
 * @author fes77
 */
public class CrossIcon extends ClickableIcon
{
    public static float PADDING_LINE_PERCENTAGE = 0.75f;
    public CrossIcon()
    {
        super();
        color = Color.WHITE;
    }
    
    public CrossIcon(AnimationType type)
    {
        super(type);
        color = Color.WHITE;
    }

    @Override
    protected void drawIcon(Graphics2D g2d, int width, int height, int length, int thickness, float zoomRatio) {
        int lengthLine = (int)(length * (1 - PADDING_LINE_PERCENTAGE));
        float shift = length * MAX_SHIFT_PERCENTAGE * zoomRatio;
        
        BasicStroke wideStroke = new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2d.setStroke(wideStroke);
        
        g2d.drawLine(
                (int)(width / 2f - lengthLine / 2f - shift), 
                (int)(height / 2f - lengthLine / 2f - shift), 
                (int)(width / 2f + lengthLine / 2f - shift), 
                (int)(height / 2f + lengthLine / 2f - shift)
        );
        
        g2d.drawLine(
                (int)(width / 2f + lengthLine / 2f - shift), 
                (int)(height / 2f - lengthLine / 2f - shift), 
                (int)(width / 2f - lengthLine / 2f - shift), 
                (int)(height / 2f + lengthLine / 2f - shift)
        );
    }

    @Override
    public void onClickAction(MouseEvent e) {
//        super.onClickAction(e);
        
        if(MovieStackedPanel.STACK_PANEL != null)
        {
//            Component parent = getParent();
//            System.out.println(parent.getClass().getSimpleName());
//            if(parent instanceof MovieDetailPanel)
//            {
//                Component grandParent = parent.getParent();
//                if(grandParent instanceof MovieScrollDetailPanel)
//                    MovieStackedPanel.STACK_PANEL.remove(grandParent);
//                else
//                    MovieStackedPanel.STACK_PANEL.remove(parent);
//            }
//            if(MovieStackedPanel.STACK_PANEL.peekTop() instanceof MovieScrollDetailPanel)
            
//                MovieStackedPanel.STACK_PANEL.backHome();
                
            Component parent = getParent().getParent().getParent().getParent().getParent();
            System.out.println(parent.getClass().getSimpleName());
            
            if(parent instanceof MovieScrollDetailPanel)
                MovieStackedPanel.STACK_PANEL.backHome();
                
        }
    }
    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 800));
        CrossIcon movieListPanel = new CrossIcon();

        frame.add(movieListPanel);
        frame.setVisible(true);

    }
    
    
}
