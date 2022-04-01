/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_backend;
import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.evenement;
import gui.Home;
import gui.LoginForm;
import services.ServiceEvent;

import java.util.ArrayList;
/**
 *
 * @author Arij Hajji
 */
public class ListEventbackForm extends Form {

    public static evenement evenementActuelle = null;
    private String url="http://localhost:8080/assets/";
    private EncodedImage enc;
    private String imageName;
               public Resources theme;

    public ListEventbackForm(Form previous) {
        super("", new BoxLayout(BoxLayout.Y_AXIS));

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

        addGUIs();
        getToolbar().hideToolbar();
    }

    public void refresh() {
        this.removeAll();
        addGUIs();


        this.refreshTheme();
    }
    private void addGUIs() {
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


        ArrayList<evenement> evenements = ServiceEvent.getInstance().getAllEvenement();
        for (int i = 0; i < evenements.size(); i++) {
            this.add(evenement(evenements.get(i)));
        }


    }
    private Component evenement(evenement evenement){
        Container evenementModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        evenementModel.setUIID("evenementContainer");


        imageName = evenement.getImageName();
        //System.out.println(url+imageName);
        setTitle("evenement :");
        Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
        FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
        EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 10, p.getHeight() *8), false);
        Image img = URLImage.createToStorage(placeholder, imageName, url+imageName);
        ImageViewer iv = new ImageViewer(img);
        Label labelTitre = new Label("Titre: "+(String) evenement.getNom());
        labelTitre.setUIID("titre");
        Label labelDate = new Label(" Date : " +evenement.getDate());
        labelDate.setUIID("dateLabel");
        Label labelDescription = new Label(" Description : " +evenement.getDescription());
        labelDescription.setUIID("dateLabel");

        //Container btnsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        // btnsContainer.setUIID("buttonsContainer");
        // btnsContainer.setPreferredH(250);

        Button btnModifier = new Button();
        btnModifier.setUIID("actionButton");
        Button btnSupprimer = new Button();
        btnSupprimer.setUIID("actionButton");
        FontImage.setMaterialIcon(btnSupprimer, FontImage.MATERIAL_DELETE);
        FontImage.setMaterialIcon(btnModifier, FontImage.MATERIAL_UPDATE);




        btnModifier.addActionListener(action -> {

            evenementActuelle = evenement;
            new ModifierEventForm(this).show();

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
                ServiceEvent.getInstance().supprimerEvenement(evenement);
                evenementActuelle = null;
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



        // btnsContainer.addAll(btnModifier,btnSupprimer);
        evenementModel.addAll(labelTitre,labelDate,labelDescription,iv,btnModifier,btnSupprimer);


        return evenementModel;
    }





}
