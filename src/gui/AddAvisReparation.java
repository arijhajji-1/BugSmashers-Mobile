/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.MyApplication;
import static gui.ShowReparation.reparationActuelle;
import entities.AvisReparation;
import services.ServiceAvisReparation;

/**
 *
 * @author Arij Hajji
 */
public class AddAvisReparation extends Form {
      Label labelDescription;
   
    TextArea tfDescription;
TextField tfId;
    Button btnAjouter;
    Form previous;
    public AddAvisReparation(Form previous) {
         super(new BoxLayout(BoxLayout.Y_AXIS));
        
        this.previous = previous;
        
        addGUIs();
        addActions();
      
        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> Home.accueilFrontForm.showBack());
    }

    private void addGUIs() {

      
        labelDescription = new Label("Description : ");
        labelDescription.setUIID("defaultLabel");
        tfDescription = new TextArea();

                

System.out.println(reparationActuelle.getId());
 

        btnAjouter = new Button("Ajouter");

        btnAjouter.setUIID("Button");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        container.setUIID("AvisReparationContainer");
        container.addAll( labelDescription, tfDescription,btnAjouter);

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

        btnAjouter.addActionListener((ActionEvent action) -> {
          
            if (controleDeSaisie()) {
               
 
                int responseCode;
                AvisReparation t = new AvisReparation(tfDescription.getText(),String.valueOf(MyApplication.getSession().getEmail()),String.valueOf(MyApplication.getSession().getNom()),MyApplication.getSession().getId());

               responseCode = ServiceAvisReparation.getInstance().AddAvisReparation((int)reparationActuelle.getId(),t);
                
                if (responseCode == 200) {
                    Dialog.show("Succés", "Avis ajouté avec succes", new Command("Ok"));
                } else {
                    Dialog.show("Erreur", "Erreur d'ajout de Avois. Code d'erreur : " + responseCode, new Command("Ok"));
                }
                
               
            }
        });

    }
  
}
