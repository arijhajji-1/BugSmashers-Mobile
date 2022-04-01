/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_backend;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ToastBar;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.*;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import entities.actualite;
import gui.Home;
import gui.LoginForm;
import static gui_backend.ListReclamation.theme;
import java.io.IOException;
import services.ServiceActualite;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Random;
import utils.Statics;
/**
 *
 * @author Arij Hajji
 */
public class AddActualiteForm extends Form{
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
    public AddActualiteForm (Form previous) {
        setTitle("Add Actualite");
        setLayout(BoxLayout.y());

        TextField tftitre= new TextField("", "titre");

        TextField tfdescription= new TextField("", "description");
        TextField tfimageName= new TextField("", "imageName");
        Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE);
        date.setDate(new Date());
        Button btnValider = new Button("Add actualite");
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
                if ((tftitre.getText().length()==0)||(date.getText().length()==0)||(tfdescription.getText().length()==0));
               // Dialog.show("Alert", "Please fill all the fields", new Command("OK"));


                    int responseCode;
                    {
                        responseCode=ServiceActualite.getInstance().AddActualite(new actualite(tftitre.getText(), date.getValue().toString(), tfdescription.getText(),filename ));
                        if(responseCode==2000 ) {
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                              ToastBar.getInstance().setPosition(BOTTOM);
                ToastBar.Status status = ToastBar.getInstance().createStatus();
                status.setShowProgressIndicator(true);
                //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                status.setMessage("Actualite ajouté avec succes");
                status.setExpires(10000);
                status.show();
                        }
                    }



            }
        });

        addAll(tftitre,date,tfdescription,btnUpload,btnValider);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    }


}