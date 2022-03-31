/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codenameone1.gui;

import com.codenaeone1.entities.Reclamation;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codenameone1.services.serviceReclamation;
import com.codenameone1.gui.ListReclamation;
import static com.codenameone1.gui.ListReclamation.reclamationActuelle;

/**
 *
 * @author Gloria
 */
public class modifierReclamation extends Form {
      
 Label labelSujet, labelDescription,labelCategory,labelDate,labelIdCommande;
    TextField tfSujet,tfIdcommande;
    TextArea tfDescription;
    ComboBox tfCategory;
    Button btnModifier;
Picker Date;
    Form previous;
    

       

    public modifierReclamation(Form previous) {
        super(new BoxLayout(BoxLayout.Y_AXIS));

        this.previous = previous;

        addGUIs();
        addActions();
        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
//now please change les attributs et les fonction
    //what is reparationActuelle?
    //attend
    private void addGUIs() {
      
labelCategory = new Label("categorie");
        labelCategory.setUIID("defaultLabel");
        tfCategory =  new ComboBox<>(reclamationActuelle.getCategorie());
            tfCategory = new ComboBox<>();
      tfCategory.addItem("location");
      tfCategory.addItem("montage");
      tfCategory.addItem("reparation");
        labelSujet = new Label("sujet de la reclamation");
        labelSujet.setUIID("sujet");
        tfSujet = new TextField(reclamationActuelle.getSujet());

        labelDescription = new Label("Description : ");
        labelDescription.setUIID("Description");
        tfDescription = new TextArea(reclamationActuelle.getDescription());

         labelDate = new Label("Date : ");
        labelDate.setUIID("defaultLabel");
         
 
 Date = new Picker();
Date.setType(Display.PICKER_TYPE_DATE_AND_TIME);
     
        btnModifier = new Button("Modifier");

        btnModifier.setUIID("Button");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        container.setUIID("reclamationContainer");
        container.addAll(labelCategory, tfCategory, labelSujet,tfSujet, labelDescription, tfDescription,labelDate,Date, btnModifier);

        this.addAll(container);
    }

    private boolean controleDeSaisie() {
     
        if (tfDescription.getText().equals("")) {
            Dialog.show("Description vide", "", new Command("Ok"));
            return false;
        }

        return true;
    }

    private void addActions() {

        btnModifier.addActionListener((action) -> {
            if (controleDeSaisie()) {
                System.out.println("---------------");
                Reclamation reclamation = new Reclamation(
                        reclamationActuelle.getId(),
                        //reclamationActuelle.getIdCommande(),
                        tfSujet.getText(),
                        tfDescription.getText(),
                        (String) tfCategory.getSelectedItem(),
                        Date.getValue().toString()
                );
                      
                int responseCode = serviceReclamation.getInstance().modifierReclamation(reclamation);
                System.out.println("22");
                if (responseCode == 200) {
                    Dialog.show("Succés", "reclamation modifiée avec succes", new Command("Ok"));
                } else {
                    Dialog.show("Erreur", "Erreur d'modifier de reclamation. Code d'erreur : " + responseCode, new Command("Ok"));
                }

                ((ListReclamation) previous).refresh();
                previous.showBack();
            }
        });

    }
    
    
}
