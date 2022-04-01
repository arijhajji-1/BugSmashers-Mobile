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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.montage;
import java.util.ArrayList;
import services.ServiceMontage;

/**
 *
 * @author Arij Hajji
 */
public class ShowMontage extends Form {
      public static montage montageActuelle = null;
    public static Resources theme = UIManager.initFirstTheme("/theme");

   
    Button btnAjouter;

    public ShowMontage(Form previous) {
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

    private void addGUIs() {

     btnAjouter = new Button("Nouvelle Montage");
        btnAjouter.setUIID("LoginButton");

        this.add(btnAjouter);
     


        ArrayList<montage> montages = ServiceMontage.getInstance().getAllmontage();
        for (int i = 0; i < montages.size(); i++) {
            this.add(creerMontage(montages.get(i)));
        }


        
    }

  private void addActions() {
        btnAjouter.addActionListener(action -> {
            new AddMontage(this).show();
        });
    }

    private Component creerMontage(montage montage) {
        Container montageModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        montageModel.setUIID("montageContainer");
     
        Label labelProcesseur = new Label("Processeur: "+(String) montage.getProcesseur());
        labelProcesseur.setUIID("Processeur");
        Label labelCarteGraphique = new Label("Carte Graphique: "+(String) montage.getCarte_graphique());
        labelCarteGraphique.setUIID("carte graphique");
        Label LabelCartemere = new Label(montage.getCarte_mere());
        LabelCartemere.setUIID("caret mere");
        Label labelDisquesysteme = new Label("Disque systeme: "+montage.getDisque_systeme());
        labelDisquesysteme.setUIID("disque systeme");
Label labelBoitier = new Label("Boitier: "+montage.getBoitier());
        labelBoitier.setUIID("Boitier");
        Label labelStockagesupp = new Label("Stockage supp: "+montage.getStockage_supp());
        labelStockagesupp.setUIID("Boitier");
        Container btnsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        btnsContainer.setUIID("buttonsContainer");
        btnsContainer.setPreferredH(250);

       Button btnModifier = new Button();
        btnModifier.setUIID("actionButton");
              Button btnSupprimer = new Button();
              btnSupprimer.setUIID("actionButton");
                FontImage.setMaterialIcon(btnSupprimer, FontImage.MATERIAL_DELETE);
                FontImage.setMaterialIcon(btnModifier, FontImage.MATERIAL_UPDATE);
       

        

        btnModifier.addActionListener(action -> {

           montageActuelle = montage;
            new ModifierMontage(this).show();
            ServiceMontage.getInstance().modifierMontage(montage);

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
                ServiceMontage.getInstance().supprimerMontage(montage);
                montageActuelle = null;
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

        

        btnsContainer.addAll(btnModifier,btnSupprimer);
      montageModel.addAll(labelProcesseur,  labelCarteGraphique, LabelCartemere, labelDisquesysteme,labelBoitier,labelStockagesupp, btnsContainer);     
        return montageModel;


    }
}
