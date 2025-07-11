package poly.bandocongnghe.dao.impl;

import java.util.List;
import poly.bandocongnghe.dao.CategorieDAO;
import poly.bandocongnghe.entity.Categorie;
import poly.bandocongnghe.util.XJdbc;
import poly.bandocongnghe.util.XQuery;
import poly.bandocongnghe.util.XDialog;

public class CategorieDAOImpl implements CategorieDAO {

    String insertSql = "INSERT INTO Categories (Id, Name) VALUES (?, ?)";
    String updateSql = "UPDATE Categories SET Name = ? WHERE Id = ?";
    String deleteSql = "DELETE FROM Categories WHERE Id = ?";
    String findAllSql = "SELECT * FROM Categories";
    String findByIdSql = "SELECT * FROM Categories WHERE Id = ?";

    @Override
    public Categorie create(Categorie entity) {
        Object[] values = {
            entity.getId(),
            entity.getName()
        };
        XJdbc.executeUpdate(insertSql, values);
        return entity;
    }

    @Override
    public void update(Categorie entity) {
        Object[] values = {
            entity.getName(),
            entity.getId()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String id) {
        try {
            XJdbc.executeUpdate(deleteSql, id);
        } catch (Exception e) {
            XDialog.alert("Không thể xóa loại sản phẩm. Kiểm tra xem có sản phẩm đang dùng loại này!");
        }
    }

    @Override
    public List<Categorie> findAll() {
        return XQuery.getBeanList(Categorie.class, findAllSql);
    }

    @Override
    public Categorie findById(String id) {
        return XQuery.getSingleBean(Categorie.class, findByIdSql, id);
    }
}
