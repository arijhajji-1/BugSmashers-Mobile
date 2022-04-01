/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_backend;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.Display;
import com.codename1.components.SpanLabel;
import com.codename1.ui.plaf.UIManager;
import com.codename1.components.InfiniteScrollAdapter;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.Form;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.components.SpanMultiButton;
import java.util.Map;
import java.util.ArrayList;
import com.codename1.ui.TextField;
import services.ServiceProduit;
import gui_backend.ModifyProduitAcheter;
import gui.SingleProduitA;
import entities.ProduitAcheter;
import entities.ProduitLouer;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import gui.Home;
import gui.LoginForm;
import java.util.ArrayList;
/**
 *
 * @author Arij Hajji
 */
public class ListProduitAcheterBack extends Form {
    

    private String url="http://localhost:8080/assets/";
    private EncodedImage enc;
    public static 
                  TextField searchbar;
                   public Resources theme;

    Form current;
    public void refresh(Form previous) {
         this.removeAll();
         new ListProduitAcheterBack(previous).show();
        this.refreshTheme();
    }
    public void refresh(Form previous, String text) {
         this.removeAll();
         ArrayList<ProduitAcheter> prod = ServiceProduit.getInstance().recherche(text);
         new ListProduitAcheterBack(previous,prod,text).show();
        this.refreshTheme();
    }
    public ListProduitAcheterBack(Form previous) {
     current=this;
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

     getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        searchbar = new TextField("","Search");
        add(searchbar);
         searchbar.addDataChangedListener(new DataChangedListener() {
            @Override
            public void dataChanged(int type, int index) {
                refresh(previous,searchbar.getText());
           // ServiceReparation.getInstance().recherche(searchbar.getText());
}});
    ArrayList<ProduitAcheter> ProduitsAcheter=ServiceProduit.getInstance().getAllProduitsAcheter();
    setLayout(new GridLayout(10, 5));
    //Label t= new Label("ID");
    Label t2 =new Label("Label");
    Label t3 =new Label("Prix");
    Label t4 =new Label("Modify");
    Label t5 =new Label("Delete");
    //add(t);
    add(t2);
    add(t3);
    add(t4);
    add(t5);
    for(ProduitAcheter produitA : ProduitsAcheter) {
                    String nom = (String) produitA.getNom();
                    String prix = Float.toString(produitA.getPrix());
                    String imageName = (String) produitA.getImagePath();
                    Label id= new Label(Integer.toString(Math.round(Float.parseFloat(produitA.getId()))));
                    Label n =new Label(nom);
                    Label pr =new Label(prix);
                    Button md =new Button("Modify");
                    Button dl =new Button("Delete");
                    md.addActionListener(e-> new ModifyProduitAcheter(current, produitA).show());
                    dl.addActionListener(e-> {ServiceProduit.getInstance().deleteProduitA(produitA.getId());
                                              this.refresh(previous);});
                    add(id);
                    add(n);
                    add(pr);
                    add(md);
                    add(dl);
                }
        



}

public ListProduitAcheterBack(Form previous, ArrayList<ProduitAcheter> prod, String text) {
     current=this;
     getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        searchbar = new TextField(text,"Search");
        add(searchbar);
         searchbar.addDataChangedListener(new DataChangedListener() {
            @Override
            public void dataChanged(int type, int index) {
                refresh(previous,searchbar.getText());
           // ServiceReparation.getInstance().recherche(searchbar.getText());
}});
setLayout(new GridLayout(10, 5));
    //Label t= new Label("ID");
    Label t2 =new Label("Label");
    Label t3 =new Label("Prix");
    Label t4 =new Label("Modify");
    Label t5 =new Label("Delete");
    //add(t);
    add(t2);
    add(t3);
    add(t4);
    add(t5);
    for(ProduitAcheter produitA : prod) {
                    String nom = (String) produitA.getNom();
                    String prix = Float.toString(produitA.getPrix());
                    String imageName = (String) produitA.getImagePath();
                    Label id= new Label(Integer.toString(Math.round(Float.parseFloat(produitA.getId()))));
                    Label n =new Label(nom);
                    Label pr =new Label(prix);
                    Button md =new Button("Modify");
                    Button dl =new Button("Delete");
                    md.addActionListener(e-> new ModifyProduitAcheter(current, produitA).show());
                    dl.addActionListener(e-> {ServiceProduit.getInstance().deleteProduitA(produitA.getId());
                                              this.refresh(previous);});
                    add(id);
                    add(n);
                    add(pr);
                    add(md);
                    add(dl);
                }
        



}

}


