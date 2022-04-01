/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.ui.Form;
import entities.Reclamation;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
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
import entities.Commande;
import services.serviceReclamation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import services.ServiceCommande;
/**
 *
 * @author Arij Hajji
 */
public class AjoutReclamation extends Form{
       
    private Resources theme;
     private Database db;
  private TextField idc,suj;
    private TextArea desc;
    private Picker dateR;
    private Label lbid,lbc,lbd,lbs,lbdes,recl,livr;
    private Container cn1,cn2,cn3,ctn1,ctn2,crud;
    private ComboBox cb1,cb4;
    private Button btn1,btn2,btn3;
    
     public AjoutReclamation(Form previous) {
        setTitle("Soumettre une reclamation");
        setLayout(BoxLayout.y());
        
                
        //Form f1 = new Form("ajouter une réclamation", BoxLayout.y());
      // idc= new TextField(null,"id de la commande",4,TextField.NUMERIC);
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
      cb4= new ComboBox();
                 for (Commande cat : ServiceCommande.getInstance(). getAllCommandes()) {
            cb4.addItem(cat.getId());
        }
       cb1= new ComboBox();
       cb1.addItem("location");
       cb1.addItem("montage");
       cb1.addItem("reparation");
        cn3.add(lbc).add(cb1);
        
        crud= new Container(BoxLayout.x());
       btn3= new Button("envoyer"); 
    
        crud.addAll(btn3);
      // f1.addAll(lbid,idc,cn3,lbs,suj,cn1,cn2,crud);
        
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((suj.getText().length()==0)||(desc.getText().length()==0))
                    Dialog.show("Alert", "veuillez remplir tous les champs", new Command("OK"));
                else
                {
                    try {
                        Reclamation r = new Reclamation(Integer.parseInt(cb4.getSelectedItem().toString()), cb1.getSelectedItem().toString(),suj.getText(),desc.getText());
                        if( serviceReclamation.getInstance().ajoutReclamation(r))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                            ToastBar.getInstance().setPosition(BOTTOM);
                ToastBar.Status status = ToastBar.getInstance().createStatus();
                status.setShowProgressIndicator(true);
                //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                status.setMessage("reclamation ajouté avec succes");
                status.setExpires(10000);
                status.show();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(lbid,cb4,cn3,lbs,suj,cn1,cn2,crud);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
                
     }
             
}
