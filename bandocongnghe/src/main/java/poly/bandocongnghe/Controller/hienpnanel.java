/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.bandocongnghe.Controller;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import poly.bandocongnghe.view.ViewProductJPanel;
import poly.bandocongnghe.view.viewCartJpanel;


/**
 *
 * @author lam
 */
public interface hienpnanel {
    default void showviewproduct(JPanel panel){
    panel.removeAll();
        ViewProductJPanel view=new ViewProductJPanel();
        panel.add(view,BorderLayout.CENTER);
        view.setVisible(true);
         panel.revalidate();
        panel.repaint();
    }
    default  void viewCart(JPanel panel){
      panel.removeAll();
        viewCartJpanel view=new viewCartJpanel();
        panel.add(view,BorderLayout.CENTER);
        view.setVisible(true);
         panel.revalidate();
        panel.repaint();
    }
    
}
