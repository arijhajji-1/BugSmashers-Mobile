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
import entities.Location;
import utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Arij Hajji
 */
public class ServiceLocation {
public int resultCode;

    public ArrayList<Location> tasks;
    public ArrayList<Location> Locations;
    public static ServiceLocation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceLocation() {
         req = new ConnectionRequest();
    }

    public static ServiceLocation getInstance() {
        if (instance == null) {
            instance = new ServiceLocation();
        }
        return instance;
    }

    public boolean addLocation(Location t) {
        System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "/location/location/add3?dateDEB=" + t.getDb() + "&dateFin=" + t.getDf() + "&TotalL=" + t.getTotall();
    
       req.setUrl(url);
       
      
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

 
    public ArrayList<Location>  getAllLocations() {
        req.setUrl(Statics.BASE_URL + "/location/location/liste2");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    Locations = new ArrayList<>();
                    Map<String, Object> tasksListJson = new JSONParser().parseJSON(new CharArrayReader(
                            new String(req.getResponseData()).toCharArray()
                    ));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

                    for (Map<String, Object> obj : list) {
                        Location Location;
                        Location = new Location(
                                (int) Float.parseFloat(obj.get("id").toString()),
                                (int) Float.parseFloat(obj.get("TotalL").toString()),
                                
                                (String) obj.get("dateFin"),
                                (String) obj.get("dateDEB")
                                
                                
                                //  Float.parseFloat(obj.get("pourcentage_like").toString())
                        );
                        
                        Locations.add(Location);
                    }

                } catch (IOException ex) {
                    System.out.println("location vide");
                }

                req.removeResponseListener(this);
            }
        });
        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {

        }
        return Locations;
    }

public boolean modiflocation(Location t,int id ) {
    
             String url = Statics.BASE_URL + "/location/location/updatelocation/"+id+"?dateDEB=" + t.getDb() + "&dateFin=" + t.getDf() + "&TotalL=" + t.getTotall();
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
 

public int supprimerLocation(Location Location) {
    req.setUrl(Statics.BASE_URL + "/location/location/deletelocation");
        req.addArgument("id", String.valueOf(Location.getId()));
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
