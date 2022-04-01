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
public class evenement {
    private int id;
    private String nom,date,description,imageName;

    public evenement(int id, String nom, String date, String description, String imageName) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.description = description;
        this.imageName = imageName;
    }

    public evenement( String nom, String date, String description, String imageName) {

        this.nom = nom;
        this.date = date;
        this.description = description;
        this.imageName = imageName;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImage(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "evenement{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
