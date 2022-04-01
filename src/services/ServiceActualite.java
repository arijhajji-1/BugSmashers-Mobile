/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.actualite;
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

import utils.Statics;


/**
 *
 * @author Arij Hajji
 */
public class ServiceActualite {
    public int resultCode;
    public ArrayList<actualite> actualites;
    public static ServiceActualite instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceActualite() {
        req = new ConnectionRequest();
    }

    public static ServiceActualite getInstance() {
        if (instance == null) {
            instance = new ServiceActualite();
        }
        return instance;
    }
    public int AddActualite(actualite t){


        System.out.println("********");
        String url = Statics.BASE_URL + "/ajoutActualite?titre="+t.getTitre()+"&date="+t.getDate()+"&description="+t.getDescription()+"&imageName="+t.getImageName();
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




    public ArrayList<actualite> getAllActualite() {
        req.setUrl(Statics.BASE_URL + "/listeActualite");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    actualites = new ArrayList<>();
                    Map<String, Object> tasksListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

                    for (Map<String, Object> obj : list) {
                        actualite actualite;
                        actualite = new actualite(
                                (int) Float.parseFloat(obj.get("id").toString()),
                                (String) obj.get("titre"),
                                (String) obj.get("date"),
                                (String) obj.get("description"),
                                (String) obj.get("imageName")


                                //  Float.parseFloat(obj.get("pourcentage_like").toString())
                        );

                        actualites.add(actualite);
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
        return actualites;
    }
    public int modifierActualite(actualite t) {
        String url = Statics.BASE_URL + "/updateAcualite?id="+t.getId()+"&titre="+t.getTitre()+"&date="+t.getDate()+"&description="+t.getDescription()+"&imageName="+t.getImageName();
        req.setUrl(url);
        req.addArgument("id", String.valueOf(t.getId()));
        req.addArgument("titre", String.valueOf(t.getTitre()));
        req.addArgument("date", t.getDate());
        req.addArgument("description", t.getDescription());


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

    public int supprimerActualite(actualite actualite) {
        req.setUrl(Statics.BASE_URL + "/deleteActualite");
        System.out.println(actualite.getId());

        req.addArgument("id", String.valueOf(actualite.getId()));
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
