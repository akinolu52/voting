/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.Capture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author tosyngy
 */
public class DbConnect {

    public Connection con;
    public ResultSet rs,rs2;
    public PreparedStatement ps;
    public Statement st,st2;
    JOptionPane jp;

    public DbConnect() {
        jp = new JOptionPane();
        try {

            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "evoting";
            String driver = "com.mysql.jdbc.Driver";
            String userName = "root";
            String password = "";
            try {
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(url + dbName, userName, password);
                st = con.createStatement();
                st2 = con.createStatement();
                //System.out.println("Connected to the database");
            } catch (Exception e) {
                int chk = jp.showConfirmDialog(null, "Connection Off: Click Yes to Reconnect\nIf you are rounning on localhost\nMake sure server has been started", "RE-CONNECT", jp.YES_NO_CANCEL_OPTION);
                if (chk == 0) {
                    new DbConnect();
                } else {
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            //System.out.println("connector Error  " + e);
        }

    }
     public void num(JTextField a, Object ch) {
//          Object ch = evt.getKeyChar();
//        num(jTextField1, ch);

        String cha = ch.toString();
        if (!"1234567890\b\n".contains(cha)) {
            a.setText(a.getText().substring(0, (a.getText().length() - 1)));
        }
    }
     public void text(JTextField a, Object ch) {
//          Object ch = evt.getKeyChar();
//        num(jTextField1, ch);

        String cha = ch.toString().toLowerCase();
        if (!"abcdefghijklmnopqrstuvwxyz \b\n".contains(cha)) {
            a.setText(a.getText().substring(0, (a.getText().length() - 1)));
        }
    }
     public void text_num(JTextField a, Object ch) {
//          Object ch = evt.getKeyChar();
//        num(jTextField1, ch);

        String cha = ch.toString().toLowerCase();
        if (!"abcdefghijklmnopqrstuvwxyz1234567890. \b\n".contains(cha)) {
            a.setText(a.getText().substring(0, (a.getText().length() - 1)));
        }
    }

}
