/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.bandocongnghe.entity;

import java.sql.Date;

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
public class User_detail {
    private int  Userdetail;
private String   Fullname ;
private String   Phone ;
private String   Gender ;
private String   Address ;
 private String  username;
 private Date  Dob ;
private String   Photo; 
}
