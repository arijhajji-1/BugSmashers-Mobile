/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import java.util.HashMap;
import java.util.Map;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.ComboBox;
import com.codename1.components.MultiButton;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.ProduitLouer;
import com.mycompany.myapp.entities.Category;
import com.mycompany.myapp.services.ServiceProduit;
import com.mycompany.myapp.services.ServiceCategory;
import com.codename1.ui.list.GenericListCellRenderer;
import java.util.ArrayList;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import java.io.IOException;
import java.util.Random;
import com.mycompany.myapp.utils.Statics;
import com.codename1.ui.Display;

/**
 *
 * @author USER
 */
public class AddProduitLouerForm extends Form{

    public static String generateRandomPassword() {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
          +"lmnopqrstuvwxyz_-";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(20);
		for (int i = 0; i < 20; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}

 AddProduitLouerForm(Form previous) {
        setTitle("Ajouter un produit acheter");
        setLayout(BoxLayout.y());
        ArrayList<Category> categories=ServiceCategory.getInstance().getAllCategories();
        TextField tfNom= new TextField("", "nom");
        TextField tfDescription= new TextField("", "description");
        TextField tfEtat= new TextField("","etat");
        TextField tfDispo= new TextField("","dispo");
        TextField tfMarque = new TextField("", "marque");
        TextField tfPrix= new TextField("", "prix");
    ComboBox<Map<String, Object>> combo = new ComboBox<> ();
          for (Category cat : categories)
          combo.addItem(createListEntry(cat.getLabel(), cat.getId()));
          combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
Button btnUpload = new Button("Upload Image");
          MultipartRequest cr = new MultipartRequest();
          String filename = "i"+generateRandomPassword() + ".png";
          btnUpload.addActionListener((evt) -> {
                String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
                
                cr.setUrl(Statics.URL_UPLOAD);
                cr.setPost(true);
                String mime = "image/png";
                try {
                    cr.addData("file", filePath, mime);
                } catch (IOException ex) {
                    Dialog.show("Error", ex.getMessage(), "OK", null);
                }
                
                cr.setFilename("file", filename);//any unique name you want

                //InfiniteProgress prog = new InfiniteProgress();
                //Dialog dlg = prog.showInifiniteBlocking();
                //cr.setDisposeOnCompletion(dlg);
        });
        Button btnValider = new Button("Ajouter produit");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                    try {
                        ProduitLouer t = new ProduitLouer(tfNom.getText(),
 tfDescription.getText(), tfEtat.getText(), Boolean.parseBoolean(tfDispo.getText()), tfMarque.getText(),
 Float.parseFloat(tfPrix.getText()), combo.getSelectedItem().get("Line2").toString(), filename);
                        if( ServiceProduit.getInstance().addProduitL(t))
                        {
                           NetworkManager.getInstance().addToQueueAndWait(cr);
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            

           
        });
        
        addAll(tfNom,tfPrix,combo,tfMarque,tfEtat, tfDispo,tfDescription,btnUpload,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }

 private Map<String, Object> createListEntry(String name, int date) {
    Map<String, Object> entry = new HashMap<>();
    entry.put("Line1", name);
    entry.put("Line2", date);
    return entry;
}
}
