/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_backend;

import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.reparation;
import gui.AddAvisReparation;
import gui.AddReparation;
import gui.Home;
import gui.LoginForm;
import gui.ModifierReparation;
import static gui_backend.ListReclamation.theme;
import java.util.ArrayList;
import services.ServiceReparation;

/**
 *
 * @author Arij Hajji
 */
public class AfficheReparation extends Form {
         Form current;
    public void refresh(Form previous) {
         this.removeAll();
         //new ListProduitAcheterBack(previous).show();
        this.refreshTheme();
    }
     public AfficheReparation(Form previous) {
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
ArrayList<reparation> reparation=ServiceReparation.getInstance().getAllreparationsP();
    setLayout(new GridLayout(30, 4));
    //Label t= new Label("ID");
    Label t2 =new Label("Category");
    Label t3 =new Label("Type");
    Label t4 =new Label("Date");
    Label t5 =new Label("Description");
      
    //add(t);
    add(t2);
    add(t3);
    add(t4);
    add(t5);

    for(reparation a : reparation) {
                    String proc = (String) a.getCategory();
                    String  cartg= (String) a.getType();
                     String cam = (String)a.getReserver();
                     String boi = (String)a.getDescription();
                 

                    Label n =new Label(proc);
                    Label pr =new Label(cartg);
                    Label i = new Label (cam);
                     Label bb= new Label (boi);
               
                   
                    add(n);
                     add(pr);
                    add(i);
                    add(bb);
                   
                
                }
        



}


    }
