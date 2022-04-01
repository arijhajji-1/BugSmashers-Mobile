/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.ProduitAcheter;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.MyApplication;
import entities.ProduitLouer;
import utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Arij Hajji
 */
public class ServiceProduit {

    public ArrayList<ProduitAcheter> ProduitsAcheter;
    public ArrayList<ProduitLouer> ProduitsLouer;
    public Map<String, Object> parseAvis = new HashMap<String, Object>();
    public Map<String, Object> avis = new HashMap<String, Object>();
    public static ServiceProduit instance=null;
    public boolean resultOK;
    public Map <String, String> catProd = new HashMap<String, String>();
    private ConnectionRequest req;

    private ServiceProduit() {
         req = new ConnectionRequest();
    }

    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }

  public boolean addProduitA(ProduitAcheter t) {
        System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "/addProduitAcheterJSON";
    
       req.setUrl(url);
       
       req.addArgument("id", t.getId()+"");
       req.addArgument("nom", t.getNom()+"");
       req.addArgument("prix", t.getPrix()+"");
       req.addArgument("qte", t.getQte()+"");
        req.addArgument("category", t.getCategory()+"");
        req.addArgument("marque", t.getMarque()+"");
       req.addArgument("description", t.getDescription()+"");
       
       req.addArgument("imagePath", t.getImagePath()+"");
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

public void addProduitAvis(String desc, Object rating, String id) {
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "/addAvisProduit/"+id;
    
       req.setUrl(url);
              req.addArgument("nom", String.valueOf(MyApplication.getSession().getNom()));
              req.addArgument("email", String.valueOf(MyApplication.getSession().getEmail()));

       req.addArgument("rating", rating+"");
       req.addArgument("description", desc+"");
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public boolean deleteProduitA(String id) {
        System.out.println(id);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "/deleteProduitAcheterJSON/"+id;
    
       req.setUrl(url);
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
public ArrayList<ProduitAcheter> recherche(String searched){

         req.setUrl(Statics.BASE_URL + "/recherchemobileprodA");
                 req.addArgument("searchbar",searched);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    ProduitsAcheter = new ArrayList<>();
                    Map<String, Object> tasksListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

                    for (Map<String, Object> obj : list) {
                       ProduitAcheter t = new ProduitAcheter();
                        t.setId((obj.get("id").toString()));
                        t.setPrix(Float.parseFloat(obj.get("prix").toString()));
                        t.setNom(obj.get("nom").toString());
                        t.setQte( Math.round(Float.parseFloat(obj.get("qte").toString())));
                        t.setImagePath( obj.get("imagePath").toString());
                        t.setCategory(obj.get("category").toString());
                        t.setDescription(obj.get("description").toString());
                        t.setMarque(obj.get("marque").toString());
                        System.out.println(searched);

                        ProduitsAcheter.add(t);
                    }

                } catch (IOException ex) {
                    System.out.println("reparation vide");
                }

                req.removeResponseListener(this);
            }
        });
        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {

        }
        return ProduitsAcheter;
    }
