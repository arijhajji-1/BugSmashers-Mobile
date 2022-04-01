/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import entities.Commande;
import services.ServiceCommande;
import gui.Home;
/**
 *
 * @author Arij Hajji
 */
public class ListCommandesForm extends Form {
Form previous;
public static Commande commandeActuelle = null;
public ListCommandesForm(Form previous) {

        super("", new BoxLayout(BoxLayout.Y_AXIS));
this.previous = previous;
        addGUIs();
        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> Home.accueilFrontForm.showBack());
    }
public void refresh() {
        this.removeAll();
        this.refreshTheme();
    }
private void addGUIs() {



        ArrayList<Commande> commandes = ServiceCommande.getInstance().getAllCommandes();
        for (int i = 0; i < commandes.size(); i++) {
            this.add(creerCommande(commandes.get(i)));
        }



    }

    private Component creerCommande(Commande Commande) {
        Container CommandeModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        CommandeModel.setUIID("CommmandeContainer");

        Label labelNom = new Label((String) Commande.getNom());
        labelNom.setUIID("NomLabel");
        Label labelPrenom = new Label((String) Commande.getPrenom());
        labelPrenom.setUIID("PrenomLabel");
        Label labelPaiment = new Label((String) Commande.getPaiment());
        labelPaiment.setUIID("PaimentLabel");
        SpanLabel spanLabelAdresse = new SpanLabel(Commande.getAdresse());
        spanLabelAdresse.setTextUIID("AdresseLabel");
        Label labelTelephone = new Label(String.valueOf(Commande.getTelephone()));
        labelTelephone.setUIID("TelphoneLabel");
        

        Container btnsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        btnsContainer.setUIID("buttonsContainer");
        btnsContainer.setPreferredH(200);

       Button btnModifier = new Button();
        btnModifier.setUIID("actionButton");
              Button btnSupprimer = new Button();
              btnSupprimer.setUIID("actionButton");
                FontImage.setMaterialIcon(btnSupprimer, FontImage.MATERIAL_DELETE);
                FontImage.setMaterialIcon(btnModifier, FontImage.MATERIAL_UPDATE);           
 btnSupprimer.addActionListener(action -> {

            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                ServiceCommande.getInstance().supprimerCommande(Commande);
                commandeActuelle = null;
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
btnModifier.addActionListener(action -> {
commandeActuelle = Commande;
                
            new modifcommande(this).show();
            
            modifcommande.tfId.setText(String.valueOf(Commande.getId())); 
this.removeAll();
                addGUIs();
                this.refreshTheme();
        new modifcommande(this).show();
            
        });

        

        btnsContainer.addAll(btnModifier,btnSupprimer);
      CommandeModel.addAll(labelNom, labelPrenom,labelPaiment,labelTelephone, spanLabelAdresse,btnsContainer);     
        return CommandeModel;


    }

}
