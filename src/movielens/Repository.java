/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movielens;
//movie-lens.cpzst9uo9qun.ap-northeast-1.rds.amazonaws.com

import Data.Genre;
import Data.KeyException;
import Data.Movie;
import Repository.Keys;
import Repository.Tables;
import Resource.Message;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;


/**
 *
 * @author fes77
 */
public class Repository {

    static String MYSQLURL; //change project name to w/e is on your device
    static String USER;
    static String PASS; // change password if needed.
    static Connection con;
    static enum Driver {
        MySQL("mysql");
        private final String drivername;
        Driver(String drivername) {
            this.drivername = drivername;
        }
    };

    interface QueryCallback{
        public Object Callback(ResultSet rs, ResultSetMetaData rsmd, int nRow, int nCol);
    }
    
    public static boolean connect(Driver driver, String hostname, int port, String dbName, String User, String Pass) {
        MYSQLURL = "jdbc:" + driver.drivername + "://" + hostname + ":" + port + "/" + dbName;
        USER = User;
        PASS = Pass;
        con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No mysql driver");
        }

        try {
            con = DriverManager.getConnection(MYSQLURL, USER, PASS);
            if (con != null) {
                System.out.println("Database connection is successful!");
                return true;
            } else {
                System.out.println("Connection not successful. You messed up!");
                return false;
            }
        } catch (SQLException e) {
            int errorCode = e.getErrorCode();
            String errorMessage = e.getMessage();
            JOptionPane.showMessageDialog(null, "Error Code + " + errorCode + " : " + errorMessage);
            return false;
        }
    }

    public static boolean isConnected() {
        if (con != null) {
            return true;
        }
        return false;
    }

    public static String getDatabaseName() {
        String[] arr = MYSQLURL.split("/");
        return arr[arr.length - 1];
    }

    public static ResultSet query(String query) throws SQLException {
        if (con == null) {
            System.out.println("No connection");
            return null;
        }

        String[] querySeparated = query.split(";");
        Statement st = con.createStatement();
        return st.executeQuery(querySeparated[querySeparated.length - 1]);
    }
    
