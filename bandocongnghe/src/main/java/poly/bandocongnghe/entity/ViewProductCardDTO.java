/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.bandocongnghe.entity;

/**
 *
 * @author lam
 */
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewProductCardDTO {
    private String Id;
    private String Name;
    private String Icon;
   private BigDecimal  salePrice;
private BigDecimal  marketPrice;
private int discount;
private String categoryName;
private String brandName;

}