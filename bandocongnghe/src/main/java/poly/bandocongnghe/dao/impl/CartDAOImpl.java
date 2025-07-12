package poly.bandocongnghe.dao.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import poly.bandocongnghe.dao.CartDAO;
import poly.bandocongnghe.entity.Cart;
import poly.bandocongnghe.util.XJdbc;
import poly.bandocongnghe.util.XQuery;

public class CartDAOImpl implements CartDAO {

    private  String insertSql = "INSERT INTO Carts (Username, Createat, Status) VALUES (?, ?, ?)";
    private  String updateStatusSql = "UPDATE Carts SET Status=? WHERE Id=?";
    private  String findByUsernameSql = "SELECT * FROM Carts WHERE Username=? AND Status=0";
    private  String findByIdSql = "SELECT * FROM Carts WHERE Id=?";
    private  String findAllSql = "SELECT * FROM Carts";
private String findidmoi="SELECT  * FROM Carts where ;";
        

//public Cart find_id_Status(){
//return  XQuery.getSingleBean(Cart.class, findidmoi);
//}
    @Override
    public Cart create(Cart cart) {
        Timestamp createAtTs = new Timestamp(cart.getCreateat().getTime());
        XJdbc.executeUpdate(insertSql,
                cart.getUsername(),
                cart.getCreateat(),
//                cart.getStatus()
                0
        );
        return cart;
    }

    @Override
    public void updateStatus(int cartId, int status) {
        XJdbc.executeUpdate(updateStatusSql, status, cartId);
    }

    @Override
    public Cart findActiveCartByUsername(String username) {
        return XQuery.getSingleBean(Cart.class, findByUsernameSql, username);
    }

    @Override
    public Cart findById(int id) {
        return XQuery.getSingleBean(Cart.class, findByIdSql, id);
    }

    @Override
    public List<Cart> findAll() {
        return XQuery.getBeanList(Cart.class, findAllSql);
    }

    @Override
    public void update(Cart entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Cart findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  
}
