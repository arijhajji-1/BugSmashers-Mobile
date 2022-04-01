/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.BASELINE;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import entities.Location;
import services.ServiceLocation;
import java.util.Date;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Label;
import com.mycompany.myapp.MyApplication;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import entities.ProduitLouer;
import java.io.IOException;
import java.util.ArrayList;
import services.ServiceProduit;
/**
 *
 * @author Arij Hajji
 */
public class AddLocationForm extends Form{
 Label labelProduit;
ComboBox tfProduit;
Form previous;
    public AddLocationForm(Form previous) {
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


       labelProduit = new Label("Produit:");
        labelProduit.setUIID("defaultLabel");
        
        tfProduit = new ComboBox<>();
         for (ProduitLouer produit : ServiceProduit.getInstance(). getAllProduitsLouer()) {
            tfProduit.addItem(produit.getNom());
        }

        
        Button btnValider = new Button("Add Location");
        Button btnAfficher = new Button("Afficher");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tftotall==null)
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                            Location t = new Location(Integer.parseInt(tftotall.getText()),tfdb.getText(),tfdf.getText());
Email from = new Email("Reloua.tunisie@gmail.com");
                String subject = "demande de location";
                Email to = new Email(String.valueOf(MyApplication.getSession().getEmail()));
                Content content = new Content("text/plain", "Votre demande de location est en cours de traitement");
                Mail mail = new Mail(from, subject, to, content);

                SendGrid sg = new SendGrid("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");
                Request request = new Request();
                try {
                    request.setMethod(Method.POST);
                    request.setEndpoint("mail/send");
                    request.setBody(mail.build());
                    Response response = sg.api(request);
                    System.out.println(response.getStatusCode());
                    System.out.println(response.getBody());
                    System.out.println(response.getHeaders());
                } catch (IOException ex) {
                    System.out.println("message non envoyé");
                }
ToastBar.getInstance().setPosition(BOTTOM);
                ToastBar.Status status = ToastBar.getInstance().createStatus();
                status.setShowProgressIndicator(true);
                //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                status.setMessage("Demande de location ajouté avec succès");
                status.setExpires(10000);
                status.show();

                refreshTheme();

               
LocalNotification n = new LocalNotification();
        n.setId("notif");
        n.setAlertBody("Location ajoutè avec succés");
       n.setAlertTitle("RELOUA TEAM!");
       n.setAlertSound("/notification_sound_party.mp3"); //file name must begin with notification_sound
                        /*Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis()  + 10, // fire date/time
            
                LocalNotification.REPEAT_MINUTE // Whether to repeat and what frequency

        );*/

                  Display.getInstance().scheduleLocalNotification(n, System.currentTimeMillis()  + 10*1000, LocalNotification.REPEAT_MINUTE);
                System.out.println(System.currentTimeMillis());
                      if( ServiceLocation.getInstance().addLocation(t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           previous.showBack();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    }  
                         
                           
                     catch (NumberFormatException e) {
                        Dialog.show("ERROR", "totall must be an int", new Command("OK"));
                        
                    }
                    
                }
                
                
            }
        });
         btnAfficher.addActionListener((action) -> {
      new ListLocationForm(this).show();
} );
        
        addAll(tfdb,tfdf,tftotall,tfProduit,btnValider,btnAfficher);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}

