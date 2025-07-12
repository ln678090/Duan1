

package poly.bandocongnghe.dao.impl;

import java.util.List;
import poly.bandocongnghe.dao.Cart_itemDAO;
import poly.bandocongnghe.entity.Cart_item;
import poly.bandocongnghe.util.XDialog;
import poly.bandocongnghe.util.XJdbc;
import poly.bandocongnghe.util.XQuery;

public class Cart_itemDAOImpl implements Cart_itemDAO {

    private  String insertSql = "INSERT INTO Cart_items (CartId, Status, Product_id, Quantity, UnitPrice) VALUES (?, ?, ?, ?, ?)";
    private  String updateStatusSql = "UPDATE Cart_items SET Status=? WHERE CartId=? AND Product_id=?";
    private  String findByCartSql = "SELECT * FROM Cart_items WHERE CartId=?";
    private  String findSelectedSql = "SELECT * FROM Cart_items WHERE CartId=? AND Status=1";
    private  String deleteSql = "delete FROM Cart_items WHERE CartId=? AND [Product_id]=?";

   private String hiemcart_item="select *   from Cart_items a \n" +
"join Products b on a.Product_id=b.Id";
   
    @Override
   public  List<Cart_item>hientblecaritem(){
   return XQuery.getBeanList(Cart_item.class, hiemcart_item);
   }
    @Override
   public void updateQuantity(int cartId, String productId, int Quantity){
    Object[] valus={Quantity, cartId, productId};
       XJdbc.executeUpdate("UPDATE Cart_items SET Quantity=? WHERE CartId=? AND Product_id=?",valus );
   }
@Override
    public void themQuantity(int cartId, String productId, int Quantity) {
        Object[] valus={Quantity, cartId, productId};
       XJdbc.executeUpdate("UPDATE Cart_items SET Quantity=Quantity+? WHERE CartId=? AND Product_id=?",valus );
       XDialog.alert("Đã thêm vào giỏ hàng");
    }
    @Override
    public void updateStatus(int cartId, String productId, int status) {
        Object[] valus={status, cartId, productId};
        
        XJdbc.executeUpdate(updateStatusSql,valus );
    }

    @Override
    public List<Cart_item> findByCartId(int cartId) {
        return XQuery.getBeanList(Cart_item.class, findByCartSql, cartId);
    }

    @Override
    public List<Cart_item> findSelectedItems(int cartId) {
        return XQuery.getBeanList(Cart_item.class, findSelectedSql, cartId);
    }

    @Override
    public void deleteItem(int cartId, String productId) {
        XJdbc.executeUpdate(deleteSql, cartId, productId);
    }

    @Override
    public Cart_item create(Cart_item item) {
  XJdbc.executeUpdate(insertSql,
            item.getCartId(),
            item.getStatus(),
            item.getProduct_id(),
            item.getQuantity(),
            item.getUnitPrice()
        );    
   XDialog.alert("Đã thêm vào giỏ hàng");
        return item;
       
    }

    @Override
    public void update(Cart_item entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cart_item> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Cart_item findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
