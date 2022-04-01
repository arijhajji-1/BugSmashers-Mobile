/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_backend;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import entities.evenement;
import gui.Home;
import gui.LoginForm;
import services.ServiceEvent;

import java.util.Date;



import static gui_backend.ListEventbackForm.evenementActuelle;
import static gui_backend.ListReclamation.theme;
/**
 *
 * @author Arij Hajji
 */
public class ModifierEventForm extends Form{

    String fileNameInServer;
    String ipath;
    Picker date = new Picker();
    public ModifierEventForm (Form previous) {
        setTitle("Modifier Evenement");
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
        TextField tfnom= new TextField(evenementActuelle.getNom(), "nom");

        TextField tfdescription= new TextField(evenementActuelle.getDescription(), "description");
        TextField tfimageName= new TextField(evenementActuelle.getImageName(), "imageName");

        date.setType(Display.PICKER_TYPE_DATE);
        date.setDate(new Date());
        Button btnValider = new Button(" modifier");

        btnValider.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom.getText().length()==0)||(date.getText().length()==0)||(tfdescription.getText().length()==0)||(tfimageName.getText().length()==0));
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));

                {
                    int responseCode;
                    try {
                        responseCode= ServiceEvent.getInstance().modifierEvenement(new evenement(evenementActuelle.getId(),tfnom.getText(), date.getValue().toString(), tfdescription.getText(), tfimageName.getText()));
                        if(responseCode==200 ){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", " Server error", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfnom,date,tfdescription,tfimageName,btnValider);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    }

}

