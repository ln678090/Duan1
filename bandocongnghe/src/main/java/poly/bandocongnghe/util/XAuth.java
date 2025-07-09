/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.bandocongnghe.util;

//import poly.karaoke.entity.User;

import poly.bandocongnghe.entity.User;


/**
 *
 * @author Huyen
 */
public class XAuth {
    //Thêm lớp tiện ích Xauth chứa biến static để duy trì 
    //và chia sẻ thông tin user sau khi đăng nhập 
    //giữa các chức năng trong ứng dụng 
    
      public static User user = User.builder() 
            .Username("user1@gmail.com") 
            .Password("123") 
            .Roles(0) 
            .Status(0) 
            .Otp("")
            .Email("")
            .build(); // biến user này sẽ được thay thế sau khi đăng nhập
}
