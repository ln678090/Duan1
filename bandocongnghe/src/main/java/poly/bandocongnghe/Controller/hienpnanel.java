/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.bandocongnghe.Controller;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import poly.bandocongnghe.view.ViewProductJPanel;


/**
 *
 * @author lam
 */
public interface hienpnanel {
    default void showviewproduct(JPanel panel){
    panel.removeAll();
        ViewProductJPanel ViewProductJPanel=new ViewProductJPanel();
        panel.add(ViewProductJPanel,BorderLayout.CENTER);
        ViewProductJPanel.setVisible(true);
         panel.revalidate();
        panel.repaint();
    }
    
}
