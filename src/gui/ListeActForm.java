/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.Display;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.components.InfiniteScrollAdapter;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.GridLayout;
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
import java.util.ArrayList;
import services.ServiceActualite;
import entities.actualite;
import com.codename1.components.ImageViewer;
import com.codename1.ui.TextComponent;
import com.codename1.ui.util.Resources;
/**
 *
 * @author Arij Hajji
 */
public class ListeActForm extends Form {
    public static actualite actualiteActuelle = null;
    private String url="http://localhost:8080/assets/";
    private EncodedImage enc;
    private String imageName;
    Form previous;
                       public Resources theme;

    public ListeActForm(Form previous) {
        this.previous=previous;
        
     FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);

        this.getToolbar().addCommandToRightBar(null,icon,evt1 -> new LoginForm(theme).show());

        addGUIs();
    }
        private void addGUIs() {


            ArrayList<actualite> actualites = ServiceActualite.getInstance().getAllActualite();
            for (int i = 0; i < actualites.size(); i++) {
                this.add(actualite(actualites.get(i)));
            }


        }
        private Component actualite(actualite actualite){
            Container actualiteModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            actualiteModel.setUIID("actualiteContainer");
        GridLayout tl = new GridLayout(3, 1);
        setLayout(tl);

        imageName = actualite.getImageName();
        System.out.println(url+imageName);
        setTitle("actualite :");
        Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
        FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
        EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 10, p.getHeight() *8), false);
        Image img = URLImage.createToStorage(placeholder, imageName, url+imageName);
        ImageViewer iv = new ImageViewer(img);
            Label labelTitre = new Label("Titre: "+(String) actualite.getTitre());
            labelTitre.setUIID("titre");
            Label labelDate = new Label(" Date : " +actualite.getDate());
            labelDate.setUIID("dateLabel");
            Label labelDescription = new Label(" Description : " +actualite.getDescription());
            labelDescription.setUIID("dateLabel");

        actualiteModel.addAll(labelTitre,labelDate,labelDescription,iv);

        return actualiteModel;
    }




}

