/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.bandocongnghe.dao;

import poly.bandocongnghe.entity.Cart;

/**
 *
 * @author lam
 */
public interface CartDAO extends CrudDAO<Cart, String>{
    void updateStatus(int cartId, int status);
    Cart findActiveCartByUsername(String username);
    Cart findById(int id) ;
//    Cart find_id_moi();
}
