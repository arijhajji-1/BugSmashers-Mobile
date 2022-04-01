/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Arij Hajji
 */
public class Commande {
private int id,Telephone;
private String nom,prenom,paiment,adresse;

    public Commande(String nom, String prenom, String paiment, String adresse,int telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.paiment = paiment;
        this.adresse = adresse;
        this.Telephone=telephone;
    }

   

    public Commande() {
    }

    

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPaiment() {
        return paiment;
    }

    public void setPaiment(String paiment) {
        this.paiment = paiment;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

public void setTelephone(int Telephone) {
        this.Telephone = Telephone;
    }

    public int getTelephone() {
        return Telephone;
    }
 

    public Commande(int id, String nom, String prenom, String paiment, String adresse,int telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.paiment = paiment;
        this.adresse = adresse;
        this.Telephone=telephone;
    }
    
}

