/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Icon;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 *
 * @author fes77
 */
public interface Hoverable {
    public void onHoverPaint(Graphics2D g2d);
    public void onHoverEnterAction(MouseEvent e);
    public void onHoverExitAction(MouseEvent e);
}
