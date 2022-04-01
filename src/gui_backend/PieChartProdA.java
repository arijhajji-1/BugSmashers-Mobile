/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_backend;
import java.util.Arrays;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.SeriesSelection;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import services.ServiceProduit;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.FontImage;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.geom.Shape;
import gui.Home;
import gui.LoginForm;
import static gui_backend.ListReclamation.theme;
import java.util.Collection;

import java.util.*;

import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author Arij Hajji
 */
public class PieChartProdA extends Form {
protected CategorySeries buildCategoryDataset(String title,Collection<String> values,String[] Keys) {
    CategorySeries series = new CategorySeries(title);
    int k = 0;
    for (String value : values) {
        series.add(Keys[k++], Float.parseFloat(value));
        
    }

    return series;
}
private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(50);
    renderer.setLegendTextSize(80);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }

    return renderer;
}
public PieChartProdA(){
    setLayout(new BorderLayout());
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
    Map<String, String> mp = ServiceProduit.getInstance().getCatProdN();
    System.out.println(mp);
Collection<String>values = new ArrayList<String>();
 Collection<String>keys = new ArrayList<String>();
for (Map.Entry<String,String> entry : mp.entrySet()){
            System.out.println("Key = " + entry.getKey() +
                             ", Value = " + entry.getValue());
                             keys.add(entry.getKey());
                             values.add(entry.getValue());
    }
String [] Keys = keys.toArray(new String[keys.size()]);
    
 int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.GRAY, ColorUtil.CYAN,ColorUtil.LTGRAY,ColorUtil.BLACK,ColorUtil.BLUE, ColorUtil.MAGENTA, ColorUtil.BLUE,ColorUtil.CYAN,ColorUtil.GREEN};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(80);
    renderer.setDisplayValues(true);
    renderer.setBackgroundColor(ColorUtil.YELLOW);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLUE);
    r.setGradientStop(0, ColorUtil.MAGENTA);
    r.setHighlighted(true);
 final CategorySeries seriesSet = buildCategoryDataset("Produits Par Categorie",values,Keys);
 PieChart pi = new PieChart(seriesSet,renderer);
ChartComponent c = new ChartComponent(pi);
add(BorderLayout.CENTER,c);
}

}
