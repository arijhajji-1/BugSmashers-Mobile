/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import static gui.ListLocationForm.locationActuelle;
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
import entities.Location;
import services.ServiceLocation;
/**
 *
 * @author Arij Hajji
 */
public class ListLocationForm extends Form {
public static Location locationActuelle = null;
public ListLocationForm(Form previous) {
        super("", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
        // getToolbar().hideToolbar();
                super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> Home.accueilFrontForm.showBack());

    }
public void refresh() {
        this.removeAll();
        this.refreshTheme();
    }
private void addGUIs() {



        ArrayList<Location> Locations = ServiceLocation.getInstance().getAllLocations();
        for (int i = 0; i < Locations.size(); i++) {
            this.add(creerLocation(Locations.get(i)));
        }



    }

    private Component creerLocation(Location location) {
        Container LocationModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        LocationModel.setUIID("LocationContainer");
        Label labelDb = new Label(location.getDb());
        labelDb.setUIID("dateDEBLabel");
        Label labelDf = new Label(location.getDf());
        labelDf.setUIID("dateFinLabel");
        Label labelTotall = new Label(String.valueOf(location.getTotall()));
        labelTotall.setUIID("TotalLLabel");
        


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
                ServiceLocation.getInstance().supprimerLocation(location);
                locationActuelle = null;
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
locationActuelle = location;
                
            new modiflocation(this).show();
            
            modiflocation.tfId.setText(String.valueOf(location.getId())); 
this.removeAll();
                addGUIs();
                this.refreshTheme();
        new modiflocation(this).show();
            
        });
        

      btnsContainer.addAll(btnModifier,btnSupprimer);
      LocationModel.addAll(labelDb, labelDf,labelTotall,btnsContainer);     
        return LocationModel;


    }

}

