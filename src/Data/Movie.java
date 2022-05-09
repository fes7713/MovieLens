/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Repository.Keys;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import javax.swing.JOptionPane;
import movielens.Repository;

/**
 *
 * @author fes77
 */
public class Movie implements SQLData{
    private int id;
    private String title;
    private Set<Genre> genres;
    
    public Movie(int id, String title)
    {
        this.id = id;
        this.title = title;
        genres = new HashSet<>();
    }
    
    public Movie(int id, String title, Set<Genre> genres)
    {
        this.id = id;
        this.title = title;
        this.genres = genres;
    }

    public Movie() {
        id = -1;
        title = null;
        genres = new HashSet<>();
    }
    
    public Movie findMovieById(int id)
    {
        try {
            ResultSet rs = Repository.query("Select * from Movie");
            ResultSetMetaData rsmd = rs.getMetaData();
            int nCols = rsmd.getColumnCount();

            Vector<String> colNames = new Vector<>();

            for (int i = 1; i <= nCols; i++) {
                colNames.add(rsmd.getColumnName(i));
            }

            while (rs.next()) {
                Object[] rowData = new Object[colNames.size()];
                for (int i = 0; i < rowData.length; i++) {
                    rowData[i] = rs.getObject(colNames.get(i));
//                    System.out.println(rs.getObject(colNames.get(i).toString());
                    System.out.println(rowData[i]);
                }
//                tableModel.addRow(rowData);
            }

        } catch (SQLException e) {
            int errorCode = e.getErrorCode();
            String errorMessage = e.getMessage();
            JOptionPane.showMessageDialog(null, "Error Code + " + errorCode + " : " + errorMessage);
        }
        
        return null;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    @Override
    public void set(String key, Object value) throws KeyException {
        switch(key)
        {
            case Keys.MOVIEID-> id = (int)value;
            case Keys.TITLE-> title = (String )value;
            case Keys.GENRE-> genres.add((Genre)value);
            default -> throw new KeyException(key);
        }
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title=" + title + ", genres=" + genres + '}';
    }
}
