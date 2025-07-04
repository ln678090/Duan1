/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.bandocongnghe.dao.impl;

import java.util.List;
import poly.bandocongnghe.dao.UserDAO;
import poly.bandocongnghe.entity.User;
import poly.bandocongnghe.util.XDialog;
import poly.bandocongnghe.util.XJdbc;
import poly.bandocongnghe.util.XQuery;

/**
 *
 * @author lam
 */
public class UserDAOImpl implements UserDAO{
       String createSql = "INSERT INTO [Users](Username, Password, Roles, Email, Status) VALUES (?, ?, ?, ?,  ?)";
    String updateSql = "UPDATE  [Users] set Password=?, Roles=?,  Email=?, Status=? where Username=? ";
    String deleteSql = "delete from [Users] where Username=?";
    String findAllSql = "select*from [Users]";
    String findByIdSql = "select *from [Users] where Username=?";

    @Override
    public User create(User entity) {
        Object[] values = {
            entity.getUsername(),
            entity.getPassword(),
            entity.getRoles(),
            entity.getEmail(),
            entity.getStatus(),
            
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    

    @Override
    public void update(User entity) {
        Object[] values = {
             
            entity.getPassword(),
           entity.getRoles(),
            entity.getEmail(),
            entity.getStatus(),
           entity.getUsername(),

          
        };
        XJdbc.executeUpdate(updateSql, values);

    }

    @Override
    public void deleteById(String Username) {
        try {
            XJdbc.executeUpdate(deleteSql, Username);
        } catch (Exception e) {
            XDialog.alert("xoa bang bill truoc");
        }
    }

    @Override
    public List< User> findAll() {
        return XQuery.getBeanList(User.class, findAllSql);
    }

    @Override
    public User findById(String Username) {
        return XQuery.getSingleBean(User.class, findByIdSql, Username);
    }

   String updatepassSql="UPDATE  [Users] set Password=? where Username=? ";;
    public void updatepass(User entity) {
        Object[] values = {
            entity.getPassword(),
            entity.getUsername()
        };
        XJdbc.executeUpdate(updatepassSql, values);

    }

    @Override
    public void updateotp(User entity) {
        Object[] values = {
            entity.getOtp(),
            entity.getUsername()
        };
        XJdbc.executeUpdate("UPDATE  [Users] set otp=? where Username=? ", values);
    }

  
}
