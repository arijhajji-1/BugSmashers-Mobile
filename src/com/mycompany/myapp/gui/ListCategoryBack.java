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
import com.codename1.ui.Form;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.components.SpanMultiButton;
import java.util.Map;
import java.util.ArrayList;
import com.mycompany.myapp.services.ServiceCategory;
import com.mycompany.myapp.gui.ModifyProduitAcheter;
import com.mycompany.myapp.gui.SingleProduitA;
import com.mycompany.myapp.entities.ProduitAcheter;
import com.mycompany.myapp.entities.Category;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.spinner.Picker;
/**
 *
 * @author USER
 */
public class ListCategoryBack extends Form{
 Form current;
    public void refresh(Form previous) {
         this.removeAll();
         new ListCategoryBack(previous).show();
        this.refreshTheme();
    }
    public ListCategoryBack(Form previous) {
     current=this;
     getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
    ArrayList<Category> categories=ServiceCategory.getInstance().getAllCategories();
    setLayout(new GridLayout(10, 4));
    Label t= new Label("ID");
    Label t2 =new Label("Label");
    Label t4 =new Label("Modify");
    Label t5 =new Label("Delete");
    add(t);
    add(t2);
    add(t4);
    add(t5);
    for(Category cat : categories) {
                    String nom = (String) cat.getLabel();
                    Label id= new Label(Integer.toString(cat.getId()));
                    Label n =new Label(nom);
                    Button md =new Button("Modify");
                    Button dl =new Button("Delete");
                    md.addActionListener(e-> new ModifyCategoryForm(current, cat).show());
                    dl.addActionListener(e-> {ServiceCategory.getInstance().deleteCategory(cat.getId());
                                            this.refresh(previous);});
                    add(id);
                    add(n);
                    add(md);
                    add(dl);
                }
        



}

}
