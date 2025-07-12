package poly.bandocongnghe.dao.impl;

import java.util.List;
import poly.bandocongnghe.dao.Product_Interested_UserDAO;

import poly.bandocongnghe.entity.Product_Interested_User;
import poly.bandocongnghe.util.XDialog;
import poly.bandocongnghe.util.XJdbc;
import poly.bandocongnghe.util.XQuery;

public class Product_Interested_UserDAOImpl implements Product_Interested_UserDAO {

    String insertSql = "INSERT INTO Product_Interested_Users (Username, Product_id, Status) VALUES (?, ?, ?)";
    String updateStatusSql = "UPDATE Product_Interested_Users SET Status=? WHERE Username=? AND Product_id=?";
    String findByUserProductSql = "SELECT * FROM Product_Interested_Users WHERE Username=? AND Product_id=?";
    String findByProductSql = "SELECT * FROM Product_Interested_Users WHERE Product_id=?";
    String findAllSql = "SELECT * FROM Product_Interested_Users";

    @Override
    public void kiemtra(String username, String Product) {
        boolean exists = XQuery.getSingleBean(Product_Interested_User.class, findByUserProductSql, username, Product) != null;
        if (!exists) {
            XJdbc.executeUpdate(insertSql, username, Product, 0); 
            XDialog.alert("Đăng ký thành công");
        }else{XDialog.alert("Bạn đã đăng ký rồi");}
    }

    @Override
    public void updateStatus(String username, String productId, int status) {
        XJdbc.executeUpdate(updateStatusSql, status, username, productId);
    }

    @Override
    public Product_Interested_User find(String username, String productId) {
        return XQuery.getSingleBean(Product_Interested_User.class, findByUserProductSql, username, productId);
    }

    @Override
    public List<Product_Interested_User> findByProduct(String productId) {
        return XQuery.getBeanList(Product_Interested_User.class, findByProductSql, productId);
    }

    @Override
    public List<Product_Interested_User> findAll() {
        return XQuery.getBeanList(Product_Interested_User.class, findAllSql);
    }

    @Override
    public Product_Interested_User create(Product_Interested_User entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Product_Interested_User entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Product_Interested_User findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
