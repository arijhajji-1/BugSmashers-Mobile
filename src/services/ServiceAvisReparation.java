/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.MyApplication;
import entities.AvisReparation;
import entities.reparation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Arij Hajji
 */
public class ServiceAvisReparation {
     public int resultCode;
  public ArrayList<AvisReparation> AvisReparations;
    public static ServiceAvisReparation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceAvisReparation() {
         req = new ConnectionRequest();
    }

    public static ServiceAvisReparation getInstance() {
        if (instance == null) {
            instance = new ServiceAvisReparation();
        }
        return instance;
    }
     public int AddAvisReparation(int y,AvisReparation t){
      
     String url = Statics.BASE_URL + "/addavisreparation?idreparation="+y+"&Description="+t.getDescription()+"&Email="+String.valueOf(MyApplication.getSession().getEmail())+"&nom="+String.valueOf(MyApplication.getSession().getNom())+"&UserId="+String.valueOf(MyApplication.getSession().getId());
     req.setUrl(url);// Insertion de l'URL de notre demande de connexion
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
    
    
    public ArrayList<AvisReparation> getAllAvisreparationsP() {
        req.setUrl(Statics.BASE_URL + "/displayEn");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    AvisReparations = new ArrayList<>();
                    Map<String, Object> tasksListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

                    for (Map<String, Object> obj : list) {
                        AvisReparation AvisReparation;
                        AvisReparation = new AvisReparation(
                                (int) Float.parseFloat(obj.get("id").toString()),
                                
                                (int) Float.parseFloat(obj.get("UserId").toString()),

                                (int) Float.parseFloat(obj.get("Idreparation").toString()),
                                (String) obj.get("Description"),
                                (String) obj.get("Email"),
                                (String) obj.get("nom")
                             
                                
                                //  Float.parseFloat(obj.get("pourcentage_like").toString())
                        );
                        System.out.println(list);
                        AvisReparations.add(AvisReparation);
                    }

                } catch (IOException ex) {
                    System.out.println("Avisreparation vide");
                }

                req.removeResponseListener(this);
            }
        });
        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {

        }
        return AvisReparations;
    }
     public int supprimerAvisReparation(AvisReparation AvisReparation) {
    req.setUrl(Statics.BASE_URL + "/deleteAvisreparations");
        req.addArgument("id", String.valueOf(AvisReparation.getId()));
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
