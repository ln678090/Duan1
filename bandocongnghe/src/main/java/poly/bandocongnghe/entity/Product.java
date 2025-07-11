/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.bandocongnghe.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author lam
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Product {
    private String id,Name;
   private BigDecimal import_price,
            market_price,
            sale_price;
   private int discount;
 private String category_id,brand_id;
}
