
package Service;

import Core.DataSource;
import Entity.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class serviceCategory {
    
    PreparedStatement pst;
    ResultSet rs;
    Connection cnx= DataSource.getInstance().getConnection();
    public Statement ste;

    public serviceCategory() {
        try {
            ste = cnx.createStatement();
                    } catch (SQLException ex) {
            Logger.getLogger(serviceCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AjouterCat(Category c) throws SQLException{
        String req= "INSERT INTO categorie  (libelle,description,date_ajout,image) VALUES (?,?,?,?)";
        PreparedStatement prs= cnx.prepareStatement(req);
        prs.setString(1, c.getLibelle());
        prs.setString(2, c.getDescription());
        prs.setString(3, c.getDate_ajout());
        prs.setString(4, c.getImage());
        
        int executeUpdate = prs.executeUpdate();
        System.out.println(" Ajoutée");
    }
    
    public void supprimer(int idcat) throws SQLException {
        Scanner sc = new Scanner(System.in);
       
           String req = "DELETE from  categorie  WHERE id =?";
          PreparedStatement pre = cnx.prepareStatement(req);
            pre.setInt(1, idcat);
            System.out.println(" categorie supprimee ");
            pre.executeUpdate();
    }
    
    public ResultSet ConsulterTT() throws SQLException {
        
       String requete= "select * from categorie";
       rs = ste.executeQuery(requete);
       return rs;
     
    }
    
    public void update(int id) throws SQLException{

    
    
      Scanner sc = new Scanner(System.in);
        
        String a,b,e,c;
        
        
          System.out.println("le libelle");
        a= sc.next();
        System.out.println("la description");
         b= sc.next();
         System.out.println("la date d'ajout");
         c= sc.next();
         System.out.println("l'url image");
         e= sc.next();
        
      
     String req = "UPDATE `categorie` SET `libelle`=?,`description`=?,`date_ajout`=?,`image`=?  WHERE id=?";
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setString(1,a);
        pre.setString(2,b);
        pre.setString(3, c);
        pre.setString(4, e);
         pre.setInt(5, id);
       
       
        pre.executeUpdate();
      
        System.out.println("Categorie Modifie avec Succees");
    
}
    
    public List<Category>ConsulterCategory(List<Category>list) throws SQLException {
        
        
        String req = "SELECT * from categorie";
        ResultSet res; 
        res = ste.executeQuery(req);
    while (res.next()) {
                Category p = new Category(res.getInt("id"),
                        res.getString("libelle"), 
                        res.getString("description"),
                        res.getString("date_ajout"));
                list.add(p);


            }

        
        return list;

        }
    
    

    
    
}

