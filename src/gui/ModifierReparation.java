/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import entities.ProduitAcheter;

import entities.reparation;
import static gui.ShowReparation.reparationActuelle;
import services.ServiceProduit;
import services.ServiceReparation;

/**
 *
 * @author Arij Hajji
 */
public class ModifierReparation extends Form {
      Label labelType, labelDescription,labelCategory,labelDate;
    TextField tfType;
    TextArea tfDescription;
    ComboBox tfCategory;
    Button btnModifier;
Picker Date;
    Form previous;

    public ModifierReparation(Form previous) {
        super(new BoxLayout(BoxLayout.Y_AXIS));

        this.previous = previous;

        addGUIs();
        addActions();
        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> Home.accueilFrontForm.showBack());

    }

    private void addGUIs() {
labelCategory = new Label("category de la reparation");
        labelCategory.setUIID("defaultLabel");
        tfCategory =  new ComboBox<>(reparationActuelle.getCategory());
            tfCategory = new ComboBox<>();
        for (ProduitAcheter produit : ServiceProduit.getInstance(). getAllProduitsAcheter()) {
            tfCategory.addItem(produit.getNom());
        }
        labelType = new Label("Type de la reparation");
        labelType.setUIID("Type");
        tfType = new TextField(reparationActuelle.getType());

        labelDescription = new Label("Description : ");
        labelDescription.setUIID("Description");
        tfDescription = new TextArea(reparationActuelle.getDescription());

         labelDate = new Label("Date : ");
        labelDate.setUIID("defaultLabel");
         
 
Picker Date = new Picker();
Date.setType(Display.PICKER_TYPE_DATE_AND_TIME);
     
        btnModifier = new Button("Modifier");

        btnModifier.setUIID("Button");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        container.setUIID("reparationContainer");
        container.addAll(labelCategory, tfCategory, labelType,tfType, labelDescription, tfDescription,labelDate,Date, btnModifier);

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
                System.out.println("11");
                reparation reparation = new reparation(
                        reparationActuelle.getId(),
                      (String) tfCategory.getSelectedItem(),
                        tfType.getText(),
                        tfDescription.getText(),
                        Date.getValue().toString());
                      
                int responseCode = ServiceReparation.getInstance().modifierReparation(reparation);
                System.out.println("22");
                if (responseCode == 200) {
                    Dialog.show("Succés", "reparation modifieré avec succes", new Command("Ok"));
                } else {
                    Dialog.show("Erreur", "Erreur d'modifier de reparation. Code d'erreur : " + responseCode, new Command("Ok"));
                }

                ((ShowReparation) previous).refresh();
                previous.showBack();
            }
        });

    }
    
}
