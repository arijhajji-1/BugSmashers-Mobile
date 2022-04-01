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
public class reparation {
    private int id,iduser;
    private String category,type,description,Reserver,telephone,etat;

    public reparation() {
    }

    public reparation(String category) {
        this.category = category;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
   

 

    public reparation(int id, String category, String type, String description, String Reserver) {
        this.id = id;
        this.category = category;
        this.type = type;
        this.description = description;
        this.Reserver = Reserver;
    }

    public reparation(String category, String type, String description, String Reserver) {
        this.category = category;
        this.type = type;
        this.description = description;
        this.Reserver = Reserver;
    }

    public reparation(int id, String category, String type, String description, String Reserver, String telephone) {
        this.id = id;
        this.category = category;
        this.type = type;
        this.description = description;
        this.Reserver = Reserver;
        this.telephone = telephone;
    }

    public reparation(int id, int iduser, String category, String type, String description, String Reserver, String telephone, String etat) {
        this.id = id;
        this.iduser = iduser;
        this.category = category;
        this.type = type;
        this.description = description;
        this.Reserver = Reserver;
        this.telephone = telephone;
        this.etat = etat;
    }

    public reparation(String category, String type, String description, String Reserver, String telephone) {
        this.category = category;
        this.type = type;
        this.description = description;
        this.Reserver = Reserver;
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = "en Cours";
    }



    public void setReserver(String Reserver) {
        this.Reserver = Reserver;
    }

    public String getReserver() {
        return Reserver;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "reparation{" + "category=" + category + ", type=" + type + ", description=" + description + ", Reserver=" + Reserver + ", telephone=" + telephone + ", etat=" + etat + '}';
    }

   


    

    
    
}
