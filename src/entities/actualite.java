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
public class actualite {
    private int id;
    private String titre,date,description,imageName;

    public actualite(int id, String titre, String date, String description, String imageName) {
        this.id = id;
        this.titre = titre;
        this.date = date;
        this.description = description;
        this.imageName = imageName;
    }

    public actualite( String titre, String date, String description, String imageName) {

        this.titre = titre;
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

    public String getTitre() {
        return titre;
    }

    public void setNom(String nom) {
        this.titre = titre;
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

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "actualite{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
