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
public class AddCategoryForm extends Form {
public AddCategoryForm(Form previous) {
        setTitle("Ajouter Catégorie");
        setLayout(BoxLayout.y());
        TextField tfLabel= new TextField("", "label");
        Button btnValider = new Button("Ajouter Catégorie");
  
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                    try {
                        Category t = new Category(0,tfLabel.getText());
                        if( ServiceCategory.getInstance().addCategory(t))
                        {  
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                           Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            

           
        });
        
        addAll(tfLabel,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
