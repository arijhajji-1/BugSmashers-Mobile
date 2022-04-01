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
public class ProduitLouer {
private String id;
private String etat;
private boolean dispo;
private float prix;
private String nom, description, imagePath, marque, category;

  public String getId() {
        return id;
    }
public String getCategory(){
    return category;
}
public void setCategory(String category){
    this.category = category;
}
public String getMarque(){
    return marque;
}
public void setMarque(String marque){
    this.marque = marque;
}
public void setDescription(String description){
    this.description = description;
}
public String getDescription(){
    return description;
}
  public void setId(String id) {
        this.id = id;
}
  public String getEtat() {
        return etat;
    }
  public void setEtat(String etat) {
        this.etat = etat;
}
  public boolean getDispo(){
        return dispo;
}
public void setDispo(boolean dispo){
        this.dispo = dispo;
}
  public String getNom() {
        return nom;
    }
  public void setNom(String nom) {
        this.nom = nom;
}
public float getPrix() {
        return prix;
    }
  public void setPrix(float prix) {
        this.prix = prix;
}
public String getImagePath(){
return imagePath;
}
public void setImagePath(String imagePath){
this.imagePath = imagePath;
}

public ProduitLouer(String id, String nom, float prix, String etat) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.etat = etat;
    }
public ProduitLouer(String nom, String description, String etat, boolean dispo, String marque, float prix, String category) {
        this.nom = nom;
        this.description = description;
        this.marque = marque;
        this.category = category;
        this.prix = prix;
        this.etat = etat;
        this.dispo = dispo;
    }
public ProduitLouer(String nom, String description, String etat, boolean dispo, String marque, float prix, String category, String imagePath) {
        this.nom = nom;
        this.description = description;
        this.marque = marque;
        this.category = category;
        this.prix = prix;
        this.etat = etat;
        this.dispo = dispo; 
        this.imagePath = imagePath;
    }
public ProduitLouer(String id,String nom, String category, String description, boolean dispo, String etat, String marque, float prix, String imagePath) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.marque = marque;
        this.imagePath = imagePath;
        this.prix = prix;
        this.etat = etat;
        this.category = category;
        this.dispo = dispo;
    }
public ProduitLouer() {
    }
 @Override
    public String toString() {
        return "Produitlouer : " + "id = " + id + " , nom = " + nom +
 " , prix = " + prix + " , etat = " + etat + " , category = "+ category + " , marque = "
 +marque+" , description = "+description+ " , dispo = "+ dispo+" \n";
    }
}
