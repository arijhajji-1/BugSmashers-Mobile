/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.Display;
import com.codename1.ui.plaf.UIManager;
import com.codename1.components.InfiniteScrollAdapter;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Form;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.components.SpanMultiButton;
import java.util.Map;
import java.util.ArrayList;
import com.mycompany.myapp.services.ServiceProduit;
import com.mycompany.myapp.gui.ModifyProduitAcheter;
import com.mycompany.myapp.gui.SingleProduitA;
import com.mycompany.myapp.entities.ProduitAcheter;
import com.mycompany.myapp.entities.ProduitLouer;


public class ListProduitAcheter extends Form {

    private String url="http://localhost:8080/assets/";
    private EncodedImage enc;
    Form current;
    public ListProduitAcheter(Form previous) {
        current=this; 
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setTitle("List Produit Acheter");
        Style s = UIManager.getInstance().getComponentStyle("MultiLine2");
        s.setMargin(1,1,false);
        FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
        EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 6, p.getHeight() *3), false); 
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        ArrayList<ProduitAcheter> ProduitsAcheter=ServiceProduit.getInstance().getAllProduitsAcheter();
        ArrayList<ProduitLouer> ProduitsLouer=ServiceProduit.getInstance().getAllProduitsLouer();
        int i=0;
        MultiButton mbtnA = new MultiButton("Produit à Acheter");
        add(mbtnA);
        for(ProduitAcheter produitA : ProduitsAcheter) {
                    System.out.println(produitA.getId());
                    String nom = (String) produitA.getNom();
                    String prix = Float.toString(produitA.getPrix());
                    String imageName = (String) produitA.getImagePath();
                    MultiButton mb = new MultiButton(nom);
                    Image resizedImage = URLImage.createToStorage(placeholder, imageName, url+imageName);
                    mb.setIcon(resizedImage);
                    mb.setTextLine2(prix+" TD");
                    mb.addActionListener(e-> new SingleProduitA(current, produitA).show());
                    add(mb);
                }

        MultiButton mbtnL = new MultiButton("Produit à Louer");
        add(mbtnL);
        for(ProduitLouer produitL : ProduitsLouer) {    
                    System.out.println(produitL.getId());
                    String nom = (String) produitL.getNom();
                    String prix = Float.toString(produitL.getPrix());
                    String imageName = (String) produitL.getImagePath();
                    MultiButton mb = new MultiButton(nom);
                    Image resizedImage = URLImage.createToStorage(placeholder, imageName, url+imageName);
                    mb.setIcon(resizedImage);
                    mb.setTextLine2(prix+" TD/Jour");
                    add(mb);
                }



}

}
