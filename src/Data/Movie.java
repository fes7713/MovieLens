/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Repository.Keys;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import movielens.Repository;

/**
 *
 * @author fes77
 */
public class Movie implements SQLData{
    private int id;
    private String title;
    private Set<Genre> genres;
    private Map<String, Integer> tagMap;
    private float rating;
    
    public Movie() {
        this(0, null, new HashSet<>());
    }
    
    public Movie(int id, String title)
    {
        this(id, title, new HashSet<>());
        
    }
    
    public Movie(int id, String title, Set<Genre> genres)
    {
        this(id, title, genres, new HashMap<>());
    }

    public Movie(int id, String title, Set<Genre> genres, Map<String, Integer> tagMap)
    {
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.tagMap = tagMap;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Set<Genre> getGenres() {
        if(genres == null || genres.isEmpty())
            genres = Repository.findGenresById(id);
        return genres;
    }

    public Map<String, Integer> getTagMap() {
        if(tagMap == null || tagMap.isEmpty())
            tagMap = Repository.findTagsById(id);
        return tagMap;
    }

    public float getRating() {
        if(rating == 0)
            rating = Repository.findAverageRatingById(id);
        return rating;
    }


    @Override
    public void set(String key, Object... value) throws KeyException {
        switch(key)
        {
            case Keys.MOVIEID-> id = (int)value[0];
            case Keys.TITLE-> title = (String )value[0];
            case Keys.GENRE-> genres.add((Genre)value[0]);
            case Keys.TAG-> tagMap.put((String)value[0], (Integer)value[1]);
            case Keys.RATING-> rating = (Float)value[0];
            default -> throw new KeyException(key);
        }
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title=" + title + ", genres=" + genres + '}';
    }
}
