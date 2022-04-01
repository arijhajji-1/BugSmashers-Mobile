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
import java.util.ArrayList;
import services.ServiceAvisReparation;
/**
 *
 * @author Arij Hajji
 */
public class ShowAvisReparation extends Form {
      public static AvisReparation AvisReparationActuelle = null;
    public static Resources theme = UIManager.initFirstTheme("/theme");

   
    Button btnAjouter;

    public ShowAvisReparation(Form previous) {
      super("", new BoxLayout(BoxLayout.Y_AXIS));
   

        getToolbar().hideToolbar();
    }

    public void refresh() {
         this.removeAll();
   addGUIs();
        this.refreshTheme();
    }
     private void addGUIs() {
    
       

        ArrayList<AvisReparation> Avisreparations = ServiceAvisReparation.getInstance().getAllAvisreparationsP();
        for (int i = 0; i < Avisreparations.size(); i++) {
            this.add(creerAvisReparation(Avisreparations.get(i)));
        }
     }
         private Component creerAvisReparation(AvisReparation AvisReparation) {
        Container AvisreparationModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        AvisreparationModel.setUIID("AvisreparationContainer");
Label sp = new Label();
       sp.setText("Id : " + AvisReparation.getId());
    
        SpanLabel spanLabelDescription = new SpanLabel(AvisReparation.getDescription());
        spanLabelDescription.setTextUIID("descriptionLabel");
  
Label sp2 = new Label();
       sp2.setText("Id reparation : " + AvisReparation.getIdreparation());
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
   
   
       
 btnSupprimer.addActionListener(action -> {

            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                ServiceAvisReparation.getInstance().supprimerAvisReparation(AvisReparation);
                AvisReparationActuelle = null;
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
      AvisreparationModel.addAll(spanLabelDescription,sp,sp2, btnsContainer);     
        return AvisreparationModel;


    }
}
