/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Icon;

import Resource.Message;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author fes77
 */
public class ClickableIcon extends HoverableIcon implements Clickable{

    
    @Override
    public void onClickPaint(Graphics2D g2d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onClickAction(MouseEvent e) {
        try {
            Browser.BrowserSupport.openWebpage(new URL("https://www.google.co.jp/"));
        } catch (MalformedURLException ex) {
            Message.ERROR.printMessage(ex);
        }
    }
    
    public ClickableIcon()
    {
        super();
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
    
    public ClickableIcon(AnimationType type)
    {
        super(type);
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
    
    
}
