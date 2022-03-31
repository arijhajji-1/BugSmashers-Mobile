/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codenaeone1.entities;

import java.util.Date;

/**
 *
 * @author Gloria
 */
public class Reclamation {
 
    private int id;
    private int idCommande;
    private String description;
    private String sujet;
    private String date;

    public void setDate(String date) {
        this.date = date;
    }
    private String categorie;
   // private int etat;

   

    public void setId(int id) {
        this.id = id;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

  

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

   /* public void setEtat(int etat) {
        this.etat = etat;
    }*/

    public int getId() {
        return id;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public String getDescription() {
        return description;
    }

    public String getSujet() {
        return sujet;
    }

    public String getDate() {
        return date;
    }

   
    public String getCategorie() {
        return categorie;
    }

   /* public int getEtat() {
        return etat;
    }*/

    public Reclamation(int id,String categorie, String sujet, String description) {
        this.id = id;
        this.description = description;
        this.sujet = sujet;
    
        this.categorie = categorie;
    }

    public Reclamation(int id, int idCommande, String description, String sujet, String categorie, String date) {
        this.id = id;
        this.idCommande = idCommande;
        this.description = description;
        this.sujet = sujet;
        this.categorie = categorie;
        this.date = date;
    }

   public Reclamation(int id, String sujet, String description,String categorie,String date) {
        this.id = id;
        this.description = description;
        this.sujet = sujet;
        this.date = date;
        this.categorie = categorie;
    }

 

   
    public Reclamation() {
        
    }

    public Reclamation(String description, String sujet, String categorie) {
        this.description = description;
        this.sujet = sujet;
        this.categorie = categorie;
    }
    

   
  
                                            
                            
    
}
