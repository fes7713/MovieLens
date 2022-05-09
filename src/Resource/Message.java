/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resource;

import javax.swing.JOptionPane;

/**
 *
 * @author fes77
 */
public enum Message {
    NO_MATCH_ERROR("There was no match for the request"),
    EXECUTED("Executed statement successfully"),
    EXECUTING("Executing statement");
    private String msg;
    
    private Message(String msg)
    {
        this.msg = msg;
    }
    
    public String getMessage()
    {
        return msg;
    }
    
    public void printMessage()
    {
        System.out.println(msg);
    }
    
    public void printMessage(String extra)
    {
        System.out.println(msg + " : " + extra);
    }
    
    public void showMessage()
    {
        JOptionPane.showMessageDialog(null, msg);
    }
    
    public void showMessage(String extra)
    {
        JOptionPane.showMessageDialog(null, msg + " : " + extra);
    }
}
