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
public class ProduitAcheter {

private String id;
private int qte;
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
  public int getQte() {
        return qte;
    }
  public void setQte(int qte) {
        this.qte = qte;
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

public ProduitAcheter(String id, String nom, float prix, int qte) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.qte = qte;
    }
public ProduitAcheter(String nom, String description, int qte, String marque, float prix, String category) {
        this.nom = nom;
        this.description=description;
        this.marque = marque;
        this.category = category;
        this.prix = prix;
        this.qte = qte;
    }
public ProduitAcheter(String nom, String description, int qte, String marque, float prix, String category, String imagePath) {
        this.nom = nom;
        this.description=description;
        this.marque = marque;
        this.category = category;
        this.prix = prix;
        this.qte = qte;
        this.imagePath = imagePath;
    }
public ProduitAcheter(String id,String nom, String category, String description, int qte, String marque, float prix, String imagePath) {
        this.id = id;
        this.nom = nom;
        this.description=description;
        this.marque = marque;
        this.imagePath = imagePath;
        this.prix = prix;
        this.qte = qte;
        this.category = category;
    }
public ProduitAcheter() {
    }
 @Override
    public String toString() {
        return "ProduitAcheter : " + "id = " + id + ", nom = " + nom +
 ", prix = " + prix + ", qte = " + qte + ", category = "+ category + ", marque = "
 +marque+" , description = "+description+ " Image Name = "+imagePath+"\n";
    }


}