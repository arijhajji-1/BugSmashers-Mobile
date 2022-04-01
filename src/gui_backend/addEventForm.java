/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_backend;

import com.codename1.io.rest.Response;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import entities.actualite;
import entities.evenement;
import services.ServiceActualite;
import services.ServiceEvent;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.net.URI;
import java.net.URISyntaxException;
import utils.Statics;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ToastBar;
import java.io.IOException;
import java.util.Random;

import static com.codename1.push.PushContent.setTitle;
import static com.codename1.ui.Component.BOTTOM;
import gui.Home;
import gui.LoginForm;
import static gui_backend.ListReclamation.theme;
/**
 *
 * @author Arij Hajji
 */
public class addEventForm extends Form{


    String fileNameInServer;
    String ipath;
    public static String generateRandomPassword() {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
                +"lmnopqrstuvwxyz_-";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(20);
        for (int i = 0; i < 20; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }
    public addEventForm (Form previous) {
        setTitle("Add Evenement");
        setLayout(BoxLayout.y());
FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "TitleCommand", 5);

        this.getToolbar().addCommandToRightBar(null,icon,evt1 -> new LoginForm(theme).show());

        this.getToolbar().addCommandToRightBar(null,icon,evt1 -> new HomeBack(this).show());
        this.getToolbar().addCommandToSideMenu("HOME",null,evt -> new Home().show());
        this.getToolbar().addCommandToSideMenu("Reparation",null,evt1 -> new AfficheReparation(this).show());
        this.getToolbar().addCommandToSideMenu("Montage",null,evt1 -> new AfficheMontage(this).show());
        this.getToolbar().addCommandToSideMenu("Avis reparation",null,evt1 -> new AfficheAvisReparation(this).show());
                this.getToolbar().addCommandToSideMenu("Ajouter Produit",null,evt1 -> new AddProduitAcheterForm(this).show());
               this.getToolbar().addCommandToSideMenu("Ajouter Produit louer",null,evt1 -> new AddProduitLouerForm(this).show());
                this.getToolbar().addCommandToSideMenu("Ajouter category",null,evt1 -> new AddCategoryForm(this).show());
               this.getToolbar().addCommandToSideMenu("List category",null,evt1 -> new ListCategoryBack(this).show());
this.getToolbar().addCommandToSideMenu("List produit",null,evt1 -> new ListProduitAcheterBack(this).show());

                this.getToolbar().addCommandToSideMenu("List reclamation",null,evt1 -> new ListReclamation(this).show());
                this.getToolbar().addCommandToSideMenu("Statistique Reclamation",null,evt1 -> new statReclamation());

                this.getToolbar().addCommandToSideMenu("Ajouter actualite",null,evt1 -> new AddActualiteForm(this).show());
           this.getToolbar().addCommandToSideMenu("Ajouter evenement",null,evt1 -> new addEventForm(this).show());
        this.getToolbar().addCommandToSideMenu("list evenement",null,evt1 -> new ListEventbackForm(this).show());
        this.getToolbar().addCommandToSideMenu("list actualite",null,evt1 -> new ListeActbackForm(this).show());


                           this.getToolbar().addCommandToSideMenu("Statistique Produit",null,evt1 -> new PieChartProdA().show());
        TextField tfnom= new TextField("", "nom");

        TextField tfdescription= new TextField("", "description");
        Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE);
        date.setDate(new Date());
        Button btnValider = new Button("Add evenement");
        Button btnUpload = new Button("Upload Image");
        MultipartRequest cr = new MultipartRequest();
        String filename = "i"+generateRandomPassword() + ".png";
        btnUpload.addActionListener((evt) -> {
            String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);

            cr.setUrl(Statics.URL_UPLOAD);
            cr.setPost(true);
            String mime = "image/png";
            try {
                cr.addData("file", filePath, mime);
            } catch (IOException ex) {
                Dialog.show("Error", ex.getMessage(), "OK", null);
            }
            cr.setFilename("file",filename);//any unique name you want

            InfiniteProgress prog = new InfiniteProgress();
            Dialog dlg = prog.showInifiniteBlocking();
            cr.setDisposeOnCompletion(dlg);
            NetworkManager.getInstance().addToQueueAndWait(cr);
            Dialog.show("Success", "Image uploaded", "OK", null);

        });

        btnValider.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom.getText().length()==0)||(date.getText().length()==0)||(tfdescription.getText().length()==0));
                Dialog.show("Alert", "connection accepted", new Command("OK"));
                  ToastBar.getInstance().setPosition(BOTTOM);
                ToastBar.Status status = ToastBar.getInstance().createStatus();
                status.setShowProgressIndicator(true);
                //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                status.setMessage("Evenement ajouté avec succes");
                status.setExpires(10000);
                status.show();


                {
                    int responseCode;

                    responseCode= ServiceEvent.getInstance().AddEvenement(new evenement(tfnom.getText(), date.getValue().toString(), tfdescription.getText(), filename));



                    if(responseCode==2000 ) {
                        NetworkManager.getInstance().addToQueueAndWait(cr);
                        Dialog.show("Success","Connection accepted",new Command("OK"));
                    }
                   /*/ Email from = new Email("Reloua.tunisie@gmail.com");
                    String subject = "Ajout événement";
                    Email to = new Email("Reloua.tunisie@gmail.com");
                    Content content = new Content("text/plain", "un événement est ajouté");
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

/*/
                }

            }
        });


        addAll(tfnom,date,tfdescription,btnUpload,btnValider);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }



}
