/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Icon;

import Output.StackView.MovieStackedPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

/**
 *
 * @author fes77
 */
public class HomeIcon extends TextIcon{

    public HomeIcon() {
        this("Home", Color.WHITE.darker());
    }

    public HomeIcon(String text, Color color) {
        super("Home", color);
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

    public HomeIcon(AnimationType type, String text, Color color) {
        super(type, "Home", color);
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

    @Override
    public void onClickAction(MouseEvent e) {
        if(MovieStackedPanel.STACK_PANEL != null)
        {
                MovieStackedPanel.STACK_PANEL.reset();
            System.out.println("Resetting");
                
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 800));
        HomeIcon movieListPanel = new HomeIcon();

        frame.add(movieListPanel);
        frame.setVisible(true);

    }

    
    
}
