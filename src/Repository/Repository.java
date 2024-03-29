/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
//movie-lens.cpzst9uo9qun.ap-northeast-1.rds.amazonaws.com

import Data.Genre;
import Data.Movie;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
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

    
    public static enum Driver {
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
            Message.ERROR.showMessage("No connection");
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
    
    private static Object processQuery(String query, QueryCallback rq){
        try{
            Message.EXECUTING.printMessage(query);
            ResultSet rs = query(query);
            Message.EXECUTED.printMessage(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            int nCols = rsmd.getColumnCount();
            int nRows = getRowCount(rs);
            
            if(nRows == 0)
            {
                Message.NO_MATCH_ERROR.printMessage();
                return null;
            }
//            Object[][] array2D = new Object[nRows][nCols];

            int counter = 0;
            while (rs.next()) {
                for (int i = 1; i <= nCols; i++) {
    //               array2D[counter][i] = rs.getObject(rsmd.getColumnName(i));
                    Object obj = rq.Callback(rs, rsmd, counter, i);
                    if(obj != null)
                        return obj;
                }
            }
            return null;
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
        
        processQuery(query, 
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
    
    public static List<Integer> findMovieByQuery(String query, int start, int size) {
        if(size <= 0)
            return new ArrayList();
        List<Integer> ids = new ArrayList<>();
        
        if(!query.contains("LIMIT"))
                query += " LIMIT "
                    + size;
        if(!query.contains("OFFSET"))
                query += " OFFSET "
                    + start;
        
        processQuery(query, 
                (ResultSet rs, ResultSetMetaData rsmd, int nRow, int nCol) -> 
                {
                    try{
                        int id = rs.getObject(Keys.MOVIEID, Integer.TYPE);
                        ids.add(id);
                    }
                    catch(SQLException e){
                        int errorCode = e.getErrorCode();
                        String errorMessage = e.getMessage();
                        JOptionPane.showMessageDialog(null, "Error Code + " + errorCode + " : " + errorMessage);
                    }
                    return null;
                }
        );  
        return ids;
    }
    
    public static Set<Genre> findGenresById(int movieId)
    {
        Set<Genre> genres = new HashSet();
        String query = 
                "SELECT " + Keys.GENRE 
                + " FROM " + Tables.GENRE 
                + " WHERE " + Keys.MOVIEID + " = " + movieId;
        
            processQuery(query, (ResultSet rs, ResultSetMetaData rsmd, int nRow, int nCol) -> {
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
        String query = "SELECT " + "AVG(" + Keys.RATING + ")"
                    + " FROM " + Tables.RATING 
                    + " WHERE " + Keys.MOVIEID + " = " + movieId;
        
        Object rtnObj = processQuery(
                    query,
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
                            return 0;
                        }
                    );
        if(rtnObj == null)
            return 0;
        return ((Double)rtnObj).floatValue();
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
            
        processQuery(query, (ResultSet rs, ResultSetMetaData rsmd, int nRow, int nCol) -> {
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
    
    public static List<String>  findTagsByIdOrdered(int movieId)
    {
        List<String> tagList = new ArrayList<>();
//        select Tag, count(*) from Tag Group By Tag
        String query = 
            "SELECT " 
                + " DISTINCT " + Keys.TAG
            + " FROM " 
                + Tables.TAG 
            + " WHERE " 
                + Keys.MOVIEID + " = " + movieId
            + " GROUP BY "
                + Keys.TAG
            + " ORDER BY COUNT(*) DESC";
            
        processQuery(query, (ResultSet rs, ResultSetMetaData rsmd, int nRow, int nCol) -> {
                try {
                    tagList.add((String)rs.getObject(Keys.TAG));
                }
                catch(SQLException e){
                    int errorCode = e.getErrorCode();
                    String errorMessage = e.getMessage();
                    JOptionPane.showMessageDialog(null, "Error Code + " + errorCode + " : " + errorMessage);
                }
                return null;
        });
        return tagList;
    }
    
    public static int findImdbIdById(int movieId)
    {
        String query = 
            "SELECT " 
                + Keys.IMDBID
            + " FROM " 
                + Tables.LINK 
            + " WHERE " 
                + Keys.MOVIEID + " = " + movieId;
            
        Object rtnObj = processQuery(query, (ResultSet rs, ResultSetMetaData rsmd, int nRow, int nCol) -> {
                try {
                    return rs.getObject(Keys.IMDBID);
                }
                catch(SQLException e){
                    int errorCode = e.getErrorCode();
                    String errorMessage = e.getMessage();
                    JOptionPane.showMessageDialog(null, "Error Code + " + errorCode + " : " + errorMessage);
                }
                return 0;
        });
        if(rtnObj == null)
            return 0;
        return (Integer)rtnObj;
    }
    
    public static int findTmdbIdById(int movieId)
    {
        String query = 
            "SELECT " 
                + Keys.TMDBID
            + " FROM " 
                + Tables.LINK 
            + " WHERE " 
                + Keys.MOVIEID + " = " + movieId;
            
        Object rtnObj = processQuery(query, (ResultSet rs, ResultSetMetaData rsmd, int nRow, int nCol) -> {
                try {
                    return rs.getObject(Keys.TMDBID, Integer.TYPE);
                }
                catch(SQLException e){
                    int errorCode = e.getErrorCode();
                    String errorMessage = e.getMessage();
                    JOptionPane.showMessageDialog(null, "Error Code + " + errorCode + " : " + errorMessage);
                }
                return 0;
        });
        if(rtnObj == null)
            return 0;
        return (Integer)rtnObj;
    }

    public static List<Movie> findTopRatedMovies(int start, int size)
    {
        if(size <= 0)
            return new ArrayList();
        List<Movie> movies = new ArrayList();
        List<Integer> ids = new ArrayList<>();
        
        Movie movie = new Movie();
        String query = 
                "SELECT " 
                    + Keys.MOVIEID
                + " FROM " 
                    + Tables.RATING
                + " GROUP BY " 
                    + Keys.MOVIEID
                + " ORDER BY "
                    + "AVG(" + Keys.RATING + ") DESC, "
                    + Keys.TIMESTAMP + " DESC"
                + " LIMIT "
                    + size
                + " OFFSET "
                    + start
                ;

        processQuery(query, 
                (ResultSet rs, ResultSetMetaData rsmd, int nRow, int nCol) -> 
                {
                    try{
                        int id = rs.getObject(Keys.MOVIEID, Integer.TYPE);
                        movies.add(Repository.findMovieById(id));
                    }
                    catch(SQLException e){
                        int errorCode = e.getErrorCode();
                        String errorMessage = e.getMessage();
                        JOptionPane.showMessageDialog(null, "Error Code + " + errorCode + " : " + errorMessage);
                    }
                    return null;
                }
        );  
        return movies;
    }
    
    public static List<Integer> findTopRatedMovieIds(int start, int size)
    {
        if(size <= 0)
            return new ArrayList();
        List<Integer> ids = new ArrayList<>();
        
        Movie movie = new Movie();
        String query = 
                "SELECT " 
                    + Keys.MOVIEID
                + " FROM " 
                    + Tables.RATING
                + " GROUP BY " 
                    + Keys.MOVIEID
                + " ORDER BY "
                    + "AVG(" + Keys.RATING + ") DESC, "
                    + Keys.TIMESTAMP + " DESC"
                + " LIMIT "
                    + size
                + " OFFSET "
                    + start
                ;

        processQuery(query, 
                (ResultSet rs, ResultSetMetaData rsmd, int nRow, int nCol) -> 
                {
                    try{
                        int id = rs.getObject(Keys.MOVIEID, Integer.TYPE);
                        ids.add(id);
                    }
                    catch(SQLException e){
                        int errorCode = e.getErrorCode();
                        String errorMessage = e.getMessage();
                        JOptionPane.showMessageDialog(null, "Error Code + " + errorCode + " : " + errorMessage);
                    }
                    return null;
                }
        );  
        return ids;
    }
    
    public static int findRateCountById(int movieId)
    {
        String query = 
                "SELECT " 
                    + "COUNT(*)"
                + " FROM " 
                    + Tables.RATING
                + " WHERE " 
                    + Keys.MOVIEID + " = " + movieId;
                

        Object count  = processQuery(query, 
                (ResultSet rs, ResultSetMetaData rsmd, int nRow, int nCol) -> 
                {
                    try{
                        int id = rs.getObject("COUNT(*)", Integer.TYPE);
                        return id;
                    }
                    catch(SQLException e){
                        int errorCode = e.getErrorCode();
                        String errorMessage = e.getMessage();
                        JOptionPane.showMessageDialog(null, "Error Code + " + errorCode + " : " + errorMessage);
                    }
                    return null;
                }
        );  
        if(count == null)
            return 0;
        return (Integer)count;
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
                            + "\"" + movie.getTitle() + "\");";
             
            Statement st = con.createStatement();
            Message.EXECUTING.printMessage(query);
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
    
    public static boolean insertRating(int userId, int movieId, float rating)
    {
        return insertRating(userId, movieId, rating, currentTime()); 
    }
    
    public static boolean insertRating(int userId, int movieId, float rating, int timestamp)
    {
        return insertRating(userId, movieId, rating, timestamp + "");
    }
    
    public static boolean insertRating(int userId, int movieId, float rating, String timestamp)
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

    public static boolean insertTag(int userId, int movieId, String tag, String timestamp)
    {
        try {
            String query = 
                    "INSERT IGNORE INTO " + Tables.TAG
                        + "(" 
                            + Keys.USERID + ", " 
                            + Keys.MOVIEID + ", " 
                            + Keys.TAG + ", "
                            + Keys.TIMESTAMP + ")"
                        +  "Values(" 
                            + userId + ", "
                            + movieId + ", "
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
    
    public static boolean insertTag(int userId, int movieId, String tag)
    {
        return insertTag(userId, movieId, tag, currentTime());
    }
    
    public static boolean insertTag(int userId, int movieId, String tag, int timestamp)
    {
        return insertTag(userId, movieId, tag, timestamp + "");
    }
    
    public static boolean insertTags(int userId, int movieId, List<String> tags)
    {
        return insertTags(userId, movieId, tags, currentTime());
    }
    
    public static boolean insertTags(int userId, int movieId, List<String> tags, int timestamp)
    {
        return insertTags(userId, movieId, tags, timestamp + "");
    }
    
    public static boolean insertTags(int userId, int movieId, List<String> tags, String timestamp)
    {
        for(int i = 0; i < tags.size(); i++)
        {
            if(insertTag(userId, movieId, tags.get(i)) != true)
                return false;
        }
        return true;
    }
    
    public static boolean insertLinks(int movieId, int imdbId, int tmdbId)
    {
        try {
            String query = 
                    "INSERT IGNORE INTO " + Tables.LINK
                        + "(" 
                            + Keys.MOVIEID + ", " 
                            + Keys.IMDBID + ", " 
                            + Keys.TMDBID + ")"
                        +  "Values(" 
                            + movieId + ", "
                            + imdbId + ", "
                            + tmdbId + ")";
            
                    
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
    
    public static boolean insertLinks(int movieId, int imdbId)
    {
        try {
            String query = 
                    "INSERT IGNORE INTO " + Tables.LINK
                        + "(" 
                            + Keys.MOVIEID + ", " 
                            + Keys.IMDBID + ")"
                        +  "Values(" 
                            + movieId + ", "
                            + imdbId + ")";
            
                    
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
    
    // If separated string array has length more than limit length, 
    // concatenate strings more than limit to one string
    private static String[] commaSeparator(String csv, int limit, int concatenatePos)
    {
        String[] separated = csv.split(",");
        if(separated.length == limit)
        {
            if(separated[concatenatePos].contains("\""))
                separated[concatenatePos] = separated[concatenatePos].replace("\"", "");
            return separated;
        }
        else if(limit > separated.length)
            throw new IllegalArgumentException("Limit cannot be bigger than the size of separated csv line lenth");
        else
        {
            if(limit < concatenatePos)
                throw new IllegalArgumentException("Concatenate position has to be bigger than limit");
//            separated = csv.replace("\"", "").split(",");
            List<String> separatedList = new ArrayList(Arrays.asList(separated));
            while(separatedList.size() > limit)
                separatedList.add(concatenatePos, separatedList.remove(concatenatePos) + "," + separatedList.remove(concatenatePos + 1));
            separatedList.add(concatenatePos, separatedList.remove(concatenatePos).replace("\"", ""));
            return separatedList.toArray(separated);
            
        }
    }
    // Load movies from files. Connect multiple files according to the arguments given to this function.
    private static List<Movie> loadMovies(boolean rating, boolean tags)
    {
        List<Movie> movies = new ArrayList();
        Path file = Paths.get("data/movies.csv");
        String[] separated;
        try{
            List<String> text = Files.readAllLines(file); // UTF-8
            text.remove(0);
            
            for(String t: text)
            {
                separated = commaSeparator(t, 3, 1);
//                String[] arr = separated[2].split("\\|");
//                for(int i = 0; i < arr.length; i++)
//                    System.out.println(arr[i]);
                movies.add(new Movie(Integer.parseInt(separated[0]), separated[1], separated[2].split("\\|")));
            }
        } catch (IOException e) {
            String errorMessage = e.getMessage();
            JOptionPane.showMessageDialog(null, "IO Exception" + errorMessage);
        }
        
        return movies;
    }
    
    public static void insertMoviesFromFile()
    {
        List<Movie> movies = loadMovies(false, false);
        
        insertMovies(movies);
//        for(Movie movie: movies)
//        {
//            insertGenres(movie.getId(), movie.getGenres());
//        }
    }
    
    public static void insertGenresFromFile()
    {
        List<Movie> movies = loadMovies(false, false);
        
//        insertMovies(movies);
        for(Movie movie: movies)
        {
            insertGenres(movie.getId(), movie.getGenres());
        }
    }
    
    public static void insertRatingFromFile()
    {
        Path file = Paths.get("data/ratings.csv");
        String[] separated;
        try{
            List<String> text = Files.readAllLines(file); // UTF-8
            text.remove(0);
            
            for(String t: text)
            {
                separated = t.split(",");
                insertRating(
                        Integer.parseInt(separated[0]), 
                        Integer.parseInt(separated[1]), 
                        Float.parseFloat(separated[2]), 
                        Integer.parseInt(separated[3])
                );
            }
        } catch (IOException e) {
            String errorMessage = e.getMessage();
            JOptionPane.showMessageDialog(null, "IO Exception" + errorMessage);
        }
    }
    
    public static void insertTagFromFile()
    {
        Path file = Paths.get("data/tags.csv");
        String[] separated;
        try{
            List<String> text = Files.readAllLines(file); // UTF-8
            text.remove(0);
            
            for(String t: text)
            {
                separated = t.split(",");
                insertTag(
                        Integer.parseInt(separated[0]), 
                        Integer.parseInt(separated[1]), 
                        separated[2].replace("\"", ""),
                        Integer.parseInt(separated[3])
                );
            }
        } catch (IOException e) {
            String errorMessage = e.getMessage();
            JOptionPane.showMessageDialog(null, "IO Exception" + errorMessage);
        }
    }
    
    public static void insertLinkFromFile()
    {

        Path file = Paths.get("data/links.csv");
        String[] separated;
        try{
            List<String> text = Files.readAllLines(file); // UTF-8
            text.remove(0);
            
            for(String t: text)
            {
                separated = t.split(",");
                switch (separated.length) {
                    case 3:
                        insertLinks(
                                Integer.parseInt(separated[0]),
                                Integer.parseInt(separated[1]),
                                Integer.parseInt(separated[2])
                        );  break;
                    case 2:
                        insertLinks(
                                Integer.parseInt(separated[0]),
                                Integer.parseInt(separated[1])
                        );  break;
                    default:
                        throw new IllegalArgumentException("Invalid input for the insertLink() function");
                }
            }
        } catch (IOException e) {
            String errorMessage = e.getMessage();
            JOptionPane.showMessageDialog(null, "IO Exception" + errorMessage);
        }
    }
    
    public static List<Movie> provideTestMovies(int size) {
        List<Movie> movies = new ArrayList<>();
        
        IntStream.range(0, size).forEach(n ->
        {
            movies.add(new Movie(0, "Sample Movie"));
        });
        
        return movies;
    }
    
    
    public static void main(String[] args) {
//        JFrame frame = new JFrame("Test frame");
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setBounds(0, 0, 300, 300);
//        frame.add(new MovieCard());
//        frame.setVisible(true);
        
        
        // TODO code application logic here
//        Repository.connect(Repository.Driver.MySQL ,"movie-lens.cpzst9uo9qun.ap-northeast-1.rds.amazonaws.com", 3306, "mydb" , "root", "rsTTMA2sHyUL");
        Repository.connect(Repository.Driver.MySQL ,"127.0.0.1", 3306, "mydb" , "root", "rsTTMA2sHyUL");
        if(Repository.findTagsByIdOrdered(1).isEmpty())
        {
            Repository.insertMoviesFromFile();
            Repository.insertRatingFromFile();
            Repository.insertTagFromFile();
            Repository.insertLinkFromFile();
        }
        
//        System.out.println(Repository.findRateCountById(1));

//        Repository.loadMovies(false, false);
////        System.out.println(Repository.insertLinks(2, 30, 50));
//        System.out.println(Repository.findImdbIdById(2));
//        System.out.println(Repository.findTmdbIdById(2));
////        insertTagFromFile();
//        List<Movie> movies = findTopRatedMovies(2, 10);
//        
//        for(Movie m: movies)
//            System.out.println(m);
    
//        Repository.insertLinkFromFile();
//        Repository.insertGenresFromFile();
        System.out.println(Repository.findTagsByIdOrdered(1));
    }
    
}