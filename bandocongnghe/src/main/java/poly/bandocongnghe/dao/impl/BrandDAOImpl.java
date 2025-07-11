package poly.bandocongnghe.dao.impl;

import java.util.List;
import poly.bandocongnghe.dao.BrandDAO;
import poly.bandocongnghe.entity.Brand;
import poly.bandocongnghe.util.XJdbc;
import poly.bandocongnghe.util.XQuery;
import poly.bandocongnghe.util.XDialog;

public class BrandDAOImpl implements BrandDAO {

    String insertSql = "INSERT INTO Brands (Id, Name, Origin) VALUES (?, ?, ?)";
    String updateSql = "UPDATE Brands SET Name = ?, Origin = ? WHERE Id = ?";
    String deleteSql = "DELETE FROM Brands WHERE Id = ?";
    String findAllSql = "SELECT * FROM Brands";
    String findByIdSql = "SELECT * FROM Brands WHERE Id = ?";

    @Override
    public Brand create(Brand entity) {
        Object[] values = {
            entity.getId(),
            entity.getName(),
            entity.getOrigin()
        };
        XJdbc.executeUpdate(insertSql, values);
        return entity;
    }

    @Override
    public void update(Brand entity) {
        Object[] values = {
            entity.getName(),
            entity.getOrigin(),
            entity.getId()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String id) {
        try {
            XJdbc.executeUpdate(deleteSql, id);
        } catch (Exception e) {
            XDialog.alert("Không thể xóa thương hiệu này. Có thể đang được sử dụng bởi sản phẩm!");
        }
    }

    @Override
    public List<Brand> findAll() {
        return XQuery.getBeanList(Brand.class, findAllSql);
    }

    @Override
    public Brand findById(String id) {
        return XQuery.getSingleBean(Brand.class, findByIdSql, id);
    }
}
