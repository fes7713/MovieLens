/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Repository;

/**
 *
 * @author fes77
 */
public enum MovieKeys {
    MOVIE_ID("movieId");
    
    private String keyName;
    
    private  MovieKeys(String name)
    {
        keyName = name;
    }

    @Override
    public String toString() {
        return keyName;
    }
}
