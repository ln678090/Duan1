/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package poly.bandocongnghe.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import poly.bandocongnghe.dao.BrandDAO;
import poly.bandocongnghe.dao.CartDAO;
import poly.bandocongnghe.dao.Cart_itemDAO;

import poly.bandocongnghe.dao.CategorieDAO;
import poly.bandocongnghe.dao.InventoryDAO;
import poly.bandocongnghe.dao.ProductDAO;
import poly.bandocongnghe.dao.Product_Interested_UserDAO;
import poly.bandocongnghe.dao.Product_detailDAO;

import poly.bandocongnghe.dao.impl.BrandDAOImpl;
import poly.bandocongnghe.dao.impl.CartDAOImpl;
import poly.bandocongnghe.dao.impl.Cart_itemDAOImpl;
import poly.bandocongnghe.dao.impl.CategorieDAOImpl;
import poly.bandocongnghe.dao.impl.InventoryDAOImpl;
import poly.bandocongnghe.dao.impl.ProductDAOImpl;
import poly.bandocongnghe.dao.impl.Product_Interested_UserDAOImpl;
import poly.bandocongnghe.dao.impl.Product_detailDAOImpl;

import poly.bandocongnghe.entity.Brand;
import poly.bandocongnghe.entity.Cart;
import poly.bandocongnghe.entity.Cart_item;
import poly.bandocongnghe.entity.Categorie;
import poly.bandocongnghe.entity.Inventory;
import poly.bandocongnghe.entity.Product;
import poly.bandocongnghe.entity.Product_detail;
import poly.bandocongnghe.entity.ViewProductCardDTO;
import poly.bandocongnghe.util.TimeRange;
import poly.bandocongnghe.util.XAuth;
import poly.bandocongnghe.util.XDate;
import poly.bandocongnghe.util.XIcon;
import poly.bandocongnghe.util.XQuery;

/**
 *
 * @author lam
 */
public class ViewProductJPanel extends javax.swing.JPanel {

    /**
     * Creates new form SanPhamJPanel
     */
    public ViewProductJPanel() {
        initComponents();
        open();
    }
    private void open(){
    
    fillCategories();
    fillBrand();
    insp();
    sukien();
    btdattruoc.setVisible(false);
    btmua.setVisible(false);btthemgio.setVisible(false);
    }
  
