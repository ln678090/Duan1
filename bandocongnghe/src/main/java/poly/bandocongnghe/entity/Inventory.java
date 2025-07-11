/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.bandocongnghe.entity;

import java.util.Date;
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
public class Inventory {
    private int Id;
    private String Product_id;
   private int Quantity ;
   private String 
            Unit;
           private Date   LastUpdated;
}
