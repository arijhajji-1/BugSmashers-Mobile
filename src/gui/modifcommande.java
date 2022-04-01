/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.BASELINE;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import entities.Commande;
import services.ServiceCommande;
/**
 *
 * @author Arij Hajji
 */
public class modifcommande extends Form  {
  static TextField tfId = new TextField();
    Commande current;
public modifcommande(Form previous) {
        setTitle("modifier Commande");
        setLayout(BoxLayout.y());
        
        TextField tfnom = new TextField("","nom");
        TextField tfprenom= new TextField("", "prenom");
        TextField tfpaiment= new TextField("", "paiment");
        TextField tfadress= new TextField("", "adresse");
        TextField tftelephone= new TextField("", "telephone");
        Button btnValider = new Button("Add Commande");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom.getText().length()==0)||(tfprenom.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                            Commande t = new Commande(tfnom.getText().toString(), tfprenom.getText().toString(),tfpaiment.getText().toString(),tfadress.getText().toString(),tftelephone.getAsInt(BASELINE));
                        if( ServiceCommande.getInstance().modifcommande(t,Integer.parseInt(tfId.getText())))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "nom must be a string", new Command("OK"));
                        
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfnom,tfprenom,tfpaiment,tfadress,tftelephone,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}

