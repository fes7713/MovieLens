/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package movielens;

import Output.ListView.ScrollView.Scroll;
import Output.StackView.MovieStackedPanel;
import Repository.Repository;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author fes77
 */
public class MovieLens {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Repository.connect(Repository.Driver.MySQL ,"127.0.0.1", 3306, "mydb" , "root", "rsTTMA2sHyUL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1500, 800));
        MovieStackedPanel stackPanel = new MovieStackedPanel(Scroll.GRID);
        frame.add(stackPanel);
        frame.setVisible(true);
    }




    
    
}
