/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Core.DataSource;
import Core.LoginController;
import Entity.User;
import com.jfoenix.controls.JFXCheckBox;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.ini4j.Wini;

/**
 *
 * @author ASUS
 */
public class LoginService {
    
         PreparedStatement pst;
    ResultSet rs;
    Connection cnx= DataSource.getInstance().getCon();
    public Statement ste;
    
     public LoginService() {
        try {
            ste = cnx.createStatement();
                    } catch (SQLException ex) {
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
    public String LoginAction(User user) throws SQLException{
        String req= "Select * from user where username=? and password=?";
        PreparedStatement prs= cnx.prepareStatement(req);
        prs.setString(1, user.getUsername());
        prs.setString(2, user.getPassword());
        rs = prs.executeQuery();
        return "good job u made it here";
    }
    
    public void createiniFile(String path,String user,String pass){
        try {
        File file = new File(path);
        if(!file.exists()){ 
            file.createNewFile();
        }
        Wini wini = new Wini(new File(path));
        wini.put("Login data", "Username",user);
        wini.put("Login data", "Password",pass);
        wini.store();
        
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    public void readinifile(String path, TextField userid, PasswordField passid, JFXCheckBox remember){
        File file = new File(path);
        if(file.exists()){  
            try {
                Wini wini = new Wini(new File(path));
                String username = wini.get("Login data","Username");
                String password = wini.get("Login data","Password");
                if ((username!=null && !username.equals(""))&&(password!=null && !password.equals("")) ){
                    userid.setText(username);
                    passid.setText(password);
                    remember.setSelected(true);
                }
            } catch (IOException ex) {
                Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   
}
