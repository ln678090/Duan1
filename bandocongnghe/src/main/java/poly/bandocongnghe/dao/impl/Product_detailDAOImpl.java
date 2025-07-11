package poly.bandocongnghe.dao.impl;

import java.util.List;
import poly.bandocongnghe.dao.Product_detailDAO;
import poly.bandocongnghe.entity.Product_detail;
import poly.bandocongnghe.util.XJdbc;
import poly.bandocongnghe.util.XQuery;
import poly.bandocongnghe.util.XDialog;

public class Product_detailDAOImpl implements Product_detailDAO {

    String insertSql = "INSERT INTO Products_detail (Product_id, description, Icon) VALUES (?, ?, ?)";
    String updateSql = "UPDATE Products_detail SET description = ?, Icon = ? WHERE Product_id = ?";
    String deleteSql = "DELETE FROM Products_detail WHERE Product_id = ?";
    String findAllSql = "SELECT * FROM Products_detail";
    String findByIdSql = "SELECT * FROM Products_detail WHERE Product_id = ?";

    @Override
    public Product_detail create(Product_detail entity) {
        Object[] values = {
            entity.getProduct_id(),
            entity.getDescription(),
            entity.getIcon()
        };
        XJdbc.executeUpdate(insertSql, values);
        return entity;
    }

    @Override
    public void update(Product_detail entity) {
        Object[] values = {
            entity.getDescription(),
            entity.getIcon(),
            entity.getProduct_id()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteByProductId(String productId) {
        try {
            XJdbc.executeUpdate(deleteSql, productId);
        } catch (Exception e) {
            XDialog.alert("Không thể xóa chi tiết sản phẩm. Kiểm tra bảng liên quan trước!");
        }
    }

    @Override
    public List<Product_detail> findAll() {
        return XQuery.getBeanList(Product_detail.class, findAllSql);
    }

    @Override
    public Product_detail findByProductId(String productId) {
        return XQuery.getSingleBean(Product_detail.class, findByIdSql, productId);
    }

    @Override
    public void deleteById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Product_detail findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
