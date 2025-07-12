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
public class Cart_item {
  private int CartId ;
   private int Status  ;
/*
0	Đã thêm vào giỏ  chưa chọn thanh toán
1	Đã chọn để thanh toán
2	Đã thanh toán thành công
3	Đã huỷ (người dùng bỏ qua hoặc hệ thống tự huỷ nếu quá hạn) */
 

private String Product_id ;
 private int   Quantity   ;
 private BigDecimal UnitPrice ;
 
 
 private BigDecimal sale_price;
 private String  name;
}
