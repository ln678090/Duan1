/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package poly.bandocongnghe.dao;

import poly.bandocongnghe.entity.Product_detail;

/**
 *
 * @author lam
 */
public interface Product_detailDAO extends CrudDAO<Product_detail, String>{
    public void deleteByProductId(String productId);
    public Product_detail findByProductId(String productId);
}
