/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.mycompany.myapp.MyApplication;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import entities.ProduitAcheter;
import entities.montage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import services.ServiceMontage;
import services.ServiceProduit;

/**
 *
 * @author Arij Hajji
 */
public class AddMontage extends Form {
     Label labelProcesseur, labelCartemere,labelCartegraphique,labelDisquesysteme,labelBoitier,labelStockagesupp ;
  
    ComboBox<Map<String, Object>> tfProcesseur = new ComboBox<> ();
    ComboBox<Map<String, Object>>  tfCartegraphique = new ComboBox<> ();
     ComboBox<Map<String, Object>>  tfCartemere = new ComboBox<> ();
       ComboBox<Map<String, Object>> tfDisquesysteme = new ComboBox<> ();
         ComboBox<Map<String, Object>>  tfBoitier = new ComboBox<> ();
           ComboBox<Map<String, Object>>  tfStockagesupp = new ComboBox<> ();
    Button btnAjouter,btnAfficher;

    Form previous;
    public AddMontage(Form previous) {
         super(new BoxLayout(BoxLayout.Y_AXIS));
        
        this.previous = previous;
        
        addGUIs();
        addActions();
      
        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> Home.accueilFrontForm.showBack());
    }

    private void addGUIs() {

       labelProcesseur = new Label("Processeur :");
        labelProcesseur.setUIID("defaultLabel");
        
        //ComboBox<Map<String, Object>> tfProcesseur = new ComboBox<> ();
         for (ProduitAcheter produit : ServiceProduit.getInstance(). getAllProduitsAcheter()) {
             if((int)Float.parseFloat(produit.getCategory())==1)

            tfProcesseur.addItem(createListEntry(produit.getNom(),(int) produit.getPrix()));
          tfProcesseur.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
        }
  labelCartegraphique = new Label("Carte graphique :");
        labelCartegraphique.setUIID("defaultLabel");
         //ComboBox<Map<String, Object>> tfCartegraphique = new ComboBox<> ();
         for (ProduitAcheter produit : ServiceProduit.getInstance(). getAllProduitsAcheter()) {
             if((int)Float.parseFloat(produit.getCategory())==2)

            tfCartegraphique.addItem(createListEntry(produit.getNom(),(int) produit.getPrix()));
          tfCartegraphique.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
         }
        labelCartemere = new Label("Carte mere : ");
        labelCartemere.setUIID("defaultLabel");
      // ComboBox<Map<String, Object>> tfCartemere = new ComboBox<> ();
         for (ProduitAcheter produit : ServiceProduit.getInstance(). getAllProduitsAcheter()) {
             if((int)Float.parseFloat(produit.getCategory())==7)

            tfCartemere.addItem(createListEntry(produit.getNom(),(int) produit.getPrix()));
          tfCartemere.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
         }
  labelDisquesysteme = new Label("Disque systeme : ");
        labelDisquesysteme.setUIID("defaultLabel");
        // ComboBox<Map<String, Object>> tfDisquesysteme = new ComboBox<> ();
         for (ProduitAcheter produit : ServiceProduit.getInstance(). getAllProduitsAcheter()) {
             if((int)Float.parseFloat(produit.getCategory())==9)

            tfDisquesysteme.addItem(createListEntry(produit.getNom(),(int) produit.getPrix()));
          tfDisquesysteme.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
         }
labelBoitier = new Label("Boitier : ");
        labelBoitier.setUIID("defaultLabel");
      // ComboBox<Map<String, Object>> tfBoitier = new ComboBox<> ();
         for (ProduitAcheter produit : ServiceProduit.getInstance(). getAllProduitsAcheter()) {
             if((int)Float.parseFloat(produit.getCategory())==6)

            tfBoitier.addItem(createListEntry(produit.getNom(),(int) produit.getPrix()));
          tfBoitier.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
         }
         labelStockagesupp = new Label("Stockage supp  : ");
        labelStockagesupp .setUIID("defaultLabel");
         //ComboBox<Map<String, Object>> tfStockagesupp = new ComboBox<> ();
         for (ProduitAcheter produit : ServiceProduit.getInstance(). getAllProduitsAcheter()) {
             if((int)Float.parseFloat(produit.getCategory())==9)

            tfStockagesupp.addItem(createListEntry(produit.getNom(),(int) produit.getPrix()));
          tfStockagesupp.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
         }
        btnAjouter = new Button("Ajouter");

        btnAjouter.setUIID("Button");
 btnAfficher = new Button("Afficher");

        btnAfficher.setUIID("Button");
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        container.setUIID("ReparationContainer");
        container.addAll(labelProcesseur, tfProcesseur, labelCartegraphique,tfCartegraphique,labelBoitier,tfBoitier,labelStockagesupp,tfStockagesupp,labelCartemere,tfCartemere,labelDisquesysteme,tfDisquesysteme, btnAjouter,btnAfficher);

        this.addAll(container);
    }

    private boolean controleDeSaisie() {
        if (tfProcesseur.getSelectedItem().get("Line1").toString().equals("")) {
            Dialog.show("Objet vide", "", new Command("Ok"));
            return false;
        }
        if (tfCartegraphique.getSelectedItem().get("Line1").toString().equals("")) {
            Dialog.show("carte graphique vide", "", new Command("Ok"));
            return false;
        }

        return true;
    }

    private void addActions() {
btnAfficher.addActionListener((action) -> {
      new ShowMontage(this).show();
} );

        btnAjouter.addActionListener((action) -> {
            if (controleDeSaisie()) {
            

                int responseCode;
                responseCode = ServiceMontage.getInstance().AddMontage(new montage((String) tfProcesseur.getSelectedItem().get("Line1").toString(),
                        (String) tfCartegraphique.getSelectedItem().get("Line1").toString(), (String) tfCartemere.getSelectedItem().get("Line1").toString(),
                        (String) tfDisquesysteme.getSelectedItem().get("Line1").toString(),(String) tfBoitier.getSelectedItem().get("Line1").toString(),
                        (String) tfStockagesupp.getSelectedItem().get("Line1").toString()));
                
                   Email from = new Email("Reloua.tunisie@gmail.com");
                String subject = "Demande Montage";
                Email to = new Email(String.valueOf(MyApplication.getSession().getEmail()));
                Content content = new Content("text/plain", "Votre demande est en cours de traitement");
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
                status.setMessage("Montage ajouté avec succes");
                status.setExpires(10000);
                status.show();

                refreshTheme();
               
            }
        });
 
    }
     private Map<String, Object> createListEntry(String name, int date) {
    Map<String, Object> entry = new HashMap<>();
    entry.put("Line1", name);
    entry.put("Line2", date);
    return entry;
}   
    
}
