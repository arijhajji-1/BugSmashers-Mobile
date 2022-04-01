/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_backend;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import entities.actualite;
import gui.Home;
import gui.LoginForm;
import static gui_backend.ListReclamation.theme;

import services.ServiceActualite;


import static gui_backend.ListeActbackForm.actualiteActuelle;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
/**
 *
 * @author Arij Hajji
 */
public class ModifierActualiteForm extends Form{

    Label labelTitre,labelDate, labelDescription;
    TextField tfTitre;
    Picker Date = new Picker();
    TextArea tfDescription;
    Button btnModifier;
    Form previous;

    public ModifierActualiteForm(Form previous) {
        super(new BoxLayout(BoxLayout.Y_AXIS));

        this.previous = previous;

        addGUIs();
        addActions();
        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

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
        labelTitre = new Label("Titre ");
        labelTitre.setUIID("Titre");
        if(actualiteActuelle!=null){
        tfTitre = new TextField(actualiteActuelle.getTitre());


        labelDate = new Label("Date : ");
        labelDate.setUIID("defaultLabel");

        labelDescription = new Label("Description : ");
        labelDescription.setUIID("Description");
        tfDescription = new TextArea(actualiteActuelle.getDescription());}
        else{
            tfTitre = new TextField();
            tfDescription = new TextArea();
            labelDescription = new Label("Description : ");
            labelDescription.setUIID("Description");
            labelDate = new Label("Date : ");
            labelDate.setUIID("defaultLabel");

        }




        Date.setType(Display.PICKER_TYPE_DATE_AND_TIME);

        btnModifier = new Button("Modifier");

        btnModifier.setUIID("Button");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        container.setUIID("actualiteContainer");
        container.addAll(labelTitre,tfTitre,labelDate,Date,labelDescription, tfDescription, btnModifier);

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
                actualite actualite = new actualite(
                        actualiteActuelle.getId(),
                        (String) tfTitre.getText(),
                        tfTitre.getText(),
                        tfDescription.getText(),
                        Date.getValue().toString());

                int responseCode = ServiceActualite.getInstance().modifierActualite(actualite);
                System.out.println("22");
                if (responseCode == 200) {
                    Dialog.show("Succés", "actualite modifieré avec succes", new Command("Ok"));
                } else {
                    Dialog.show("Erreur", "Erreur d'modifier de l'actualite. Code d'erreur : " + responseCode, new Command("Ok"));
                }

                ((ListeActbackForm) previous).refresh();
                previous.showBack();
            }
        });

    }
}
