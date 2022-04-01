/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import com.mycompany.myapp.services.ServiceProduit;
import com.mycompany.myapp.gui.ModifyProduitAcheter;
import com.mycompany.myapp.entities.ProduitAcheter;
import com.mycompany.myapp.entities.ProduitLouer;
import com.codename1.components.ImageViewer;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;

/**
 *
 * @author USER
 */
public class SingleProduitA extends Form {
    private String url="http://localhost:8080/assets/";
    private EncodedImage enc;
    private String imageName;
    Form current;
     public void refresh(Form previous,ProduitAcheter produitA) {
         this.removeAll();
         new SingleProduitA(previous,produitA).show();
        this.refreshTheme();
    }
    public SingleProduitA(Form previous,ProduitAcheter produitA) {
    current=this; 
    GridLayout tl = new GridLayout(2, 1);
    setLayout(tl);
    imageName = produitA.getImagePath();
    setTitle("Produit :");
    Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
    EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 6, p.getHeight() *3), false); 
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    Image img = URLImage.createToStorage(placeholder, imageName, url+imageName);
    ImageViewer iv = new ImageViewer(img);
    MultiButton title = new MultiButton("Nom du produit :");
    title.setTextLine2(produitA.getNom());
    MultiButton price = new MultiButton("Prix : ");
    price.setTextLine2(Float.toString(produitA.getPrix())+" TD");
    MultiButton location = new MultiButton("Marque :");
    location.setTextLine2(produitA.getMarque());
    MultiButton description = new MultiButton("Description : ");
    description.setTextLine2(produitA.getDescription());
    add(title);
    add(iv);
    add(price);
    add(location);
    add(description);
    Map<String, Object> Avis=ServiceProduit.getInstance().getAllProduitsAvis(produitA.getId()); 
    MultiButton rt = new MultiButton("Ratings :");
    rt.setTextLine2(Integer.toString(Avis.size())+" ratings");
    add(rt);
    for (Map.Entry<String,Object> entry : Avis.entrySet()){
            Label l1= new Label(entry.getKey());
            add(l1);
            Slider sli = createStarRankSlider(false,(int)entry.getValue());
            add(FlowLayout.encloseCenter(sli));}
    MultiButton ra = new MultiButton("Rate IT ?");
    add(ra);
    Slider sl = createStarRankSlider(true);
    Button slvl = new Button("Ajoutez avis!");
    add(FlowLayout.encloseCenter(sl));
    TextArea desc = new TextField("", "Votre avis", 200, TextArea.ANY);
    slvl.addActionListener(e-> {ServiceProduit.getInstance().addProduitAvis(desc.getText().toString(), sl.getProgress(), produitA.getId());this.refresh(previous,produitA);});
    add(desc);
    add(slvl);
    
}
private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}

private Slider createStarRankSlider(boolean editable) {
    Slider starRank = new Slider();
    starRank.setEditable(editable);
    starRank.setMinValue(0);
    starRank.setMaxValue(5);
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
    return starRank;
}
private Slider createStarRankSlider(boolean editable, int value) {
    Slider starRank = new Slider();
    starRank.setEditable(editable);
    starRank.setMinValue(0);
    starRank.setMaxValue(5);
    starRank.setProgress(value);
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
    return starRank;
}
}
