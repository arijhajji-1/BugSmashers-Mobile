/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.DateSpinner;
import com.codename1.ui.spinner.DateTimeSpinner;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.MyApplication;
import entities.ProduitAcheter;
import entities.reparation;
import services.ServiceReparation;
import gui.Home;
import java.util.Date;
import services.ServiceProduit;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entities.Category;
import services.ServiceCategory;
/**
 *
 * @author Arij Hajji
 */
public class AddReparation extends Form {
    
       Label labelType, labelDescription,labelCategory,labeldate;
    TextArea tfType;
    TextArea tfDescription;
    ComboBox tfcategory; 
    Button btnAjouter,btnAfficher;
 Picker date;
    Form previous;
    public AddReparation(Form previous) {
         super(new BoxLayout(BoxLayout.Y_AXIS));
        
        this.previous = previous;
        
        addGUIs();
        addActions();
      
        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> Home.accueilFrontForm.showBack());
    }

    private void addGUIs() {

       labelCategory = new Label("category de la reparation");
        labelCategory.setUIID("defaultLabel");
         tfcategory = new ComboBox<>();
         
            tfcategory = new ComboBox<>();
        for (Category cat : ServiceCategory.getInstance(). getAllCategories()) {
            tfcategory.addItem(cat.getLabel());
        }
        

  labelType = new Label("Type de la reparation", "WelcomeBlack");
        labelType.setUIID("defaultLabel");
        tfType = new TextArea();
        labelDescription = new Label("Description : ");
        labelDescription.setUIID("defaultLabel");
        tfDescription = new TextArea();
  labeldate = new Label("Date : ");
        labeldate.setUIID("defaultLabel");
         
 
 date = new Picker();
date.setType(Display.PICKER_TYPE_DATE_AND_TIME);
     date.setDate(new Date());


        btnAjouter = new Button("Ajouter");

        btnAjouter.setUIID("Button");
 btnAfficher = new Button("Afficher");

        btnAfficher.setUIID("Button");
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        container.setUIID("ReparationContainer");
        container.addAll(labelCategory, tfcategory, labelType,tfType, labelDescription, tfDescription,labeldate,date, btnAjouter,btnAfficher);

        this.addAll(container);
    }

    private boolean controleDeSaisie() {
        if (tfType.getText().equals("")) {
            Dialog.show("Objet vide", "", new Command("Ok"));
            return false;
        }
        if (tfDescription.getText().equals("")) {
            Dialog.show("Description vide", "", new Command("Ok"));
            return false;
        }

        return true;
    }

    private void addActions() {
btnAfficher.addActionListener((action) -> {
      new ShowReparation(this).show();
} );
        btnAjouter.addActionListener((ActionEvent action) -> {
          
            if (controleDeSaisie()) {
                System.out.println("11");
               

 System.out.println(date.getValue());
                int responseCode;
                responseCode = ServiceReparation.getInstance().AddReparation(new reparation((String) tfcategory.getSelectedItem(),
                        tfType.getText(),
                        tfDescription.getText(),
                        date.getValue().toString()));
                System.out.println("22");
                String accountSID = "AC203d7e5c4ae96078900c9898bb147dd7";
                String authToken = "a8b4d7df3e029ad2558d5e38afcde9a9";
                String fromPhone = "+19036648097";
                Twilio.init(accountSID, authToken);

                Message message = Message
                        .creator(new PhoneNumber("+21690197079"), // to
                                new PhoneNumber(fromPhone), // from
                                "Votre demande de reparation est en cours de traitement ")
                        .create();

                System.out.println(message.getSid());
                  ToastBar.getInstance().setPosition(BOTTOM);
                ToastBar.Status status = ToastBar.getInstance().createStatus();
                status.setShowProgressIndicator(true);
                //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                status.setMessage("reparation ajouté avec succes");
                status.setExpires(10000);
                status.show();
                if (responseCode == 200) {
                    Dialog.show("Succés", "Reparation ajouté avec succes", new Command("Ok"));
                } else {
                    Dialog.show("Erreur", "Erreur d'ajout de Reparation. Code d'erreur : " + responseCode, new Command("Ok"));
                }
                
                
            }
        });

    }
  
   
}
