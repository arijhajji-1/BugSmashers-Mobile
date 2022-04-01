/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ToastBar;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.MyApplication;
import entities.Commande;
import services.ServiceCommande;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;
/**
 *
 * @author Arij Hajji
 */
public class AddCommandeForm extends Form{

    public AddCommandeForm(Form previous) {
        setTitle("Add new Commande");
        setLayout(BoxLayout.y());
        
        TextField tfnom = new TextField("","nom");
        TextField tfprenom= new TextField("", "prenom");
        TextField tfpaiment= new TextField("", "paiment");
        TextField tfadress= new TextField("", "adresse");
        TextField tftelephone= new TextField("", "telephone");
        Button btnValider = new Button("Add Commande");
       Button btnAfficher = new Button("Afficher");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom.getText().length()==0)||(tfprenom.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                            Commande t = new Commande(tfnom.getText().toString(), tfprenom.getText().toString(),tfpaiment.getText().toString(),tfadress.getText().toString(),tftelephone.getAsInt(BASELINE));
Email from = new Email("Reloua.tunisie@gmail.com");
                String subject = "Event response";
                Email to = new Email(String.valueOf(MyApplication.getSession().getEmail()));
                Content content = new Content("text/plain", "Votre commande est en cours de livraison");
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
                status.setMessage("Commande ajouté avec succès");
                status.setExpires(10000);
                status.show();

                refreshTheme();

               
LocalNotification n = new LocalNotification();
        n.setId("notif");
        n.setAlertBody("Commande ajoutè avec succés");
       n.setAlertTitle("RELOUA TEAM!");
       n.setAlertSound("/notification_sound_party.mp3"); //file name must begin with notification_sound
                        /*Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis()  + 10, // fire date/time
            
                LocalNotification.REPEAT_MINUTE // Whether to repeat and what frequency

        );*/

                  Display.getInstance().scheduleLocalNotification(n, System.currentTimeMillis()  + 10*1000, LocalNotification.REPEAT_MINUTE);
                System.out.println(System.currentTimeMillis());
                        
                            if( ServiceCommande.getInstance().addCommande(t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           previous.showBack();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                     catch (NumberFormatException e) {
                        Dialog.show("ERROR", "nom must be a string", new Command("OK"));
                        
                    }
                    
                }
                
                
            }
        });
        btnAfficher.addActionListener((action) -> {
      new ListCommandesForm(this).show();
} );
        
        addAll(tfnom,tfprenom,tfpaiment,tfadress,tftelephone,btnValider,btnAfficher);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}

