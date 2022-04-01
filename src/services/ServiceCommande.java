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
import entities.Commande;
import utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Arij Hajji
 */
public class ServiceCommande {
public int resultCode;

    public ArrayList<Commande> tasks;
    
    public static ServiceCommande instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCommande() {
         req = new ConnectionRequest();
    }

    public static ServiceCommande getInstance() {
        if (instance == null) {
            instance = new ServiceCommande();
        }
        return instance;
    }

    public boolean addCommande(Commande t) {
        System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "/commande/commande/add2?nom=" + t.getNom() + "&prenom=" + t.getPrenom() + "&paiment=" + t.getPaiment() + "&adresse=" + t.getAdresse() + "&telephone=" + t.getTelephone();
    
       req.setUrl(url);
       
       req.addArgument("nom", t.getNom());
       req.addArgument("prenom", t.getPrenom());
       req.addArgument("paiment", t.getPaiment());
       req.addArgument("adresse", t.getAdresse());
      System.out.println(t);
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

    public ArrayList<Commande> parseCommandes(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> CommandesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)CommandesListJson.get("root");
System.out.println("ttttttt"+list);
            for(Map<String,Object> obj : list){
                Commande t = new Commande();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                float telephone = Float.parseFloat(obj.get("Telephone").toString());
                t.setTelephone((int)telephone);

                
                if (obj.get("nom")==null)
              t.setNom("null");
              
                else
                    t.setNom(obj.get("nom").toString());
                tasks.add(t);
if (obj.get("prenom")==null)
              t.setPrenom("null");
              
                else
                    t.setPrenom(obj.get("prenom").toString());
                tasks.add(t);
if (obj.get("paiment")==null)
              t.setPaiment("null");
              
                else
                    t.setPaiment(obj.get("paiment").toString());
                tasks.add(t);
if (obj.get("adresse")==null)
              t.setAdresse("null");
              
                else
                    t.setAdresse(obj.get("adresse").toString());
                tasks.add(t);



            }

            
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Commande> getAllCommandes(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"/commande/commande/liste";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseCommandes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
 public boolean modifcommande(Commande t,int id ) {
    
             String url = Statics.BASE_URL + "/commande/commande/updateCommande/"+id+"?nom=" + t.getNom() + "&prenom=" + t.getPrenom() + "&paiment=" + t.getPaiment() + "&adresse=" + t.getAdresse() + "&telephone=" + t.getTelephone();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

public int supprimerCommande(Commande Commande) {
    req.setUrl(Statics.BASE_URL + "/commande/commande/deleteCommande");
        req.addArgument("id", String.valueOf(Commande.getId()));
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




