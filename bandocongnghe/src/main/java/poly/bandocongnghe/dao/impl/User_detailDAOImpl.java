/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.bandocongnghe.dao.impl;

import java.util.List;
import poly.bandocongnghe.dao.UserDAO;
import poly.bandocongnghe.dao.User_detailDAO;
import poly.bandocongnghe.entity.User_detail;
import poly.bandocongnghe.util.XDialog;
import poly.bandocongnghe.util.XJdbc;
import poly.bandocongnghe.util.XQuery;

/**
 *
 * @author lam
 */
public class User_detailDAOImpl implements User_detailDAO{

     String createSql = "INSERT INTO [User_details](Fullname, Phone, Gender, Address, username,Dob,Photo ) VALUES (?, ?, ?, ?,?,?,  ?)";
    String updateSql = "UPDATE  [User_details] set Fullname=?, Phone=?,  Gender=?, Address=?,Dob=?,Photo=? where username=? ";
    String deleteSql = "delete from [User_details] where username=?";
    String findAllSql = "select*from [User_details]";
    String findByIdSql = "select *from [User_details] where username=?";
  
     @Override
    public User_detail create(User_detail entity) {
        Object[] values = {
            entity.getFullname(),
            entity.getPhone(),
            entity.getGender(),
            entity.getAddress(),
            entity.getUsername(),
            entity.getDob(),
            entity.getPhoto()
            
        };
         XJdbc.executeUpdate(createSql, values);
        return entity;
    }
    

    @Override
    public void update(User_detail entity) {
        Object[] values = {
             
           entity.getFullname(),
            entity.getPhone(),
            entity.getGender(),
            entity.getAddress(),
           
            entity.getDob(),
            entity.getPhoto(),
             entity.getUsername()

          
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
    public List< User_detail> findAll() {
        return XQuery.getBeanList(User_detail.class, findAllSql);
    }

    @Override
    public User_detail findById(String Username) {
        return XQuery.getSingleBean(User_detail.class, findByIdSql, Username);
    }
}
