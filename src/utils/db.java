/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import com.codename1.io.ConnectionRequest;


/**
 *
 * @author Arij Hajji
 */
public class db {
     private static db instance;
    private ConnectionRequest connexion=null;
	
	/* Connexion à la base de données */
	String url = "jdbc:mysql://localhost:3307/reloua";
	String utilisateur = "root";
	String motDePasse = "";
	
	
	public db(){
		connexion = new ConnectionRequest();
	}
        
	public static db getInstance() {
        if (instance == null) {
            instance = new db();
        }
        return instance;
    }

    public ConnectionRequest getConnexion() {
        return connexion;
    }
}
