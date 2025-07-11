/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.bandocongnghe.dao;

import java.util.List;
import poly.bandocongnghe.entity.Inventory;

/**
 *
 * @author lam
 */
public interface InventoryDAO extends CrudDAO<Inventory, String>{
    public List<Inventory> findByProductId(String productId);
}