    CategorieDAO categoryDao = new CategorieDAOImpl();
    List<Categorie> categories = List.of();
    BrandDAO BrandDao = new BrandDAOImpl();
    List<Brand> Brands = List.of();
    ProductDAO ProductDAO=new ProductDAOImpl();
    List<Product> Products=List.of();
    Product_detailDAO  Product_detailDAO=new Product_detailDAOImpl();
    List<Product_detail>Product_details=List.of();
    InventoryDAO  inventoryDAO=new InventoryDAOImpl();
    List<Inventory>Inventorys=List.of();
    CartDAO cartDAO=new CartDAOImpl();
    Cart_itemDAO Cart_itemDAO=new Cart_itemDAOImpl();
    private String productId=null;
    private void fillCategories() {
        categories = categoryDao.findAll();
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbocategory.getModel();
        model.removeAllElements();
        cbocategory.addItem("category");
        categories.forEach((Categorie d) ->{
                model.addElement(d.getName());
        });
       
       
    }
    private void fillBrand() {
        Brands = BrandDao.findAll();
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbobrand.getModel();
        model.removeAllElements();
        cbobrand.addItem("Brand");
        Brands.forEach(d ->{
                model.addElement(d.getName());
        });
         
    }
    private void sukien(){
    cbocategory.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
           
           if(e.getStateChange()==ItemEvent.SELECTED){
        String selet=e.getItem().toString();
        loc();
        }
        }
    });
    cbobrand.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
           
           if(e.getStateChange()==ItemEvent.SELECTED){
        String selet=e.getItem().toString();
        loc();
        }
        }
    });
    }
    private Cart getfromcard(){
    Cart items=new Cart();
    items.setUsername(XAuth.user.getUsername());
        items.setStatus(0);
        items.setCreateat(new Date());
        return items;
        
    } 
      private Cart_item getfromcarditem(){
        
        
         Cart in=cartDAO.findActiveCartByUsername(XAuth.user.getUsername());
      Cart_item items=new Cart_item();
     
     
      items.setCartId(in.getId());
      items.setProduct_id(productId);
      items.setQuantity(1);
      items.setStatus(0);
      Product itemsp= ProductDAO.findById(productId);
      items.setUnitPrice(itemsp.getSale_price());
      return items;
      }
    private void themgiohang(){
         Cart in=cartDAO.findActiveCartByUsername(XAuth.user.getUsername());
         if(in==null){
         cartDAO.create(getfromcard());
        
        
        Cart_itemDAO.create(getfromcarditem());
         }else{
        int a=in.getId();
        int b=0;
        List<Cart> Carts=cartDAO.findAll();
       
        for (Cart t : Carts) {
            if(t.getStatus()==0){
            a=t.getId();
           
            b=1;
             break;
            
            }
        }
        if(b==0){
        
        cartDAO.create(getfromcard());
        }
      b=9;
         List<Cart_item> itemss=Cart_itemDAO.findByCartId(in.getId());
          for (Cart_item t : itemss) {
              if(t.getProduct_id().equals(productId)){
                  b=8;
               Cart_itemDAO.themQuantity(in.getId(), productId, 1);
               break;
              }
          }
          if(b==9){
        Cart_itemDAO.create(getfromcarditem());
          }
         }
    }
    private void hienchitiet(String ProductId){
        Product itemsp= ProductDAO.findById(ProductId);
             Product_detail  iteamdetal=Product_detailDAO.findByProductId(ProductId);
             Inventory kho= inventoryDAO.findByProductId(ProductId);
    XIcon.XIconebylam(labelimagechitiet, iteamdetal.getIcon());
    txtnamechitet.setText(itemsp.getName());
    txtmota.setText(iteamdetal.getDescription());
    txtgianiemyet.setText("Khuyễn mãi từ "+itemsp.getMarket_price()+" giảm "+itemsp.getDiscount()+"% chỉ còn:");
    txtgiaban.setText(String.valueOf(itemsp.getSale_price()+"₫"));
    txttonkho.setText("Số lượng còn :"+kho.getQuantity()+" "+kho.getUnit());
    if(kho.getQuantity()==0){btmua.setVisible(false);btthemgio.setVisible(false);btdattruoc.setVisible(true);}else{btdattruoc.setVisible(false);btmua.setVisible(true);btthemgio.setVisible(true);}
    }
    private void loc(){
         
                 
       
 //    System.out.println("trong chuong"+selected+"va"+selected1);
     
       
     String  chonloai =String.valueOf(cbocategory.getSelectedItem().toString().trim());
     String  chonhang =String.valueOf(cbobrand.getSelectedItem().toString().trim());
         
    String sql ="""
               
        
            SELECT 
        p.Id AS id,
        p.Name AS name,
        pd.Icon AS icon,
        p.sale_price AS salePrice,
        p.market_price AS marketPrice,
        p.discount AS discount,
        b.Name AS brandName,
        c.Name AS categoryName
    FROM Products p
    JOIN Products_detail pd ON p.Id = pd.Product_id
    JOIN Brands b ON p.brand_id = b.Id
    JOIN Categories c ON p.category_id = c.Id
                """;
  
   List<ViewProductCardDTO> cards = XQuery.getBeanList(ViewProductCardDTO.class, sql);
    if(!chonloai.equals("category")){
    List<ViewProductCardDTO> filtered = cards.stream()    
        .filter(p -> p.getCategoryName().toLowerCase().equalsIgnoreCase(chonloai))
        //.filter(p -> p.getBrandName().toLowerCase().equalsIgnoreCase(chonhang))
        .collect(Collectors.toList());
    panelsanpham.removeAll();
 for (ViewProductCardDTO d : filtered) {
        JPanel card =taokhung(d);
        panelsanpham.add(card);
        
    }
    }
     if (!chonhang.equals("Brand")){
    List<ViewProductCardDTO> filtered = cards.stream()    
        //.filter(p -> p.getCategoryName().toLowerCase().equalsIgnoreCase(chonloai))
        .filter(p -> p.getBrandName().toLowerCase().equalsIgnoreCase(chonhang))
        .collect(Collectors.toList());
    panelsanpham.removeAll();
 for (ViewProductCardDTO d : filtered) {
        JPanel card =taokhung(d);
        panelsanpham.add(card);
       
    }
    }
if(!chonloai.equals("category")&& !chonhang.equals("Brand")){
     List<ViewProductCardDTO> filtered = cards.stream()    
        .filter(p -> p.getCategoryName().toLowerCase().equalsIgnoreCase(chonloai))
        .filter(p -> p.getBrandName().toLowerCase().equalsIgnoreCase(chonhang))
        .collect(Collectors.toList());
    panelsanpham.removeAll();
 for (ViewProductCardDTO d : filtered) {
        JPanel card =taokhung(d);
        panelsanpham.add(card);
       
 }
    }
   
    panelsanpham.revalidate();
    panelsanpham.repaint();
    
    
    }
    
