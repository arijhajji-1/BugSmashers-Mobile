/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import entities.evenement;
import utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Arij Hajji
 */
public class ServiceEvent {

    public int resultCode;
    public ArrayList<evenement> evenements;
    public static ServiceEvent instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEvent() {
        req = new ConnectionRequest();
    }

    public static ServiceEvent getInstance() {
        if (instance == null) {
            instance = new ServiceEvent();
        }
        return instance;
    }

    public int AddEvenement(evenement event){


        System.out.println("********");
        String url = Statics.BASE_URL + "/ajoutEvenement?nom="+event.getNom()+"&date="+event.getDate()+"&description="+event.getDescription()+"&imageName="+event.getImageName();
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

    public ArrayList<evenement> getAllEvenement() {
        req.setUrl(Statics.BASE_URL + "/listeEvenement");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    evenements = new ArrayList<>();
                    Map<String, Object> tasksListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

                    for (Map<String, Object> obj : list) {
                        evenement evenement;
                        evenement = new evenement(
                                (int) Float.parseFloat(obj.get("id").toString()),
                                (String) obj.get("nom"),
                                (String) obj.get("date"),
                                (String) obj.get("description"),
                                (String) obj.get("ImageName")


                                //  Float.parseFloat(obj.get("pourcentage_like").toString())
                        );

                        evenements.add(evenement);
                    }

                } catch (IOException ex) {
                    System.out.println("evenement vide");
                }

                req.removeResponseListener(this);
            }
        });
        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {

        }
        return evenements;
    }

    public int modifierEvenement(evenement evenement) {
        String url = Statics.BASE_URL + "/updateEvenement"+"?id="+evenement.getId()+"&nom="+evenement.getNom()+"&date="+evenement.getDate()+"&description="+evenement.getDescription()+"&imageName="+evenement.getImageName();
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


    public int supprimerEvenement(evenement evenement) {
        req.setUrl(Statics.BASE_URL + "/deleteEvenement?id="+evenement.getId());
        System.out.println(evenement.getId());

        req.addArgument("id", String.valueOf(evenement.getId()));
        req.addArgument("id", String.valueOf(evenement.getId()));
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
