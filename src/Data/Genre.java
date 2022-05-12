/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

/**
 *
 * @author fes77
 */
public enum Genre {
    Action,
    Adventure,
    Animation,
    Children,
    Comedy,
    Crime,
    Documentary,
    Drama,
    Fantasy,
    Film_Noir,
    Horror,
    Musical,
    Mystery,
    Romance,
    Sci_Fi,
    Thriller,
    War,
    Western,
    None;
    
    
    public String toString()
    {
        return super.toString().replace("_", "-");
    }
}
