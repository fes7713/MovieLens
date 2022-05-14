/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Icon;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 *
 * @author fes77
 */
public interface Clickable {
    public void onHoverPaint(Graphics2D g2d);
    public void onClickPaint(Graphics2D g2d);
    public void onHoverAction(MouseEvent e);
    public void onClickAction(MouseEvent e);
}
