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
import entities.Category;
import utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Arij Hajji
 */
public class ServiceCategory {
public ArrayList<Category> categories;
 public static ServiceCategory instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCategory() {
         req = new ConnectionRequest();
    }

    public static ServiceCategory getInstance() {
        if (instance == null) {
            instance = new ServiceCategory();
        }
        return instance;
    }
public ArrayList<Category> parseCategories(String jsonText){
        try {
            categories = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> CategoriesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)CategoriesListJson.get("root");
            for(Map<String,Object> obj : list){
                Category t = new Category();
                t.setId(Math.round(Float.parseFloat((obj.get("id").toString()))));
                t.setLabel(obj.get("label").toString());
                categories.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return categories;
    }
public boolean addCategory(Category t) {
        System.out.println(t);
        System.out.println("********");
      String url = Statics.BASE_URL + "/addCatJSON?label=" + t.getLabel();
    
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
public boolean modifyCategory(Category t) {
        System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "/ModifyCatJSON"+t.getId()+"?label="+ t.getLabel();
    
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
public boolean deleteCategory(int id) {
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "/deleteC/"+id;
    
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

public ArrayList<Category> getAllCategories(){
        //String url = Statics.BASE_URL+"/Students/";
        String url = Statics.BASE_URL +"/listeC";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categories = parseCategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    }
}

