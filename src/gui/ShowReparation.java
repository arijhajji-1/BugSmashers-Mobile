/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.AvisReparation;
import entities.reparation;
import java.util.ArrayList;
import services.ServiceAvisReparation;
import services.ServiceReparation;

/**
 *
 * @author Arij Hajji
 */
public class ShowReparation extends Form {
                public static reparation reparationActuelle = null;
    public static Resources theme = UIManager.initFirstTheme("/theme");
 public static int AvisReparation ;
                  TextField searchbar;

    Button btnAjouter;

    public ShowReparation(Form previous) {
        super("", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
        addActions();
        // getToolbar().hideToolbar();        
        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> Home.accueilFrontForm.showBack());

        
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        addActions();
        this.refreshTheme();
    }
     public void refresh_(String text) {
        this.removeAll();
        addGUIs(text);
        addActions();
        this.refreshTheme();
    }
 private void addGUIs(String text) {
    
        btnAjouter = new Button("Nouvelle reparation");
        btnAjouter.setUIID("LoginButton");

       
     searchbar = new TextField(text,"Search");
  
 this.addAll(btnAjouter,searchbar);

        ArrayList<reparation> reparations = ServiceReparation.getInstance().recherche(text);
        for (int i = 0; i < reparations.size(); i++) {
            this.add(creerReparation(reparations.get(i)));
        }
          

            
    }
    private void addGUIs() {
    
        btnAjouter = new Button("Nouvelle reparation");
        btnAjouter.setUIID("LoginButton");

       
     searchbar = new TextField("","Search");
  
 this.addAll(btnAjouter,searchbar);

        ArrayList<reparation> reparations = ServiceReparation.getInstance().getAllreparationsP();
        for (int i = 0; i < reparations.size(); i++) {
            this.add(creerReparation(reparations.get(i)));
        }
          

            
    }

    private void addActions() {
        btnAjouter.addActionListener(action -> {
            new AddReparation(this).show();
        });
        searchbar.addDataChangedListener(new DataChangedListener() {
            @Override
            public void dataChanged(int type, int index) {
                refresh_(searchbar.getText());
           // ServiceReparation.getInstance().recherche(searchbar.getText());

                
            

}
           
});
    }

    private Component creerReparation(reparation reparation) {
        Container reparationModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        reparationModel.setUIID("reparationContainer");

        Label labelCategory = new Label("Category :"+(String) reparation.getCategory());
        labelCategory.setUIID("defaultLabel");
        Label labelType = new Label((String) reparation.getType());
        labelType.setUIID("typeLabel");
        SpanLabel spanLabelDescription = new SpanLabel("Description: "+reparation.getDescription());
        spanLabelDescription.setTextUIID("descriptionLabel");
        Label labelDateReservation = new Label(" Date : " +reparation.getReserver());
        labelDateReservation.setUIID("dateLabel");

        Container btnsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        btnsContainer.setUIID("buttonsContainer");
        btnsContainer.setPreferredH(200);

       Button btnModifier = new Button();
        btnModifier.setUIID("actionButton");
              Button btnSupprimer = new Button();
              btnSupprimer.setUIID("actionButton");
                Button btnAvis = new Button();
              btnAvis.setUIID("actionButton");
                FontImage.setMaterialIcon(btnSupprimer, FontImage.MATERIAL_DELETE);
                FontImage.setMaterialIcon(btnModifier, FontImage.MATERIAL_UPDATE);
      
 FontImage.setMaterialIcon(btnAvis, FontImage.MATERIAL_ADD);
        btnModifier.addActionListener(action -> {

           reparationActuelle = reparation;
            new ModifierReparation(this).show();
            ServiceReparation.getInstance().modifierReparation(reparation);

            addGUIs();
        });
    btnAvis.addActionListener(action -> {

           reparationActuelle = reparation;
        
         
            new AddAvisReparation(this).show();
           

            addGUIs();
        });
   
       
 btnSupprimer.addActionListener(action -> {

            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                ServiceReparation.getInstance().supprimerReparation(reparation);
                reparationActuelle = null;
                dlg.dispose();
                this.removeAll();
                addGUIs();
                this.refreshTheme();
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            //Dimension pre = dlg.getContentPane().getPreferredSize();
            //dlg.show(0, 0, Display.getInstance().getDisplayWidth() - (pre.getWidth() + pre.getWidth() / 6), 0);
            dlg.show(1000, 1000, 10, 10);

        });

        

        btnsContainer.addAll(btnModifier,btnSupprimer,btnAvis);
      reparationModel.addAll(labelCategory, labelType, spanLabelDescription,labelDateReservation, btnsContainer);     
        return reparationModel;


    }
   
}