public boolean addProduitL(ProduitLouer t) {
        System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "/addProduitLouerJSON";
    
       req.setUrl(url);
       
       req.addArgument("id", t.getId()+"");
       req.addArgument("nom", t.getNom()+"");
       req.addArgument("prix", t.getPrix()+"");
       req.addArgument("etat", t.getEtat()+"");
       req.addArgument("dispo", t.getDispo()+"");
       req.addArgument("marque", t.getMarque()+"");
        req.addArgument("category", t.getCategory()+"");
       req.addArgument("description", t.getDescription()+"");
       req.addArgument("imagePath", t.getImagePath()+"");
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean modifyProduitA(ProduitAcheter t) {
        System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "/modifProduitAcheterJSON/"+t.getId();
    
       req.setUrl(url);
       
       req.addArgument("id", t.getId()+"");
       req.addArgument("nom", t.getNom()+"");
       req.addArgument("prix", t.getPrix()+"");
       req.addArgument("qte", t.getQte()+"");
        req.addArgument("marque", t.getMarque()+"");
        req.addArgument("category", t.getCategory()+"");
       req.addArgument("description", t.getDescription()+"");
       req.addArgument("imageName", t.getImagePath()+"");
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }


    public ArrayList<ProduitAcheter> parseAcheters(String jsonText){
        try {
            ProduitsAcheter=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ProduitAcheterListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)ProduitAcheterListJson.get("root");
            for(Map<String,Object> obj : list){
                ProduitAcheter t = new ProduitAcheter();
                t.setId((obj.get("id").toString()));
                t.setPrix(Float.parseFloat(obj.get("prix").toString()));
                t.setNom(obj.get("nom").toString());
                t.setQte( Math.round(Float.parseFloat(obj.get("qte").toString())));
                t.setImagePath( obj.get("imagePath").toString());
                t.setCategory(obj.get("category").toString());
                t.setDescription(obj.get("description").toString());
                t.setMarque(obj.get("marque").toString());
                ProduitsAcheter.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return ProduitsAcheter;
    }
public Map<String, String> parseCatProd(String jsonText){
        Map<String, String> catProd=new HashMap<String, String>();
try {
            
            JSONParser j = new JSONParser();
            Map<String,Object> ProduitAcheterListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)ProduitAcheterListJson.get("root");
            for(Map<String,Object> obj : list){
                catProd.put(obj.get("label").toString(),obj.get("prod").toString());
            }
            
            
        } catch (IOException ex) {
            
        }
        return catProd;
    }

public Map<String, Object> parseAvis(String jsonText){
     
try {
            
            JSONParser j = new JSONParser();
            Map<String,Object> ProduitAcheterListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)ProduitAcheterListJson.get("root");
            for(Map<String,Object> obj : list){
                System.out.println(Math.round(Float.parseFloat(obj.get("rating").toString())));
                avis.put(obj.get("description").toString(), Math.round(Float.parseFloat(obj.get("rating").toString())));
            }
            
            
        } catch (IOException ex) {
            
        }
        return avis;
}
public ArrayList<ProduitLouer> parseLouers(String jsonText){
        try {
            ProduitsLouer=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ProduitlouerListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)ProduitlouerListJson.get("root");
            for(Map<String,Object> obj : list){
                ProduitLouer t = new ProduitLouer();
                t.setId((obj.get("id").toString()));
                t.setPrix(Float.parseFloat(obj.get("prix").toString()));
                t.setNom(obj.get("nom").toString());
                t.setEtat( obj.get("etat").toString());
                t.setDispo(Boolean.parseBoolean(obj.get("dispo").toString()));
                t.setImagePath( obj.get("imagePath").toString());
                t.setCategory(obj.get("category").toString());
                t.setDescription(obj.get("description").toString());
                t.setMarque(obj.get("marque").toString());
                ProduitsLouer.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return ProduitsLouer;
    }
    
    public ArrayList<ProduitAcheter> getAllProduitsAcheter(){
        //String url = Statics.BASE_URL+"/Students/";
        String url = Statics.BASE_URL +"/displayEnn";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitsAcheter = parseAcheters(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ProduitsAcheter;
    }
    public Map<String,String> getCatProdN(){
        //String url = Statics.BASE_URL+"/Students/";
        String url = Statics.BASE_URL +"/catprodAN";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                catProd = parseCatProd(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return catProd;
    }
    public Map<String, Object> getAllProduitsAvis(String id){
        
        //String url = Statics.BASE_URL+"/Students/";
        String url = Statics.BASE_URL +"/getAvisProduit/"+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               parseAvis = parseAvis(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return parseAvis;
    }
public ArrayList<ProduitLouer> getAllProduitsLouer(){
        //String url = Statics.BASE_URL+"/Students/";
        String url = Statics.BASE_URL +"/listepl";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitsLouer = parseLouers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ProduitsLouer;
    }

    
}
