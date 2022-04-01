/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import entities.reparation;
import services.ServiceReparation;

/**
 *
 * @author Arij Hajji
 */
public class reparationIndex extends Form{
     Form current;

   public reparationIndex(Form previous) {
       
        current=this; 
        setTitle("Menu");
        setLayout(BoxLayout.y());

    }
   
}
