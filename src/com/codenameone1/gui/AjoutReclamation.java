/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codenameone1.gui;

import com.codename1.ui.Form;
import com.codenaeone1.entities.Reclamation;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.db.Database;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.spinner.Picker;
import com.codenameone1.services.serviceReclamation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Gloria
 */
public class AjoutReclamation extends Form{
       
    private Resources theme;
     private Database db;
  private TextField idc,suj;
    private TextArea desc;
    private Picker dateR;
    private Label lbid,lbc,lbd,lbs,lbdes,recl,livr;
    private Container cn1,cn2,cn3,ctn1,ctn2,crud;
    private ComboBox cb1;
    private Button btn1,btn2,btn3,aff,mod,supp;
    
     public AjoutReclamation(Form previous) {
        setTitle("Soumettre une reclamation");
        setLayout(BoxLayout.y());
        
                 //Form f1 = new Form("ajouter une réclamation", BoxLayout.y());
       idc= new TextField(null,"id de la commande",4,TextField.NUMERIC);
       suj= new TextField(null,"sujet");
       desc= new TextField(null,"description",500,TextArea.ANY);
       dateR= new Picker();
       lbc= new Label("catégorie: ");
       lbdes= new Label("description: ");
       lbd= new Label("date: ");
       lbid= new Label("id commande: ");
       lbs= new Label("sujet: ");
       cn1= new Container();
        cn2= new Container();
         cn3= new Container();
       cn1.add(lbdes).add(desc);
       cn2.add(lbd).add(dateR);
      
       cb1= new ComboBox();
       cb1.addItem("location");
       cb1.addItem("montage");
       cb1.addItem("reparation");
        cn3.add(lbc).add(cb1);
        crud= new Container(BoxLayout.x());
       btn3= new Button("envoyer"); 
        aff= new Button("afficher");
        mod= new Button ("modifier");
        supp= new Button("supprimer");
        crud.addAll(btn3,aff,mod,supp);
      // f1.addAll(lbid,idc,cn3,lbs,suj,cn1,cn2,crud);
        
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((suj.getText().length()==0)||(desc.getText().length()==0)||(idc.getText().length()==0))
                    Dialog.show("Alert", "veuillez remplir tous les champs", new Command("OK"));
                else
                {
                    try {
                        Reclamation r = new Reclamation(Integer.parseInt(idc.getText()), cb1.getSelectedItem().toString(),suj.getText(),desc.getText());
                        if( serviceReclamation.getInstance().ajoutReclamation(r))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(lbid,idc,cn3,lbs,suj,cn1,cn2,crud);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
                
     }
             
}
