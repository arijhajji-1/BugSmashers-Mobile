/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.modiflocation.tfId;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import entities.Location;
import entities.ProduitLouer;
import services.ServiceLocation;
import services.ServiceProduit;
/**
 *
 * @author Arij Hajji
 */
public class modiflocation extends Form {
Label labelProduit;
ComboBox tfProduit;
Form previous;
static TextField tfId = new TextField();
    Location current;
public modiflocation(Form previous) {
       super(new BoxLayout(BoxLayout.Y_AXIS));
        
        this.previous = previous;
        
        addGUIs();
   
      
        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }

       

        TextField tftotall= new TextField("", "Totall");
  private void addGUIs() {
 setTitle("Add new Location");
        setLayout(BoxLayout.y());
         Picker tfdb = new Picker();
        tfdb .setType(Display.PICKER_TYPE_DATE_AND_TIME);
       tfdb .setUIID("TextFieldBlack");
 
        Picker tfdf = new Picker();
        tfdf.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        tfdf.setUIID("TextFieldBlack");


       labelProduit = new Label("Processeur :");
        labelProduit.setUIID("defaultLabel");
        
        tfProduit = new ComboBox<>();
         for (ProduitLouer produit : ServiceProduit.getInstance(). getAllProduitsLouer()) {
            tfProduit.addItem(produit.getNom());
        }
        
        Button btnValider = new Button("Add Location");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tftotall==null)
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                            Location t = new Location(Integer.parseInt(tftotall.getText()),tfdb.getText(),tfdf.getText());
                        if( ServiceLocation.getInstance().modiflocation(t,Integer.parseInt(tfId.getText())))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "totall must be an int", new Command("OK"));
                        
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfdb,tfdf,tftotall,tfProduit,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
    


