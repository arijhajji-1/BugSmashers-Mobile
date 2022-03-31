package com.codenameone1.gui;


import com.codenaeone1.entities.Reclamation;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codenameone1.gui.home;
import com.codenameone1.services.serviceReclamation;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gloria
 */
public class statReclamation extends Form{
    
       ArrayList<Reclamation> mat;
       
        
    
    public float calcul_nbr_categorie(ArrayList<Reclamation> r,String ch){
        
         ArrayList<Reclamation> mat = new ArrayList<Reclamation>();
         mat =     serviceReclamation.getInstance().getAllReclamation();

        
    int f=0;
    for(int i=0;i<mat.size();i++){
        if (mat.get(i).getCategorie().equals(ch)){ f++;}
    }
    return f;
    
    
}

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(70);
    renderer.setLegendTextSize(70);
    renderer.setMargins(new int[]{12, 14, 11, 10, 19,0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}


protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
        series.add("location", this.calcul_nbr_categorie(mat, "location") );
        series.add("montage", this.calcul_nbr_categorie(mat, "montage") );
         series.add("reparation", this.calcul_nbr_categorie(mat, "reparation") );
        
        

    return series;
    
}

public Form createPieChartForm() {
    
    
    
     new Label("Statistiques Reclamations");
    // Generate the values
    double[] values = new double[]{
        this.calcul_nbr_categorie(mat, "3"),
        this.calcul_nbr_categorie(mat, "5"),
        this.calcul_nbr_categorie(mat, "8")
        };


    // Set up the renderer
    int[] colors = new int[]{ColorUtil.GRAY, ColorUtil.GREEN, ColorUtil.CYAN,ColorUtil.BLUE};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(20);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLUE);
    r.setGradientStop(0, ColorUtil.GREEN);





    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("reclamation", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    Form f = new Form("reclamation", new BorderLayout());
    f.addComponent(BorderLayout.CENTER, c);
            //hi.addComponent(BorderLayout.CENTER, clock);

        f.show();

        f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> {new home().show();});
   /* f.getToolbar().addCommandToOverflowMenu("Retour", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
              Gestcalan f2 = new Gestcalan();
              f2.show();
            }
        });*/
return f;
    
    

}












}