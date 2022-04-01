/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import entities.User;
import gui_backend.HomeBack;
import gui.Home;
import java.io.IOException;
import java.util.List;
import services.ServiceUser;

/**
 *
 * @author Arij Hajji
 */


public class LoginForm extends Form {

    public LoginForm(Resources theme) {
super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        addGUIs();
        addActions();
    }

    TextField tfEmail;
    TextField tfPassword;
    Button btnConnexion,btnInscription;
    //Button btnConnexion = new Button("LOGIN");


    private void addGUIs() {

        //tfEmail = new TextField("", "Entrez votre email", 20, TextField.EMAILADDR);
        //tfPassword = new TextField("", "PASSWORD", 20, TextField.PASSWORD);
         tfEmail = new TextField("", "Login", 20, TextField.EMAILADDR) ;
         tfPassword = new TextField("", "Password", 20, TextField.PASSWORD) ;
        //btnConnexion = new Button("Connexion");
           
        btnInscription = new Button("Inscription");
 tfEmail.getAllStyles().setMargin(LEFT, 0);
        tfPassword .getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
          btnConnexion = new Button("LOGIN");
       // btnConnexion.setUIID("LoginButton");
      Container by = BoxLayout.encloseY(
                
                BorderLayout.center(tfEmail).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(tfPassword).
                        add(BorderLayout.WEST, passwordIcon),
                btnConnexion,
                 btnInscription
        );
        add(BorderLayout.CENTER, by);
        
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
        //this.addAll(tfEmail, tfPassword, btnConnexion, btnInscription);
    }

    private void addActions() {
        btnConnexion.addActionListener(action -> {
            if (ServiceUser.getInstance().verifierMotDePasse(tfEmail.getText(), tfPassword.getText())) {
                connexion(ServiceUser.getInstance().recupererUserParEmail(tfEmail.getText()));
            } else {
                Dialog.show("Erreur", "Identifiants invalides", new Command("Ok"));
            }
        });

       /*btnInscription.addActionListener(action -> {
            new Inscription(this).show();
        })*/

     
    }

    private void connexion(User user) {
        List<String> roles = user.getRoles();

       MyApplication.setSession(user);

        for (int i = 0; i < roles.size(); i++) {
            switch (roles.get(i)) {
                case "ROLE_ADMIN":
                    new gui_backend.HomeBack(this).show();
                    break;
            
                case "ROLE_USER":
                    new gui.Home().show();
                    break;
            }
        }
    }
}