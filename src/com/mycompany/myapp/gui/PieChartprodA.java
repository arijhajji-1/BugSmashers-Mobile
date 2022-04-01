/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.myapp.gui;
import java.util.Arrays;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.SeriesSelection;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.mycompany.myapp.services.ServiceProduit;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.geom.Shape;
import java.util.Collection;

import java.util.*;

import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author USER
 */
public class PieChartprodA extends Form {
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
public PieChartprodA(){
    setLayout(new BorderLayout());
    
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
