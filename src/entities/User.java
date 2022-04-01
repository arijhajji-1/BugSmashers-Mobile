/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;

/**
 *
 * @author Arij Hajji
 */
public class User {

    private int id;
 private String nom;
 private String prenom,tel;
    private String email;
    private List<String> roles;
    private String password;


    public User(int id, String email, List<String> roles, String password) {
        this.id = id;
       
        this.email = email;
        this.roles = roles;
        this.password = password;
      
    }

    public User( String email, List<String> roles, String password) {
     
        this.email = email;
        this.roles = roles;
        this.password = password;
       
    }

    public User(String tel, String email, List<String> roles, String password) {
        this.tel = tel;
        this.email = email;
        this.roles = roles;
        this.password = password;
    }

    public User(int id, String tel, String email, List<String> roles, String password) {
        this.id = id;
        this.tel = tel;
        this.email = email;
        this.roles = roles;
        this.password = password;
    }

    public User(int id, String nom, String prenom, String tel, String email, List<String> roles, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
        this.roles = roles;
        this.password = password;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTel() {
        return tel;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    @Override
    public String toString() {
        return email;
    }
}

