package poly.bandocongnghe.dao.impl;

import java.util.List;
import poly.bandocongnghe.dao.InventoryDAO;
import poly.bandocongnghe.entity.Inventory;
import poly.bandocongnghe.util.XJdbc;
import poly.bandocongnghe.util.XQuery;
import poly.bandocongnghe.util.XDialog;

public class InventoryDAOImpl implements InventoryDAO {

    String insertSql = "INSERT INTO Inventory (Product_id, Quantity, Unit, LastUpdated) VALUES (?, ?, ?, ?)";
    String updateSql = "UPDATE Inventory SET Product_id=?, Quantity=?, Unit=?, LastUpdated=? WHERE Id=?";
    String deleteSql = "DELETE FROM Inventory WHERE Id=?";
    String findAllSql = "SELECT * FROM Inventory";
    String findByIdSql = "SELECT * FROM Inventory WHERE Id=?";
    String findByProductSql = "SELECT * FROM Inventory WHERE Product_id=?";

    @Override
    public Inventory create(Inventory entity) {
        Object[] values = {
            entity.getProduct_id(),
            entity.getQuantity(),
            entity.getUnit(),
            entity.getLastUpdated()
        };
        XJdbc.executeUpdate(insertSql, values);
        return entity;
    }

    @Override
    public void update(Inventory entity) {
        Object[] values = {
            entity.getProduct_id(),
            entity.getQuantity(),
            entity.getUnit(),
            entity.getLastUpdated(),
            entity.getId()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

   

    @Override
    public List<Inventory> findAll() {
        return XQuery.getBeanList(Inventory.class, findAllSql);
    }

    

    @Override
    public Inventory findByProductId(String productId) {
        return XQuery.getSingleBean(Inventory.class, findByProductSql, productId);
    }

    @Override
    public void deleteById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Inventory findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
