package poly.bandocongnghe.dao.impl;

import java.util.List;
import poly.bandocongnghe.dao.ProductDAO;
import poly.bandocongnghe.entity.Product;
import poly.bandocongnghe.util.XJdbc;
import poly.bandocongnghe.util.XQuery;
import poly.bandocongnghe.util.XDialog;

public class ProductDAOImpl implements ProductDAO {

    String createSql = "INSERT INTO Products (Id, Name, import_price, market_price, sale_price, discount, category_id, brand_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Products SET Name=?, import_price=?, market_price=?, sale_price=?, discount=?, category_id=?, brand_id=? WHERE Id=?";
    String deleteSql = "DELETE FROM Products WHERE Id=?";
    String findAllSql = "SELECT * FROM Products";
    String findByIdSql = "SELECT * FROM Products WHERE Id=?";
    String findBynameSql = "SELECT * FROM Products WHERE Id=?";

    @Override
    public Product create(Product entity) {
        Object[] values = {
            entity.getId(),
            entity.getName(),
            entity.getImport_price(),
            entity.getMarket_price(),
            entity.getSale_price(),
            entity.getDiscount(), 
            entity.getCategory_id(),
            entity.getBrand_id()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(Product entity) {
        Object[] values = {
            entity.getName(),
            entity.getImport_price(),
            entity.getMarket_price(),
            entity.getSale_price(),
            entity.getDiscount(),
            entity.getCategory_id(),
            entity.getBrand_id(),
            entity.getId()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String id) {
        try {
            XJdbc.executeUpdate(deleteSql, id);
        } catch (Exception e) {
            XDialog.alert("Không thể xóa sản phẩm. Vui lòng kiểm tra hóa đơn hoặc bảng liên quan!");
        }
    }

    @Override
    public List<Product> findAll() {
        return XQuery.getBeanList(Product.class, findAllSql);
    }

    @Override
    public Product findById(String id) {
        return XQuery.getSingleBean(Product.class, findByIdSql, id);
    }
}
