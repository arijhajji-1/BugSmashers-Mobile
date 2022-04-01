/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.myapp.entities;

/**
 *
 * @author USER
 */
public class Category {
private int id;
private String label;

public Category(int id, String label){
    this.id = id;
    this.label = label;
}
public Category(){

}
public int getId(){
    return id;
}
public void setId(int id){
    this.id = id;
}
public String getLabel(){
    return label;
}
public void setLabel(String label){
    this.label = label;
}
@Override
    public String toString() {
        return "Category : " + "id = " + id + ", nom = " + label +"\n";
    }
}