private  JPanel taokhung(ViewProductCardDTO  p){
    JPanel card = new JPanel();
    card.setLayout(new BorderLayout());
     card.setPreferredSize(new Dimension(280, 260));
     card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    card.setBackground(Color.WHITE);
    
    card.putClientProperty("productId", p.getId());
    
   
    
    card.addMouseListener(new MouseAdapter() {
        @Override
    public void mouseClicked(MouseEvent e){
    JPanel clik=(JPanel) e.getSource();
     productId =(String) clik.getClientProperty("productId");
           hienchitiet(productId);
    }
    
    });
    
    JLabel lblmage =new JLabel();
    lblmage.setHorizontalAlignment(SwingConstants.CENTER);
    //XIcon.XIconebylam(lblmage, p.getIcon());
    ImageIcon icon;
        icon = new ImageIcon(getClass().getResource("/sanpham/" + p.getIcon()));
        lblmage.setIcon(icon);

    
    JLabel lblname=new JLabel(p.getName(),JLabel.CENTER);
    lblmage.setFont(new Font("Arial",Font.BOLD,13));
    
   JLabel lblprice =new JLabel(String.format("Giá: %,d₫",p.getSalePrice().intValue()),JLabel.CENTER);
  

    lblprice.setForeground(Color.RED);
    
    JLabel lblDiscount = new JLabel("-" + p.getDiscount() + "%", JLabel.CENTER);
    lblDiscount.setForeground(new Color(0, 153, 0));
    
    JPanel bottom=new JPanel(new GridLayout(3,1));
    bottom.add(lblname);
    bottom.add(lblprice);
    bottom.add(lblDiscount);
    
    card.add(lblmage,BorderLayout.CENTER);
    card.add(bottom,BorderLayout.SOUTH);
    
    return card;
    
}
private  void find(){
String tim=txtfind.getText().trim().toLowerCase();

   String sql = """
   SELECT 
        p.Id AS id,
        p.Name AS name,
        pd.Icon AS icon,
        p.sale_price AS salePrice,
        p.market_price AS marketPrice,
        p.discount AS discount,
        b.Name AS brandName,
        c.Name AS categoryName
    FROM Products p
    JOIN Products_detail pd ON p.Id = pd.Product_id
    JOIN Brands b ON p.brand_id = b.Id
    JOIN Categories c ON p.category_id = c.Id
""";

List<ViewProductCardDTO> cards = XQuery.getBeanList(ViewProductCardDTO.class, sql);
List<ViewProductCardDTO> filtered = cards.stream()
        .filter(p -> p.getName().toLowerCase().contains(tim))
        .collect(Collectors.toList());
panelsanpham.removeAll();
 for (ViewProductCardDTO d : filtered) {
        JPanel card =taokhung(d);
        panelsanpham.add(card);
        
    }
   

    panelsanpham.revalidate();
    panelsanpham.repaint();
}
private void insp(){
   String sql = """
   SELECT 
            p.Id AS id,
            p.Name AS name,
            pd.Icon AS icon,
            p.sale_price AS salePrice,
            p.market_price AS marketPrice,
            p.discount AS discount,
            b.Name AS brandName,
            c.Name AS categoryName
        FROM Products p
        JOIN Products_detail pd ON p.Id = pd.Product_id
        JOIN Brands b ON p.brand_id = b.Id
        JOIN Categories c ON p.category_id = c.Id
""";

List<ViewProductCardDTO> cards = XQuery.getBeanList(ViewProductCardDTO.class, sql);


panelsanpham.removeAll();
    for (ViewProductCardDTO d : cards) {
        JPanel card =taokhung(d);
        panelsanpham.add(card);
     
    }
   

    panelsanpham.revalidate();
    panelsanpham.repaint();
    
}
private void dattruoc(String username,String product){
Product_Interested_UserDAO product_Interested_UserDAO=new Product_Interested_UserDAOImpl();
   product_Interested_UserDAO.kiemtra(username, productId);
    
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbocategory = new javax.swing.JComboBox<>();
        cbobrand = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtfind = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        panelsanpham = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        labelimagechitiet = new javax.swing.JLabel();
        txtnamechitet = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtmota = new javax.swing.JTextPane();
        btmua = new javax.swing.JButton();
        btthemgio = new javax.swing.JButton();
        txtgianiemyet = new javax.swing.JLabel();
        txtgiaban = new javax.swing.JLabel();
        btdattruoc = new javax.swing.JButton();
        txttonkho = new javax.swing.JLabel();

        cbocategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbocategoryMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cbocategoryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cbocategoryMouseExited(evt);
            }
        });
        cbocategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbocategoryActionPerformed(evt);
            }
        });

        cbobrand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbobrandMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cbobrandMouseEntered(evt);
            }
        });
        cbobrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbobrandActionPerformed(evt);
            }
        });

        txtfind.setText("Tìm kiếm");
        txtfind.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtfindMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(txtfind);

        panelsanpham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelsanpham.setLayout(new java.awt.GridLayout(10, 1, 3, 10));
        jScrollPane2.setViewportView(panelsanpham);

        jButton1.setText("Tìm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelimagechitiet, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelimagechitiet, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtnamechitet.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        txtmota.setEditable(false);
        jScrollPane4.setViewportView(txtmota);

        btmua.setText("Mua hàng");

        btthemgio.setText("Thêm vào giỏ");
        btthemgio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btthemgioActionPerformed(evt);
            }
        });

        txtgiaban.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtgiaban.setForeground(new java.awt.Color(255, 51, 51));

        btdattruoc.setText("Đặt Trước");
        btdattruoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btdattruocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(txtnamechitet, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 215, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(btthemgio)
                        .addGap(144, 144, 144)
                        .addComponent(btdattruoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btmua))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(txtgianiemyet, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(txtgiaban, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(txttonkho, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtnamechitet, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtgianiemyet, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(txtgiaban, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttonkho, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btthemgio)
                    .addComponent(btdattruoc)
                    .addComponent(btmua))
                .addContainerGap(207, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cbocategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbobrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 905, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbocategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbobrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 848, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 943, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbocategoryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbocategoryMouseEntered
        // TODO add your handling code here:
        cbocategory.showPopup();
       
    }//GEN-LAST:event_cbocategoryMouseEntered

    private void cbocategoryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbocategoryMouseExited
        // TODO add your handling code here:
      
    }//GEN-LAST:event_cbocategoryMouseExited

    private void cbobrandMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbobrandMouseEntered
        // TODO add your handling code here:
         cbobrand.showPopup();
    }//GEN-LAST:event_cbobrandMouseEntered

    private void txtfindMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtfindMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtfindMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        find();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbocategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbocategoryActionPerformed
        // TODO add your handling code here:
     
        
    }//GEN-LAST:event_cbocategoryActionPerformed

    private void cbobrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbobrandActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_cbobrandActionPerformed

    private void cbocategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbocategoryMouseClicked
        // TODO add your handling code here:
       // loc();
    }//GEN-LAST:event_cbocategoryMouseClicked

    private void cbobrandMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbobrandMouseClicked
        // TODO add your handling code here:
     //   loc();
    }//GEN-LAST:event_cbobrandMouseClicked

    private void btdattruocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdattruocActionPerformed
        // TODO add your handling code here:
        dattruoc(XAuth.user.getUsername(),productId);
    }//GEN-LAST:event_btdattruocActionPerformed

    private void btthemgioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btthemgioActionPerformed
        // TODO add your handling code here:
        themgiohang();
    }//GEN-LAST:event_btthemgioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btdattruoc;
    private javax.swing.JButton btmua;
    private javax.swing.JButton btthemgio;
    private javax.swing.JComboBox<String> cbobrand;
    private javax.swing.JComboBox<String> cbocategory;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelimagechitiet;
    private javax.swing.JPanel panelsanpham;
    private javax.swing.JTextPane txtfind;
    private javax.swing.JLabel txtgiaban;
    private javax.swing.JLabel txtgianiemyet;
    private javax.swing.JTextPane txtmota;
    private javax.swing.JLabel txtnamechitet;
    private javax.swing.JLabel txttonkho;
    // End of variables declaration//GEN-END:variables
}