//    public static Object[][] ResultProcessor(ResultSet rs) throws SQLException {
//        ResultSetMetaData rsmd = rs.getMetaData();
//        
//        int nCols = rsmd.getColumnCount();
//        int nRows = getRowCount(rs);
//        Object[][] array2D = new Object[nRows][nCols];
//                
//        int counter = 0;
//        while (rs.next()) {
//            for (int i = 1; i <= nCols; i++) {
//               array2D[counter][i] = rs.getObject(rsmd.getColumnName(i));
//            }
//        }
//        return array2D;
//    }
    
    private static Object processQuery(String query, Object returnObj, QueryCallback rq){
        try{
            Message.EXECUTING.printMessage(query);
            ResultSet rs = query(query);
            Message.EXECUTED.printMessage(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            int nCols = rsmd.getColumnCount();
            int nRows = getRowCount(rs);
            Object[][] array2D = new Object[nRows][nCols];

            int counter = 0;
            while (rs.next()) {
                for (int i = 1; i <= nCols; i++) {
    //               array2D[counter][i] = rs.getObject(rsmd.getColumnName(i));
                    Object obj = rq.Callback(rs, rsmd, counter, i);
                    if(obj != null)
                        return obj;
                }
            }
            return array2D;
        }
        catch(SQLException e){
            int errorCode = e.getErrorCode();
            String errorMessage = e.getMessage();
            JOptionPane.showMessageDialog(null, "Error Code + " + errorCode + " : " + errorMessage);
            return null;
        }
        
    }

    public static void SQLDDL(String sql) throws SQLException {
        if (con == null) {
            System.out.println("No connection");
            return;
        }
        String[] sqlSeparated = sql.split(";");
        Statement st = con.createStatement();
        for (int i = 0; i < sqlSeparated.length; i++) {
            st.executeUpdate(sqlSeparated[i]);
        }
    }

    public static List<String> findAllTables() {
        List<String> tables = new ArrayList();

        try {
            DatabaseMetaData md = con.getMetaData();
//            Statement st = con.createStatement();
            String[] types = {"TABLE"};
            ResultSet rs = md.getTables(null, null, "%", types);
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                try {
//                    st.executeQuery("SELECT * FROM " + tableName + ";");
                    tables.add(rs.getString("TABLE_NAME"));
                } catch (SQLException e) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tables;
    }
    
    public static List<String> findAllAttrInTable(String tableName)
    {
        List<String> attr = new ArrayList();
        
        try{
            ResultSet rs = query("SELECT * FROM " + tableName);
            ResultSetMetaData rsmd = rs.getMetaData();
            int nCols = rsmd.getColumnCount();

            for (int i = 1; i <= nCols; i++) {
                attr.add(rsmd.getColumnName(i));
            }
        }
        catch(SQLException e){
            int errorCode = e.getErrorCode();
            String errorMessage = e.getMessage();
            JOptionPane.showMessageDialog(null, "Error Code + " + errorCode + " : " + errorMessage);
        }
        
        return attr;
                
    }
    
    private static int getRowCount(ResultSet rs) throws SQLException
    {
        rs.last();
        int number_of_row = rs.getRow();
        rs.beforeFirst();   //最初に戻る
        return number_of_row;
    }
    
//    // Nullable
//    public static Movie findMovieById(int id) {
//        Movie movie = new Movie();
//        
//        try {
//            ResultSet rs = query(
//                    "SELECT " 
//                        + Keys.MOVIEID + ", "
//                        + Keys.TITLE
//                    + " FROM " 
//                        + Tables.MOVIE
//                    + " WHERE " + Keys.MOVIEID + " = " + id);
//            
//            ResultSetMetaData rsmd = rs.getMetaData();
//            int nCols = rsmd.getColumnCount();
//            
//            if(getRowCount(rs) != 1)
//            {
//                Message.NO_MATCH_ERROR.showMessage("findMovieById(" + id + ")");
//                return null;
//            }
//                
//            System.out.println(nCols);
//            while (rs.next()) {
//                for (int i = 1; i <= nCols; i++) {
//                    movie.set(rsmd.getColumnName(i), rs.getObject(rsmd.getColumnName(i)));
//                }
//            }
//        }
//        catch(SQLException e){
//            int errorCode = e.getErrorCode();
//            String errorMessage = e.getMessage();
//            JOptionPane.showMessageDialog(null, "Error Code + " + errorCode + " : " + errorMessage);
//        }
//        catch(KeyException ke)
//        {
//            JOptionPane.showMessageDialog(null, ke.getMessage());
//        }
//        return movie;
//    }
    
    public static Movie findMovieById(int id) {
        Movie movie = new Movie();
        String query = 
                "SELECT " 
                    + Keys.MOVIEID + ", "
                    + Keys.TITLE
                + " FROM " 
                    + Tables.MOVIE
                + " WHERE " + Keys.MOVIEID + " = " + id;
        
        processQuery(query, null, 
                (ResultSet rs, ResultSetMetaData rsmd, int nRow, int nCol) -> 
                {
                    try{
                        movie.set(rsmd.getColumnName(nCol), rs.getObject(rsmd.getColumnName(nCol)));
                    }
                    catch(SQLException e){
                        int errorCode = e.getErrorCode();
                        String errorMessage = e.getMessage();
                        JOptionPane.showMessageDialog(null, "Error Code + " + errorCode + " : " + errorMessage);
                    }
                    catch(KeyException ke)
                    {
                        JOptionPane.showMessageDialog(null, ke.getMessage());
                    }
                    return null;
                }
        );  
        return movie;
    }
    
    public static Set<Genre> findGenresById(int movieId)
    {
        Set<Genre> genres = new HashSet();
        String query = 
                "SELECT " + Keys.GENRE 
                + " FROM " + Tables.GENRE 
                + " WHERE " + Keys.MOVIEID + " = " + movieId;
        
            processQuery(query, genres, (ResultSet rs, ResultSetMetaData rsmd, int nRow, int nCol) -> {
                try {
                    Genre g = Genre.valueOf(((String)rs.getObject(Keys.GENRE)).replace("-", "_"));
                    genres.add(g);
                }
                catch(SQLException e){
                    int errorCode = e.getErrorCode();
                    String errorMessage = e.getMessage();
                    JOptionPane.showMessageDialog(null, "Error Code + " + errorCode + " : " + errorMessage);
                }
                return null;
        });
        
        return genres;
    }
    
    public static float findAverageRatingById(int movieId)
    {
        float rating = 0f;
        String query = "SELECT " + "AVG(" + Keys.RATING + ")"
                    + " FROM " + Tables.RATING 
                    + " WHERE " + Keys.MOVIEID + " = " + movieId;
        
        rating = ((Double)processQuery(
                    query, 
                    rating, 
                    (ResultSet rs, ResultSetMetaData rsmd, int nRow, int nCol) 
                        -> {
                            try {
                                return rs.getObject(rsmd.getColumnName(1));
                            }
                            catch(SQLException e){
                                int errorCode = e.getErrorCode();
                                String errorMessage = e.getMessage();
                                JOptionPane.showMessageDialog(null, "Error Code + " + errorCode + " : " + errorMessage);
                            }
                            return null;
                        }
                    )
                ).floatValue();
        
        return rating;
    }
    
    public static Map<String, Integer> findTagsById(int movieId)
    {
        Map<String, Integer> tagMap = new HashMap<>();
//        select Tag, count(*) from Tag Group By Tag
        String query = 
            "SELECT " 
                + Keys.TAG + ", "
                + "COUNT(*)"
            + " FROM " 
                + Tables.TAG 
            + " WHERE " 
                + Keys.MOVIEID + " = " + movieId
            + " GROUP BY "
                + Keys.TAG
            + " ORDER BY COUNT(*) DESC";
            
        processQuery(query, tagMap, (ResultSet rs, ResultSetMetaData rsmd, int nRow, int nCol) -> {
                try {
                    tagMap.put((String)rs.getObject(Keys.TAG), ((java.lang.Long)rs.getObject("COUNT(*)")).intValue());
                }
                catch(SQLException e){
                    int errorCode = e.getErrorCode();
                    String errorMessage = e.getMessage();
                    JOptionPane.showMessageDialog(null, "Error Code + " + errorCode + " : " + errorMessage);
                }
                return null;
        });
        return tagMap;
    }
    
    
    
    public static boolean insertMovie(Movie movie)
    {
        try {
            String query = 
                    "INSERT IGNORE INTO " + Tables.MOVIE 
                        + "(" 
                            + Keys.MOVIEID + ", " 
                            + Keys.TITLE + ")"
                        +  "Values(" 
                            + movie.getId() + ", "
                            + "\"" + movie.getTitle() + "\")";
             
            Statement st = con.createStatement();
            st.executeUpdate(query);
            Message.EXECUTED.printMessage(query);
        }
        catch(SQLException e){
            int errorCode = e.getErrorCode();
            String errorMessage = e.getMessage();
            JOptionPane.showMessageDialog(null, "Error Code + " + errorCode + " : " + errorMessage);
            return false;
        }
        return true;
    }
    
    public static void insertMovies(List<Movie> movies)
    {
        for(int i = 0; i < movies.size(); i++)
            insertMovie(movies.get(i));
    }
        
    public static boolean insertGenre(int movieId, Genre genre)
    {
        try {
            String query = 
                    "INSERT IGNORE INTO " + Tables.GENRE 
                        + "(" 
                            + Keys.MOVIEID + ", " 
                            + Keys.GENRE + ")"
                        +  "Values(" 
                            + movieId + ", "
                            + "\"" + genre + "\")";
                    
            Statement st = con.createStatement();
            st.executeUpdate(query);
            Message.EXECUTED.printMessage(query);
        }
        catch(SQLException e){
            int errorCode = e.getErrorCode();
            String errorMessage = e.getMessage();
            JOptionPane.showMessageDialog(null, "Error Code + " + errorCode + " : " + errorMessage);
            return false;
        }
        return true;
    }
    
    public static boolean insertGenres(int movieId, Set<Genre> genres)
    {
        for(Genre genre: genres)
        {
            if(insertGenre(movieId, genre) != true)
                return false;
        }
        return true;
    }
    
    private static String currentTime()
    {
        return "UNIX_TIMESTAMP(current_timestamp())";
    }
    
    public static boolean insertRating(int movieId, int userId, float rating)
    {
        return insertRating(movieId, userId, rating, currentTime()); 
    }
    
    public static boolean insertRating(int movieId, int userId, float rating, int timestamp)
    {
        return insertRating(movieId, userId, rating, timestamp + "");
    }
    
    public static boolean insertRating(int movieId, int userId, float rating, String timestamp)
    {
        try {
            String query = 
                    "INSERT IGNORE INTO " + Tables.RATING 
                        + "(" 
                            + Keys.USERID + ", " 
                            + Keys.MOVIEID + ", "
                            + Keys.RATING +", "
                            + Keys.TIMESTAMP + ")"
                        +  "Values(" 
                            + userId + ", "
                            + movieId + ", "
                            + rating + ", " 
                            + timestamp + ")";
                    
            Statement st = con.createStatement();
            st.executeUpdate(query);
            Message.EXECUTED.printMessage(query);
        }
        catch(SQLException e){
            int errorCode = e.getErrorCode();
            String errorMessage = e.getMessage();
            JOptionPane.showMessageDialog(null, "Error Code + " + errorCode + " : " + errorMessage);
            return false;
        }
        return true;
    }

    
    public static boolean insertTag(int movieId, int userId, String tag)
    {
        return insertTag(movieId, userId, tag, currentTime());
    }
    
    public static boolean insertTag(int movieId, int userId, String tag, int timestamp)
    {
        return insertTag(movieId, userId, tag, timestamp + "");
    }
    
    public static boolean insertTag(int movieId, int userId, String tag, String timestamp)
    {
        try {
            String query = 
                    "INSERT IGNORE INTO " + Tables.TAG
                        + "(" 
                            + Keys.MOVIEID + ", " 
                            + Keys.USERID + ", " 
                            + Keys.TAG + ", "
                            + Keys.TIMESTAMP + ")"
                        +  "Values(" 
                            + movieId + ", "
                            + userId + ", "
                            + "\"" + tag + "\", "
                            + timestamp + ")";
            
                    
            Statement st = con.createStatement();
            st.executeUpdate(query);
            Message.EXECUTED.printMessage(query);
        }
        catch(SQLException e){
            int errorCode = e.getErrorCode();
            String errorMessage = e.getMessage();
            JOptionPane.showMessageDialog(null, "Error Code + " + errorCode + " : " + errorMessage);
            return false;
        }
        return true;
    }
    
    public static boolean insertTags(int movieId, int userId, List<String> tags)
    {
        return insertTags(movieId, userId, tags, currentTime());
    }
    
    public static boolean insertTags(int movieId, int userId, List<String> tags, int timestamp)
    {
        return insertTags(movieId, userId, tags, timestamp + "");
    }
    
    public static boolean insertTags(int movieId, int userId, List<String> tags, String timestamp)
    {
        for(int i = 0; i < tags.size(); i++)
        {
            if(insertTag(movieId, userId, tags.get(i)) != true)
                return false;
        }
        return true;
    }
    
    // Load movies from files. Connect multiple files according to the arguments given to this function.
    public static List<Movie> loadMovies(boolean rating, boolean tags)
    {
        List<Movie> movies = new ArrayList();
        Path file = Paths.get("data/movies.csv");
        String[] separated;
        try{
            List<String> text = Files.readAllLines(file); // UTF-8
            text.remove(0);
            
            for(String t: text)
            {
                separated = t.split(",", 2);
                movies.add(new Movie(Integer.parseInt(separated[0]), separated[1]));
            }
        } catch (IOException e) {
            String errorMessage = e.getMessage();
            JOptionPane.showMessageDialog(null, "IO Exception" + errorMessage);
        }
        
        return movies;
    }
    
    
}