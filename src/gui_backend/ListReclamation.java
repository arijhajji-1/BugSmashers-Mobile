/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_backend;
import entities.Reclamation;
import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import gui.AjoutReclamation;
import gui.Home;
import gui.LoginForm;
import services.serviceReclamation;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author Arij Hajji
 */
public class ListReclamation extends Form{
    
    
                  public static Reclamation reclamationActuelle = null;
    public static Resources theme = UIManager.initFirstTheme("/theme");
    
// public static int AvisReparation ;
                  TextField searchbar;

    Button btnAjouter;

    public ListReclamation(Form previous) {
        super("", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
        addActions();
        getToolbar().hideToolbar();
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
    
        btnAjouter = new Button("soumettre une reclamation");
        btnAjouter.setUIID("LoginButton");

       
     searchbar = new TextField(text,"Search");
  
 this.addAll(btnAjouter,searchbar);
 

      /*  ArrayList<Reclamation> reclamations = serviceReclamation.getInstance().recherche(text);
        for (int i = 0; i < reclamations.size(); i++) {
            this.add(creerReparation(reclamations.get(i)));
        }*/
          
 
    }
    private void addGUIs() {
    
        btnAjouter = new Button("Nouvelle reclamation");
        btnAjouter.setUIID("LoginButton");

       
     searchbar = new TextField("","Search");
  
 this.addAll(btnAjouter,searchbar);

     FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);

        this.getToolbar().addCommandToRightBar(null,icon,evt1 -> new LoginForm(theme).show());

        this.getToolbar().addCommandToRightBar(null,icon,evt1 -> new HomeBack(this).show());
        this.getToolbar().addCommandToSideMenu("HOME",null,evt -> new Home().show());
        this.getToolbar().addCommandToSideMenu("Reparation",null,evt1 -> new AfficheReparation(this).show());
        this.getToolbar().addCommandToSideMenu("Montage",null,evt1 -> new AfficheMontage(this).show());
        this.getToolbar().addCommandToSideMenu("Avis reparation",null,evt1 -> new AfficheAvisReparation(this).show());
                this.getToolbar().addCommandToSideMenu("Ajouter Produit",null,evt1 -> new AddProduitAcheterForm(this).show());
               this.getToolbar().addCommandToSideMenu("Ajouter Produit louer",null,evt1 -> new AddProduitLouerForm(this).show());
                this.getToolbar().addCommandToSideMenu("Ajouter category",null,evt1 -> new AddCategoryForm(this).show());
               this.getToolbar().addCommandToSideMenu("List category",null,evt1 -> new ListCategoryBack(this).show());
this.getToolbar().addCommandToSideMenu("List produit",null,evt1 -> new ListProduitAcheterBack(this).show());

                this.getToolbar().addCommandToSideMenu("List reclamation",null,evt1 -> new ListReclamation(this).show());
                this.getToolbar().addCommandToSideMenu("Statistique Reclamation",null,evt1 -> new statReclamation());

                this.getToolbar().addCommandToSideMenu("Ajouter actualite",null,evt1 -> new AddActualiteForm(this).show());
           this.getToolbar().addCommandToSideMenu("Ajouter evenement",null,evt1 -> new addEventForm(this).show());
        this.getToolbar().addCommandToSideMenu("list evenement",null,evt1 -> new ListEventbackForm(this).show());
        this.getToolbar().addCommandToSideMenu("list actualite",null,evt1 -> new ListeActbackForm(this).show());


                           this.getToolbar().addCommandToSideMenu("Statistique Produit",null,evt1 -> new PieChartProdA().show());
        ArrayList<Reclamation> reclamations = serviceReclamation.getInstance().getAllReclamation();
        for (int i = 0; i < reclamations.size(); i++) {
            this.add(creerReparation(reclamations.get(i)));
        }
          

            
    }

    private void addActions() {
        btnAjouter.addActionListener(action -> {
            new AjoutReclamation(this).show();
        });
        searchbar.addDataChangedListener(new DataChangedListener() {
            @Override
            public void dataChanged(int type, int index) {
                refresh_(searchbar.getText());
           // ServiceReparation.getInstance().recherche(searchbar.getText());

                
            

}
           
});
    }

    private Component creerReparation(Reclamation reclamation) {
        Container reclamationModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        reclamationModel.setUIID("reclamationContainer");
      
                SpanLabel sp1 = new SpanLabel("categorie: " + "  " + reclamation.getCategorie());
                sp1.setTextUIID("categorieLabel");
                SpanLabel sp2 = new SpanLabel("sujet: " + "  " + reclamation.getSujet());
                sp2.setTextUIID("sujetLabel");
                SpanLabel sp3 = new SpanLabel("description: " + "  " + reclamation.getDescription());
                sp3.setTextUIID("descriptionLabel");
               SpanLabel sp4 = new SpanLabel("date: " + "  " + reclamation.getDate()); 
               sp4.setTextUIID("dateLabel");
    /*    
   Label labelIdcommande = new Label("id commande :"+reclamation.getIdCommande());
       // labelIdcommande.setUIID("idcommandeLabel");
        Label labelCategory = new Label("Categorie :"+(String) reclamation.getCategorie());
       // labelCategory.setUIID("categorieLabel");
        Label labelSujet = new Label((String) reclamation.getSujet());
       // labelSujet.setUIID("sujetLabel");
        SpanLabel spanLabelDescription = new SpanLabel("Description: "+reclamation.getDescription());
        //spanLabelDescription.setTextUIID("descriptionLabel");
        Label labelDate = new Label(" Date : " +reclamation.getDate());
       // labelDate.setUIID("dateLabel");
*/
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

           reclamationActuelle = reclamation;
            new modifierReclamation(this).show();
            serviceReclamation.getInstance().modifierReclamation(reclamation);

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
                serviceReclamation.getInstance().DeleteReclamation(reclamation);
                reclamationActuelle = null;
                dlg.dispose();
                this.removeAll();
                addGUIs();
                this.refreshTheme();
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
           
            dlg.show(1000, 1000, 10, 10);

        });

        

        btnsContainer.addAll(btnModifier,btnSupprimer);
      reclamationModel.addAll( sp1,sp2,sp3,sp4,btnsContainer);     
        return reclamationModel;//labelIdcommande,labelCategory, labelSujet, spanLabelDescription,labelDate,


 
   
    
    }
}
    
                  