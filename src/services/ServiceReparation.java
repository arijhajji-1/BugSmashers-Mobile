/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.reparation ;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics ;


/**
 *
 * @author Arij Hajji
 */
public class ServiceReparation {
        public int resultCode;
  public ArrayList<reparation> reparations;
    public static ServiceReparation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReparation() {
         req = new ConnectionRequest();
    }

    public static ServiceReparation getInstance() {
        if (instance == null) {
            instance = new ServiceReparation();
        }
        return instance;
    }
    public int AddReparation(reparation x){
       
        System.out.println("********");
     String url = Statics.BASE_URL + "/addreparation?Category="+x.getCategory()+"&Type="+x.getType()+"&Description="+x.getDescription()+"&reserver="+x.getReserver()+"&iduser="+String.valueOf(MyApplication.getSession().getId())+"&tel="+String.valueOf(MyApplication.getSession().getTel())+"&email="+String.valueOf(MyApplication.getSession().getEmail());
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
    
    
    public ArrayList<reparation> getAllreparationsP() {
        req.setUrl(Statics.BASE_URL + "/affichemobilerep");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    reparations = new ArrayList<>();
                    Map<String, Object> tasksListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

                    for (Map<String, Object> obj : list) {
                        reparation reparation;
                        reparation = new reparation(
                                (int) Float.parseFloat(obj.get("id").toString()),
                                (String) obj.get("category"),
                                (String) obj.get("type"),
                                (String) obj.get("description"),
                                (String) obj.get("Reserver")
                                
                                //  Float.parseFloat(obj.get("pourcentage_like").toString())
                        );
                        
                        reparations.add(reparation);
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
        return reparations;
    }
    public ArrayList<reparation> recherche(String searched){
      
         req.setUrl(Statics.BASE_URL + "/recherchemobilerep");
                 req.addArgument("searchbar",searched);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    reparations = new ArrayList<>();
                    Map<String, Object> tasksListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

                    for (Map<String, Object> obj : list) {
                        reparation reparation;
                        reparation = new reparation(
                                (int) Float.parseFloat(obj.get("id").toString()),
                                (String) obj.get("category"),
                                (String) obj.get("type"),
                                (String) obj.get("description"),
                                (String) obj.get("Reserver")
                                
                                //  Float.parseFloat(obj.get("pourcentage_like").toString())
                        );
                        System.out.println(searched);

                        reparations.add(reparation);
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
        return reparations;
    }
     public int modifierReparation(reparation t) {
 String url = Statics.BASE_URL + "/updatereparation?id="+t.getId()+"&Category="+t.getCategory()+"&Type="+t.getType()+"&Description="+t.getDescription()+"&reserver="+t.getReserver();
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

    public int supprimerReparation(reparation reparation) {
    req.setUrl(Statics.BASE_URL + "/deletereparations");
        req.addArgument("id", String.valueOf(reparation.getId()));
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
