/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.bandocongnghe.dao;

import java.util.List;
import poly.bandocongnghe.entity.Product_Interested_User;

/**
 *
 * @author lam
 */
public interface Product_Interested_UserDAO extends CrudDAO<Product_Interested_User, String>{
     List<Product_Interested_User> findByProduct(String productId);
     Product_Interested_User find(String username, String productId);
     void updateStatus(String username, String productId, int status);
     void kiemtra(String username, String productId);
}
