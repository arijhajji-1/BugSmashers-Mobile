/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codenameone1.services;

import com.codenaeone1.entities.Reclamation;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codenameone1.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gloria
 */
public class serviceReclamation {
    
    public ArrayList<Reclamation> reclamations;
    
    public static serviceReclamation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
     public int resultCode;

    public serviceReclamation() {
         req = new ConnectionRequest();
    }

    public static serviceReclamation getInstance() {
        if (instance == null) {
            instance = new serviceReclamation();
        }
        return instance;
    }



    public ArrayList<Reclamation> parseCat(String jsonText){
        try {
            reclamations=new ArrayList<>();
            
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Reclamation r = new Reclamation();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId((int)id);
                r.setIdCommande(((int)Float.parseFloat(obj.get("idcommande").toString())));
                                if (obj.get("categorie")==null)
              r.setCategorie("null");
                else
                    r.setCategorie(obj.get("categorie").toString());
                if (obj.get("sujet")==null)
              r.setSujet("null");
                else
                    r.setSujet(obj.get("sujet").toString());
                       if (obj.get("description")==null)
              r.setDescription("null");
                else
                    r.setDescription(obj.get("description").toString());
                reclamations.add(r);
            }
            
            
        } catch (IOException ex) {
            
        }
     
        return reclamations;
    }
    
   public ArrayList<Reclamation> getAllReclamaion(){
         req.setUrl(Statics.BASE_URL + "/affReclamation");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    reclamations = new ArrayList<>();
                    Map<String, Object> tasksListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

                    for (Map<String, Object> obj : list) {
                        Reclamation reclamation;
                        reclamation = new Reclamation(
                                (int) Float.parseFloat(obj.get("id").toString()),
                                (int) Float.parseFloat(obj.get("idCommande").toString()),
                                (String) obj.get("categorie"),
                                (String) obj.get("sujet"),
                                (String) obj.get("description"),
                                (String) obj.get("date")
                                
                                //  Float.parseFloat(obj.get("pourcentage_like").toString())
                        );
                        
                        reclamations.add(reclamation);
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
        return reclamations;
                
               
      //continue
                }
            
            
            ///////////////*************supprimer reclamation***********************//
    public int  DeleteReclamation(Reclamation r){
        
          req.setUrl(Statics.BASE_URL + "/suppReclamation");
        req.addArgument("id", String.valueOf(r.getId()));
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = req.getResponseCode();
                req.removeResponseListener(this);

            }
        });
        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {

        }
        return resultCode;
    }
      /* String url = Statics.BASE_URL + "/suppReclamation?id="+id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;*/
      
     
     
    
    
    //************************afficher reclamation**************************//
    
     public ArrayList<Reclamation> getAllReclamation() {
        req.setUrl(Statics.BASE_URL + "/affReclamation");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    reclamations = new ArrayList<>();
                    Map<String, Object> tasksListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

                    for (Map<String, Object> obj : list) {
                           Reclamation  r;
                        r = new Reclamation(
                                (int) Float.parseFloat(obj.get("id").toString()),
                                //(int) Float.parseFloat(obj.get("idCommande").toString()),
                                (String) obj.get("sujet"),
                                (String) obj.get("description"),
                                (String) obj.get("categorie"),
                                (String) obj.get("date")
                             
                                
                                //  Float.parseFloat(obj.get("pourcentage_like").toString())
                        );
                        System.out.println(r.getId());
                        reclamations.add(r);
                    }

                } catch (IOException ex) {
                    System.out.println("pas de reclamations");
                }

                req.removeResponseListener(this);
            }
        });
        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {

        }
        return reclamations ;
    }
    
    //*********************ajouter une reclamation********************//
    public boolean ajoutReclamation(Reclamation r) {
        
          System.out.println(r);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "/ajoutReclamation?idCommande=\"+r.getIdCommande()+\"&sujet=\"+r.getSujet()+\"&description=\"+r.getDescription()+\"&categorie=\"+r.getCategorie()+\"&date=\"+r.getDate();";
    
       req.setUrl(url);
       req.setPost(false);
       req.addArgument("id commande", r.getIdCommande()+"");
       req.addArgument("categorie", r.getCategorie());
       req.addArgument("sujet", r.getSujet());
       req.addArgument("description", r.getDescription());
      
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
    
      //************************modifier une reclamation*********************//
    public int modifierReclamation(Reclamation r) {
         String url = Statics.BASE_URL + "/modReclamation?id="+r.getId()+"&categorie="+r.getCategorie()+"&sujet="+r.getSujet()+"&description="+r.getDescription()+"&date="+r.getDate();
     req.setUrl(url);       
     
          // l'ordre?? try it in symfony 
          //on a ajouté statut et id commande dans symfony aussi je crois que c'est le probleme
          // hmm check it because the problem with the id is rresolved idk about the rest
          //ok je vais le faire
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = req.getResponseCode();
                req.removeResponseListener(this);

            }
        });
        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {

        }
        return resultCode;
      
    
    }
    
    
    
}
