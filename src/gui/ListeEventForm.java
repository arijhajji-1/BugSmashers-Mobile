/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import entities.evenement;
import services.ServiceEvent;
import java.util.ArrayList;
import com.codename1.ui.Display;
import com.codename1.components.InfiniteScrollAdapter;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.Slider;
import com.codename1.ui.Font;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Form;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.components.SpanMultiButton;
import java.util.Map;
import com.codename1.ui.TextComponent;

/**
 *
 * @author Arij Hajji
 */
public class ListeEventForm extends Form {


    public static evenement evenementActuelle = null;
    private String url="http://localhost:8080/assets/";
    private EncodedImage enc;
    private String imageName;
    Form previous;


    public ListeEventForm(Form previous) {
        this.previous=previous;
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

        addGUIs();
    }

    private void addGUIs() {


        ArrayList<evenement> evenements = ServiceEvent.getInstance().getAllEvenement();
        for (int i = 0; i < evenements.size(); i++) {
            this.add(evenement(evenements.get(i)));
        }
    }

    private Component evenement(evenement evenement){
        Container evenementModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        evenementModel.setUIID("evenementContainer");
        GridLayout tl = new GridLayout(3, 1);
        setLayout(tl);

        imageName = evenement.getImageName();
        System.out.println(url+imageName);
        setTitle("evenement :");
        Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
        FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
        EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 10, p.getHeight() *8), false);
        Image img = URLImage.createToStorage(placeholder, imageName, url+imageName);
        ImageViewer iv = new ImageViewer(img);
        Label labelTitre = new Label("Nom: "+(String) evenement.getNom());
        labelTitre.setUIID("titre");
        Label labelDate = new Label(" Date : " +evenement.getDate());
        labelDate.setUIID("dateLabel");
        Label labelDescription = new Label(" Description : " +evenement.getDescription());
        labelDescription.setUIID("dateLabel");

        evenementModel.addAll(labelTitre,labelDate,labelDescription,iv);

        return evenementModel;
    }



}

