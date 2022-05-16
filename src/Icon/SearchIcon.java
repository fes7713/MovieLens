/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Icon;

import Output.ListView.MovieTopicListPanel;
import Output.ListView.ScrollView.Scroll;
import Output.StackView.MovieStackedPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author fes77
 */
public class SearchIcon extends TextIcon{

    JTextField textField;
    
    public SearchIcon() {
        super();
        text = "Search";
        repaint();
    }
    public SearchIcon(JTextField textField) {
        super();
        this.textField = textField;
        text = "Search";
        repaint();
    }

    public SearchIcon(JTextField textField, Color color) {
        super("Search", color);
        this.textField = textField;
        repaint();
    }

    @Override
    public void onClickAction(MouseEvent e) {
        if(MovieStackedPanel.STACK_PANEL != null)
        {   
            MovieStackedPanel.STACK_PANEL.reset(new MovieTopicListPanel(Scroll.GRID, textField.getText(), 22, true));
            textField.setText("");

        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 800));
        SearchIcon movieListPanel = new SearchIcon();

        frame.add(movieListPanel);
        frame.setVisible(true);

    }
    
    
    
}
