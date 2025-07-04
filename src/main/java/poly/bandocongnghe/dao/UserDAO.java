/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.bandocongnghe.dao;

import poly.bandocongnghe.entity.User;

/**
 *
 * @author lam
 */
public interface UserDAO extends CrudDAO<User, String>{
     public void updatepass(User entity);
     public void updateotp(User entity);
      
}
