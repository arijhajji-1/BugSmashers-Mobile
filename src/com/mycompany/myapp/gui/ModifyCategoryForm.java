/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
/**
 *
 * @author USER
 */
public class ModifyCategoryForm extends Form {

    public ModifyCategoryForm(Form previous, Category cat) {
        setTitle("Modifier Catégorie");
        setLayout(BoxLayout.y());
        TextField tfLabel= new TextField(cat.getLabel(), "label");
          
        
        Button btnValider = new Button("Modifier Catégorie");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                    try {
                        Category t = new Category(cat.getId(), tfLabel.getText());
                        if( ServiceCategory.getInstance().modifyCategory(t))
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
