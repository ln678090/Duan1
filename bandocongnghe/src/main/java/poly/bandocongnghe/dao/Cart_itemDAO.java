/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.bandocongnghe.dao;

import java.util.List;
import poly.bandocongnghe.entity.Cart_item;

/**
 *
 * @author lam
 */
public interface Cart_itemDAO extends CrudDAO<Cart_item, String>{
    void updateStatus(int cartId, String productId, int status) ;
    List<Cart_item> findByCartId(int cartId);
     void deleteItem(int cartId, String productId) ;
     List<Cart_item> findSelectedItems(int cartId);
     void themQuantity(int cartId, String productId, int Quantity);
     List<Cart_item>hientblecaritem();
     void updateQuantity(int cartId, String productId, int Quantity);
}
