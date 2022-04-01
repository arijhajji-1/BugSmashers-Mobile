/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.Display;
import com.codename1.components.SpanLabel;
import com.codename1.ui.plaf.UIManager;
import com.codename1.components.InfiniteScrollAdapter;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.Form;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.components.SpanMultiButton;
import java.util.Map;
import java.util.ArrayList;
import com.codename1.ui.TextField;
import com.mycompany.myapp.services.ServiceProduit;
import com.mycompany.myapp.gui.ModifyProduitAcheter;
import com.mycompany.myapp.gui.SingleProduitA;
import com.mycompany.myapp.entities.ProduitAcheter;
import com.mycompany.myapp.entities.ProduitLouer;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.spinner.Picker;
import java.util.ArrayList;
public class ListProduitAcheterBack extends Form {
    

    private String url="http://localhost:8080/assets/";
    private EncodedImage enc;
    public static 
                  TextField searchbar;
    Form current;
    public void refresh(Form previous) {
         this.removeAll();
         new ListProduitAcheterBack(previous).show();
        this.refreshTheme();
    }
    public void refresh(Form previous, String text) {
         this.removeAll();
         ArrayList<ProduitAcheter> prod = ServiceProduit.getInstance().recherche(text);
         new ListProduitAcheterBack(previous,prod,text).show();
        this.refreshTheme();
    }
    public ListProduitAcheterBack(Form previous) {
     current=this;
     getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        searchbar = new TextField("","Search");
        add(searchbar);
         searchbar.addDataChangedListener(new DataChangedListener() {
            @Override
            public void dataChanged(int type, int index) {
                refresh(previous,searchbar.getText());
           // ServiceReparation.getInstance().recherche(searchbar.getText());
}});
    ArrayList<ProduitAcheter> ProduitsAcheter=ServiceProduit.getInstance().getAllProduitsAcheter();
    setLayout(new GridLayout(10, 5));
    //Label t= new Label("ID");
    Label t2 =new Label("Label");
    Label t3 =new Label("Prix");
    Label t4 =new Label("Modify");
    Label t5 =new Label("Delete");
    //add(t);
    add(t2);
    add(t3);
    add(t4);
    add(t5);
    for(ProduitAcheter produitA : ProduitsAcheter) {
                    String nom = (String) produitA.getNom();
                    String prix = Float.toString(produitA.getPrix());
                    String imageName = (String) produitA.getImagePath();
                    Label id= new Label(Integer.toString(Math.round(Float.parseFloat(produitA.getId()))));
                    Label n =new Label(nom);
                    Label pr =new Label(prix);
                    Button md =new Button("Modify");
                    Button dl =new Button("Delete");
                    md.addActionListener(e-> new ModifyProduitAcheter(current, produitA).show());
                    dl.addActionListener(e-> {ServiceProduit.getInstance().deleteProduitA(produitA.getId());
                                              this.refresh(previous);});
                    add(id);
                    add(n);
                    add(pr);
                    add(md);
                    add(dl);
                }
        



}

public ListProduitAcheterBack(Form previous, ArrayList<ProduitAcheter> prod, String text) {
     current=this;
     getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        searchbar = new TextField(text,"Search");
        add(searchbar);
         searchbar.addDataChangedListener(new DataChangedListener() {
            @Override
            public void dataChanged(int type, int index) {
                refresh(previous,searchbar.getText());
           // ServiceReparation.getInstance().recherche(searchbar.getText());
}});
setLayout(new GridLayout(10, 5));
    //Label t= new Label("ID");
    Label t2 =new Label("Label");
    Label t3 =new Label("Prix");
    Label t4 =new Label("Modify");
    Label t5 =new Label("Delete");
    //add(t);
    add(t2);
    add(t3);
    add(t4);
    add(t5);
    for(ProduitAcheter produitA : prod) {
                    String nom = (String) produitA.getNom();
                    String prix = Float.toString(produitA.getPrix());
                    String imageName = (String) produitA.getImagePath();
                    Label id= new Label(Integer.toString(Math.round(Float.parseFloat(produitA.getId()))));
                    Label n =new Label(nom);
                    Label pr =new Label(prix);
                    Button md =new Button("Modify");
                    Button dl =new Button("Delete");
                    md.addActionListener(e-> new ModifyProduitAcheter(current, produitA).show());
                    dl.addActionListener(e-> {ServiceProduit.getInstance().deleteProduitA(produitA.getId());
                                              this.refresh(previous);});
                    add(id);
                    add(n);
                    add(pr);
                    add(md);
                    add(dl);
                }
        



}

}

