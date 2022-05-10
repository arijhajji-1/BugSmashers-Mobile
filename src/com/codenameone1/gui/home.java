/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codenameone1.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.util.Resources;
import com.codenameone1.gui.AjoutReclamation;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Gloria
 */
public class home extends Form{ 
    Resources res;
       Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public home() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choisissez une option"));
        Button btnAddTask = new Button("Liste des reclamations");
        Button btnAddTask1 = new Button("ajouter une reclamation");
        Button btnAddTask2 = new Button("Statistiques reclamations");
    
            

        btnAddTask.addActionListener(e -> new ListReclamation(current).show());
        btnAddTask1.addActionListener(e -> new AjoutReclamation(current).show());
        btnAddTask2.addActionListener(e -> new statReclamation().createPieChartForm());
       
        addAll(btnAddTask,btnAddTask1,btnAddTask2);

    }
    
    
    
}
