/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
Form current;
    public HomeForm() {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddProduitAcheter = new Button("Ajouter Produit Acheter");
        Button btnAddProduitLouer = new Button("Ajouter Produit Louer");
        Button btnListProduits = new Button("Liste des produit acheter");
         Button btnListProduitsB = new Button("Liste des produit acheter Back");
         
         Button btnaddCategory = new Button("Ajouter Category");
         Button listCategory = new Button("list Category");
            Button chart = new Button("chart");
        btnAddProduitAcheter.addActionListener(e-> new AddProduitAcheterForm(current).show());
        btnAddProduitLouer.addActionListener(e-> new AddProduitLouerForm(current).show());
        btnListProduits.addActionListener(e-> new ListProduitAcheter(current).show());
        btnListProduitsB.addActionListener(e-> new ListProduitAcheterBack(current).show()); 
        btnaddCategory.addActionListener(e-> new AddCategoryForm(current).show());
        listCategory.addActionListener(e-> new ListCategoryBack(current).show());
        chart.addActionListener(e-> new PieChartprodA().show());
        addAll(btnAddProduitAcheter,btnAddProduitLouer,btnListProduits,btnListProduitsB,btnaddCategory,listCategory,chart);
        
        
        
    }
    
    
}
