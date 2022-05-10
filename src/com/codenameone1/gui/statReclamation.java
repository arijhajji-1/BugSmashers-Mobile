package com.codenameone1.gui;


import com.codenaeone1.entities.Reclamation;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
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
    
    
        private boolean drawOnMutableImage;
   
    
    private double montage = 3;
    private double reparation = 6;
    private double location = 8;
   
    Form current;
   home form;
    public statReclamation()  {
       
            current= this;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
    
        getContentPane().setScrollVisible(false);
        
       // super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        //addTab(swipe, res.getImage("back-logo.jpeg"), spacer1, "Bienvenue");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
//        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        refreshTheme();
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        
        RadioButton all = RadioButton.createToggle("Feedback", barGroup);
       
        all.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("Categorie Reclamation", barGroup);
        popular.setUIID("SelectBar");
        RadioButton feedback = RadioButton.createToggle("Feedback", barGroup);
        feedback.setUIID("SelectBar");
        RadioButton profile = RadioButton.createToggle("Statistique", barGroup);
        profile.setUIID("SelectBar");
        //Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
       /* add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, all, popular,profile),
                FlowLayout.encloseBottom(arrow)
        ));
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
           
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(popular, arrow);
                all.addActionListener((e)->{
        });

                 popular.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(profile, arrow);
        });
        bindButtonSelection(profile, arrow);*/
                profile.addActionListener((e)->{
                  new statReclamation().show();
                  
        });
        // special case for rotation
        addOrientationListener(e -> {
           // updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
    
        
        //app 
        createPieChartForm();
        
        
        }
    
    
    
    
    
     private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }

        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
   private void addButton(Image img,String title) {
          int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        cnt.setLeadComponent(image);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

  ;       
      
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta
               ));
       
       image.addActionListener(e -> {
           try{
         //  new AjoutReclamation().show();
           }catch(Exception exx) {
               
           }
               });
        add(cnt);
        image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
   }
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    

    //Statistique :
    //fontion : bch n7adhro size ta3 labels ta3 stat w margin w colors ba3d chn3aytoulha methode hethi.
    public DefaultRenderer buildCatRendrer(int []colors) {
        
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[] {20, 30, 15, 0});
        
        for(int color : colors) {
            SimpleSeriesRenderer simpleSeriesRenderer = new SimpleSeriesRenderer();
            
            simpleSeriesRenderer.setColor(color);
            renderer.addSeriesRenderer(simpleSeriesRenderer);
        }
        return renderer;
     }  
    
    
    public void createPieChartForm() {
        
        
        double total = montage + reparation+ location;
        
        //values
        double prcntlocation = (location *50)/total;
        
        double prcntReparation = (reparation * 50)/total;
        
          double prcntmontage = (montage * 50)/total;
        
        //colors set:
        int[]colors = new int[]{0xf4b342, 0x52b29a};
        
        DefaultRenderer renderer = buildCatRendrer(colors);
        renderer.setLabelsColor(0x000000); // black color for labels.
        
        renderer.setZoomButtonsVisible(true);//zoom
        renderer.setLabelsTextSize(40);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setHighlighted(true);
        
        //CREATe the chart ...
        PieChart chart = new PieChart(buildDataset("title",Math.round(prcntlocation),Math.round(prcntReparation)), renderer);
        
        //
        ChartComponent c  = new ChartComponent(chart);
        
        String []messages = {
            "Statistique reclamations/categorie"
        };
        
        SpanLabel message = new SpanLabel(messages[0], "WelcomeMessage");
        
        Container cnt = BorderLayout.center(message);
        cnt.setUIID("Container");
        add(cnt);
        add(c);
                
        show();        
    }

    private CategorySeries buildDataset(String title, double prcntFeed, double prcntRec) {
        
        CategorySeries series = new CategorySeries(title);
        
        series.add("reclamation location",prcntRec);
        series.add("reclamation reparation",prcntFeed);
       // series.add("reclamation montage",prcntFeed);,double prcntloc
        
        return series;
    }
}


    
      /* ArrayList<Reclamation> mat;
       
        
    
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
        });
return f;
    
    */














