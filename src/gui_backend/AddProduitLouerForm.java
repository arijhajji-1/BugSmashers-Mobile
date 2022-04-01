/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_backend;
import com.codename1.ui.Button;
import java.util.HashMap;
import java.util.Map;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.ComboBox;
import com.codename1.components.MultiButton;
import com.codename1.ui.layouts.BoxLayout;
import entities.ProduitLouer;
import entities.Category;
import services.ServiceProduit;
import services.ServiceCategory;
import com.codename1.ui.list.GenericListCellRenderer;
import java.util.ArrayList;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ToastBar;
import static com.codename1.ui.Component.BOTTOM;
import java.io.IOException;
import java.util.Random;
import utils.Statics;
import com.codename1.ui.Display;
import gui.Home;
import gui.LoginForm;
import static gui_backend.ListReclamation.theme;
/**
 *
 * @author Arij Hajji
 */
public class AddProduitLouerForm extends Form{

    public static String generateRandomPassword() {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
          +"lmnopqrstuvwxyz_-";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(20);
		for (int i = 0; i < 20; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}

 AddProduitLouerForm(Form previous) {
        setTitle("Ajouter un produit acheter");
        setLayout(BoxLayout.y());
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
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
        ArrayList<Category> categories=ServiceCategory.getInstance().getAllCategories();
        TextField tfNom= new TextField("", "nom");
        TextField tfDescription= new TextField("", "description");
        TextField tfEtat= new TextField("","etat");
        TextField tfDispo= new TextField("","dispo");
        TextField tfMarque = new TextField("", "marque");
        TextField tfPrix= new TextField("", "prix");
    ComboBox<Map<String, Object>> combo = new ComboBox<> ();
          for (Category cat : categories)
          combo.addItem(createListEntry(cat.getLabel(), cat.getId()));
          combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
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
                
                cr.setFilename("file", filename);//any unique name you want

                //InfiniteProgress prog = new InfiniteProgress();
                //Dialog dlg = prog.showInifiniteBlocking();
                //cr.setDisposeOnCompletion(dlg);
        });
        Button btnValider = new Button("Ajouter produit");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                boolean b = false;
            try{Boolean.parseBoolean(tfDispo.getText());}
            catch(Exception e){
                b = true;
               }
            if((tfNom.getText().equals(""))||tfEtat.getText().equals("")||b||tfDescription.getText().equals("")||tfMarque.getText().equals("")){
                
                    Dialog.show("Invalide","veuillez remplir tous les champs\n NB: Etat doit prendre True/false",new Command("OK"));
                    }
                else{
                    try {
                        ProduitLouer t = new ProduitLouer(tfNom.getText(),
 tfDescription.getText(), tfEtat.getText(), Boolean.parseBoolean(tfDispo.getText()), tfMarque.getText(),
 Float.parseFloat(tfPrix.getText()), combo.getSelectedItem().get("Line2").toString(), filename);
                        if( ServiceProduit.getInstance().addProduitL(t))
                        {
                           NetworkManager.getInstance().addToQueueAndWait(cr);
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                             ToastBar.getInstance().setPosition(BOTTOM);
                ToastBar.Status status = ToastBar.getInstance().createStatus();
                status.setShowProgressIndicator(true);
                //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                status.setMessage("Produit ajouté avec succes");
                status.setExpires(10000);
                status.show();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }

                
                
            

           
        }});
        
        addAll(tfNom,tfPrix,combo,tfMarque,tfEtat, tfDispo,tfDescription,btnUpload,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }

 private Map<String, Object> createListEntry(String name, int date) {
    Map<String, Object> entry = new HashMap<>();
    entry.put("Line1", name);
    entry.put("Line2", date);
    return entry;
}
}
