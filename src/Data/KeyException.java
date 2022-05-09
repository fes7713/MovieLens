/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

/**
 *
 * @author fes77
 */
public class KeyException extends Exception {
    private static final long serialVersionUID = 1L; 

    public KeyException(String key){
        super("KeyException : " + key +  "does not exists");
    }
}
