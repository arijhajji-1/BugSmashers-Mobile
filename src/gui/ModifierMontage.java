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
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import entities.ProduitAcheter;
import entities.montage;
import static gui.ShowMontage.montageActuelle;
import services.ServiceMontage;
import services.ServiceProduit;

/**
 *
 * @author Arij Hajji
 */
public class ModifierMontage extends Form{
         Label labelProcesseur, labelCartemere,labelCartegraphique,labelDisquesysteme,labelBoitier,labelStockagesupp ;
  
    ComboBox tfProcesseur,tfCartegraphique,tfCartemere,tfDisquesysteme,tfBoitier,tfStockagesupp ;
    Button btnModifier;

    Form previous;

    public ModifierMontage(Form previous) {
        super(new BoxLayout(BoxLayout.Y_AXIS));

        this.previous = previous;

        addGUIs();
        addActions();
        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> Home.accueilFrontForm.showBack());

    }

    private void addGUIs() {
 labelProcesseur = new Label("Processeur :");
        labelProcesseur.setUIID("defaultLabel");
        tfProcesseur = new ComboBox<>(montageActuelle.getProcesseur());
               
         for (ProduitAcheter produit : ServiceProduit.getInstance(). getAllProduitsAcheter()) {
            tfProcesseur.addItem(produit.getNom());
        }

  labelCartegraphique = new Label("Carte graphique :");
        labelCartegraphique.setUIID("defaultLabel");
        tfCartegraphique = new ComboBox<>(montageActuelle.getCarte_graphique());
   for (ProduitAcheter produit : ServiceProduit.getInstance(). getAllProduitsAcheter()) {
            tfCartegraphique.addItem(produit.getNom());
        }
        labelCartemere = new Label("Carte mere : ");
        labelCartemere.setUIID("defaultLabel");
        tfCartemere = new ComboBox<>(montageActuelle.getCarte_mere());
                for (ProduitAcheter produit : ServiceProduit.getInstance(). getAllProduitsAcheter()) {
          
            tfCartemere.addItem(produit.getNom());
        }

  labelDisquesysteme = new Label("Disque systeme : ");
        labelDisquesysteme.setUIID("defaultLabel");
        tfDisquesysteme = new ComboBox<>(montageActuelle.getDisque_systeme());
                 for (ProduitAcheter produit : ServiceProduit.getInstance(). getAllProduitsAcheter()) {
            tfDisquesysteme.addItem(produit.getNom());
        }

labelBoitier = new Label("Boitier : ");
        labelBoitier.setUIID("defaultLabel");
        tfBoitier = new ComboBox<>(montageActuelle.getBoitier());
 for (ProduitAcheter produit : ServiceProduit.getInstance(). getAllProduitsAcheter()) {
            tfBoitier.addItem(produit.getNom());
        }
        labelStockagesupp = new Label("Stockage supp  : ");
        labelStockagesupp .setUIID("defaultLabel");
        tfStockagesupp  =new ComboBox<>(montageActuelle.getStockage_supp());
 for (ProduitAcheter produit : ServiceProduit.getInstance(). getAllProduitsAcheter()) {
            tfStockagesupp.addItem(produit.getNom());
        }
        btnModifier = new Button("Modifier");

        btnModifier.setUIID("Button");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        container.setUIID("ReparationContainer");
        container.addAll(labelProcesseur, tfProcesseur, labelCartegraphique,tfCartegraphique,labelBoitier,tfBoitier,labelStockagesupp,tfStockagesupp,labelCartemere,tfCartemere,labelDisquesysteme,tfDisquesysteme, btnModifier);

        this.addAll(container);
    }

      private boolean controleDeSaisie() {
        if (tfProcesseur.getSelectedItem().equals("")) {
            Dialog.show("Objet vide", "", new Command("Ok"));
            return false;
        }
        if (tfCartegraphique.getSelectedItem().equals("")) {
            Dialog.show("carte graphique vide", "", new Command("Ok"));
            return false;
        }

        return true;
    }

    private void addActions() {

        btnModifier.addActionListener((action) -> {
            if (controleDeSaisie()) {
                System.out.println("11");
                montage montage = new montage(
                        montageActuelle.getIdmontage(),(String) tfProcesseur.getSelectedItem(),
                        (String) tfCartegraphique.getSelectedItem(), (String) tfCartemere.getSelectedItem(),
                        (String) tfDisquesysteme.getSelectedItem(),(String) tfBoitier.getSelectedItem(),
                        (String) tfStockagesupp.getSelectedItem()
                );
                      
             
                int responseCode = ServiceMontage.getInstance().modifierMontage(montage);
                System.out.println("22");
                if (responseCode == 200) {
                    Dialog.show("Succés", "montage modifieré avec succes", new Command("Ok"));
                } else {
                    Dialog.show("Erreur", "Erreur d'modifier de montage. Code d'erreur : " + responseCode, new Command("Ok"));
                }

                ((ShowMontage) previous).refresh();
                previous.showBack();
            }
        });

    }
    
}
