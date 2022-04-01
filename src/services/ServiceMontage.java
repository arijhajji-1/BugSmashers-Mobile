/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.montage;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics ;

/**
 *
 * @author Arij Hajji
 */
public class ServiceMontage {
  public int resultCode;
  public ArrayList<montage> montages;
    public static ServiceMontage instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceMontage() {
         req = new ConnectionRequest();
    }

    public static ServiceMontage getInstance() {
        if (instance == null) {
            instance = new ServiceMontage();
        }
        return instance;
    }
    public int AddMontage(montage t){
       
  
        System.out.println("********");
     String url = Statics.BASE_URL + "/addmontagee?Processeur="+t.getProcesseur()+"&CarteGraphique="+t.getCarte_graphique()+"&CarteMere="+t.getCarte_mere()+"&DisqueSysteme="+t.getDisque_systeme()+"&Boitier="+t.getBoitier()+"&StockageSupp="+t.getStockage_supp();
     req.setUrl(url);
// Insertion de l'URL de notre demande de connexion
        // req.addArgument("category", t.getCategory());
       //req.addArgument("type", t.getType()+"");
          //      req.addArgument("description", t.getDescription());
                     

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                   resultCode = req.getResponseCode();
                req.removeResponseListener(this);
                 
            }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
        return resultCode;
    }
    
    
   
    
     public ArrayList<montage> getAllmontage() {
        req.setUrl(Statics.BASE_URL + "/affichemobilemon");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    montages = new ArrayList<>();
                    Map<String, Object> tasksListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

                    for (Map<String, Object> obj : list) {
                        montage montage;
                        montage = new montage(
                               (int) Float.parseFloat(obj.get("idmontage").toString()),
                                (String) obj.get("Processeur"),
                                (String) obj.get("CarteGraphique"),
                                (String) obj.get("CarteMere"),
                                (String) obj.get("DisqueSysteme"),
                                (String) obj.get("Boitier"),
                                (String) obj.get("StockageSupp")
                                
                                //  Float.parseFloat(obj.get("pourcentage_like").toString())
                        );
                        
                        montages.add(montage);
                    }

                } catch (IOException ex) {
                    System.out.println("montage vide");
                }

                req.removeResponseListener(this);
            }
        });
        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {

        }
        return montages;
    }
     public int modifierMontage(montage t) {
 String url = Statics.BASE_URL + "/updatemontage?idmontage="+t.getIdmontage()+"&Processeur="+t.getProcesseur()+"&CarteGraphique="+t.getCarte_graphique()+"&CarteMere="+t.getCarte_mere()+"&DisqueSysteme="+t.getDisque_systeme()+"&Boitier="+t.getBoitier()+"&StockageSupp="+t.getStockage_supp();
     req.setUrl(url);       
     //req.addArgument("id", String.valueOf(t.getId()));
        //req.addArgument("category", String.valueOf(t.getCategory()));
         //req.addArgument("type", t.getType());
       // req.addArgument("description", t.getDescription());
        // req.addArgument("Date reservation", t.getReserver());
          
       
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

    public int supprimerMontage(montage montage) {
    req.setUrl(Statics.BASE_URL + "/Deletemobilem?idmontage="+montage.getIdmontage());
                       System.out.println(montage.getIdmontage());

        req.addArgument("id", String.valueOf(montage.getIdmontage()));
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
