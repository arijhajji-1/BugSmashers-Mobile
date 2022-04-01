/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_backend;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import entities.AvisReparation;
import gui.Home;
import gui.LoginForm;
import static gui_backend.ListReclamation.theme;
import java.util.ArrayList;
import services.ServiceAvisReparation;

/**
 *
 * @author Arij Hajji
 */
public class AfficheAvisReparation extends Form {
    
    Form current;
    public void refresh(Form previous) {
         this.removeAll();
         //new ListProduitAcheterBack(previous).show();
        this.refreshTheme();
    }
   
    public AfficheAvisReparation(Form previous) {
     current=this;
     getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
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
ArrayList<AvisReparation> AvisReparation=ServiceAvisReparation.getInstance().getAllAvisreparationsP();
    setLayout(new GridLayout(10, 4));
    //Label t= new Label("ID");
    Label t2 =new Label("Nom");
    Label t3 =new Label("Description");
    Label t4 =new Label("Email");
    Label t5 =new Label("IdReparation");
    //add(t);
    add(t2);
    add(t3);
    add(t4);
    add(t5);
    for(AvisReparation a : AvisReparation) {
                    String nom = (String) a.getDescription();
                    String prix = (String)a.getEmail();
                     String name = (String)a.getNom();
                   String id = Integer.toString( a.getIdreparation());
                    Label n =new Label(name);
                    Label pr =new Label(prix);
                    Label i = new Label (id);
                  Label na= new Label (name);
                    add(i);
                    add(n);
                    add(pr);
                 add(na);
                }
        



}



}

