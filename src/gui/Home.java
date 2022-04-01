/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
/**
 *
 * @author Arij Hajji
 */
public class Home extends Form{
         Form current;
   public static Form accueilFrontForm;
   Resources theme = UIManager.initFirstTheme("/theme");
    Label label;
    
    public Home() {
        super(new BorderLayout());
        addGUIs();
accueilFrontForm=this;
        super.getToolbar().hideToolbar();
    }

    private void addGUIs() {
     FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);

        this.getToolbar().addCommandToRightBar(null,icon,evt1 -> new LoginForm(theme).show());
        Tabs tabs = new Tabs();
        tabs.addTab("Accueil", FontImage.MATERIAL_HOME, 5, new ListeActForm(this));
                tabs.addTab("Events", FontImage.MATERIAL_EVENT, 5, new ListeEventForm(this));

             tabs.addTab("Reparation", FontImage.MATERIAL_WORK, 5, new AddReparation(this));
    tabs.addTab("Montage", FontImage.MATERIAL_COMPUTER, 5, new AddMontage(this));
tabs.addTab("Reclamation", FontImage.MATERIAL_COMMENT, 5, new AjoutReclamation(this));
tabs.addTab("Produit", FontImage.MATERIAL_WORK , 5, new ListProduitAcheter(this));
tabs.addTab("Commande", FontImage.MATERIAL_WORK , 5, new AddCommandeForm(this));
tabs.addTab("Location", FontImage.MATERIAL_WORK , 5, new AddLocationForm(this));

        this.add(BorderLayout.CENTER, tabs);
    }

    

   

}