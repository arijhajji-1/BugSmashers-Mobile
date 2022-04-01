/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.plaf.UIManager;
import com.codename1.components.InfiniteScrollAdapter;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import java.util.HashMap;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Form;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.ComboBox;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Component;
import com.codename1.components.SpanMultiButton;
import com.mycompany.myapp.services.ServiceCategory;
import com.mycompany.myapp.entities.Category;
import java.util.Map;
import java.util.ArrayList;
import com.mycompany.myapp.services.ServiceProduit;
import com.codename1.ui.list.GenericListCellRenderer;
import com.mycompany.myapp.entities.ProduitAcheter;


public class ModifyProduitAcheter extends Form {

    private String url="http://localhost:8080/assets/";
    private EncodedImage enc;
    public ModifyProduitAcheter(Form previous, ProduitAcheter produitA) {
        setTitle("Ajouter un produit acheter");
        setLayout(BoxLayout.y());
        ArrayList<Category> categories=ServiceCategory.getInstance().getAllCategories();
        TextField tfNom= new TextField(produitA.getNom(), "nom");
        TextField tfDescription= new TextField(produitA.getDescription(), "description");
        TextField tfQte= new TextField(Integer.toString(produitA.getQte()),"qte");
        TextField tfMarque = new TextField(produitA.getMarque(), "marque");
        TextField tfPrix= new TextField(Float.toString(produitA.getPrix()), "prix");
        ComboBox<Map<String, Object>> combo = new ComboBox<> ();
          for (Category cat : categories){
          combo.addItem(createListEntry(cat.getLabel(), cat.getId()));
}
          

          combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
          combo.setSelectedIndex((int)Float.parseFloat(produitA.getCategory())-1);
        
        Button btnValider = new Button("Ajouter produit");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                    try {
                        ProduitAcheter t = new ProduitAcheter(produitA.getId(), tfNom.getText(), combo.getSelectedItem().get("Line2").toString(),
                            tfDescription.getText(), Integer.parseInt(tfQte.getText()), tfMarque.getText(),
                            Float.parseFloat(tfPrix.getText()), "imagepathTOBEFXIED");
                        if( ServiceProduit.getInstance().modifyProduitA(t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
        });
        
        addAll(tfNom,tfPrix,combo,tfMarque,tfQte,tfDescription,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
     private Map<String, Object> createListEntry(String name, int date) {
    Map<String, Object> entry = new HashMap<>();
    entry.put("Line1", name);
    entry.put("Line2", date);
    return entry;
}   

}


