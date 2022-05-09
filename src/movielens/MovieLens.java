/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package movielens;

import Data.Genre;
import Data.Movie;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fes77
 */
public class MovieLens {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        JFrame frame = new JFrame("Test frame");
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setBounds(0, 0, 300, 300);
//        frame.add(new MovieCard());
//        frame.setVisible(true);
        
        
        // TODO code application logic here
        Repository.connect(Repository.Driver.MySQL ,"movie-lens.cpzst9uo9qun.ap-northeast-1.rds.amazonaws.com", 3306, "mydb" , "root", "rsTTMA2sHyUL");
//        try {
//            ResultSet rs = Repository.query("SHOW TABLES");
//            ResultSetMetaData rsmd = rs.getMetaData();
//            int nCols = rsmd.getColumnCount();
//
//            Vector<String> colNames = new Vector<>();
//
//            for (int i = 1; i <= nCols; i++) {
//                colNames.add(rsmd.getColumnName(i));
//            }
//
//            while (rs.next()) {
//                Object[] rowData = new Object[colNames.size()];
//                for (int i = 0; i < rowData.length; i++) {
//                    rowData[i] = rs.getObject(colNames.get(i));
////                    System.out.println(rs.getObject(colNames.get(i).toString());
//                    System.out.println(rowData[i]);
//                }
////                tableModel.addRow(rowData);
//            }
//            for(String table: Repository.findAllTables())
//            {   
//                System.out.println("Table : " + table);
//                for(String attr: Repository.findAllAttrInTable(table))
//                {
//                    System.out.println(attr);
//                }
//            }
            
            Repository.insertMovie(new Movie(1, "New Movie"));
            Repository.insertTag(1, 1, "fun");
            Movie movie = Repository.findMovieById(1);
            System.out.println(movie);
            Repository.insertRating(1, 1, 10);
            System.out.println((Genre.Sci_Fi + "").getClass().getSimpleName());
            Repository.insertGenres(
                    1, 
                    new ArrayList<Genre>(){
                        {
                            add(Genre.Sci_Fi);
                            add(Genre.Action);
                            add(Genre.Romance);
                            add(Genre.Film_Noir);
                            add(Genre.Drama);
                            
                            
                        }
                    }
            );
            
            List<Genre> genres = Repository.findGenresById(1);
            for(int i = 0; i < genres.size(); i++)
            {
                System.out.println(genres.get(i));
            }
            
            Repository.insertRating(1, 1, 0);
            Repository.insertRating(1, 2, 1);
            Repository.insertRating(1, 3, 2);
            Repository.insertRating(1, 4, 3);
            Repository.insertRating(1, 5, 4);
            Repository.insertRating(1, 6, 5);
            Repository.insertRating(1, 7, 6);
            
            
            System.out.println(Repository.findAverageRatingById(1));
            
            Repository.insertTags(1, 2, new ArrayList<String>(){
                {
                    add("action");
                    add("funny");
                    add("comedy");
                    add("war");
                }
            });
            
            Repository.insertTags(1, 1, new ArrayList<String>(){
                {
                    add("action");
                    add("serious");
                    add("comedy");
                    add("computer");
                    add("romance");
                }
            });
            
            Repository.insertTags(1, 3, new ArrayList<String>(){
                {
                    add("action");
                    add("serious");
                    add("cute");
                    add("tech");
                    add("romance");
                }
            });
            
            Map<String, Integer> tagMap = Repository.findTagsById(1);
            
            tagMap.keySet().forEach(key -> {
                System.out.println(key);
                System.out.println(tagMap.get(key));
            });
            
//            List<String> tags = Repository.findTagsById(1);
//            for(String tag: tags)
//            {
//                System.out.println(tag);
//            }
//        } 
//        catch (SQLException e) {
//            int errorCode = e.getErrorCode();
//            String errorMessage = e.getMessage();
//            JOptionPane.showMessageDialog(null, "Error Code + " + errorCode + " : " + errorMessage);
        }




    
    
}
