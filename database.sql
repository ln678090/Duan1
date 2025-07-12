use master;
drop database bandocongnghe;
create database bandocongnghe;

use bandocongnghe;

CREATE TABLE Users (
   
    Username NVARCHAR(50) PRIMARY KEY,
    Password NVARCHAR(50),
    Roles int,
    Otp NVARCHAR(10),
    Email NVARCHAR(100),
    Status int 
);

CREATE TABLE User_details (
    Userdetail_id INT PRIMARY KEY IDENTITY(1000,1),
    Fullname NVARCHAR(100),
    Phone NVARCHAR(15),
    Gender NVARCHAR(10),
    Address NVARCHAR(255),
    username NVARCHAR(50),
    Dob DATE,
    Photo NVARCHAR(255),
    FOREIGN KEY (username) REFERENCES Users(username)
);



create table Categories(
Id varchar(50) primary key,
Name nvarchar(100),
);
CREATE TABLE Brands (
    Id VARCHAR(50) PRIMARY KEY,
    Name NVARCHAR(100),
    Origin NVARCHAR(100) 
);


create table Products (
Id varchar(50) primary key,
Name nvarchar(100),
import_price MONEY,      --giá nhập 
    market_price MONEY,      --giá niêm yết
    sale_price MONEY,  -- giá bán(đã giảm)
discount INT, --% giảm
category_id varchar(50),
FOREIGN KEY (category_id) REFERENCES Categories(Id),
brand_id VARCHAR(50),
FOREIGN KEY (brand_id) REFERENCES Brands(Id),

);


create table Products_detail(
Productsdetail_id INT PRIMARY KEY IDENTITY(2000,1),
Product_id varchar(50),
description nvarchar(max),
Icon nvarchar(50),
FOREIGN KEY (Product_id) REFERENCES Products(Id),
);

create table Inventory (
Id INT PRIMARY KEY IDENTITY(3000,1),
Product_id varchar(50),
FOREIGN KEY (Product_id) REFERENCES Products(Id),
Quantity  int ,
Unit NVARCHAR(20),
LastUpdated datetime 
);
create table  Product_Interested_Users (
Id INT PRIMARY KEY IDENTITY(8000,1),
Username NVARCHAR(50),
FOREIGN KEY (username) REFERENCES Users(username),
Product_id varchar(50),
FOREIGN KEY (Product_id) REFERENCES Products(Id),
RegisteredAt DATETIME DEFAULT GETDATE(),
Status int default 0 --0 :chờ hàng ,1 đã thông báo có hàng 
);

Create table Carts(
Id int primary key IDENTITY(4000,1),
Username nvarchar(50),
Createat datetime default getdate(),
Status int default 0,--trạng thái giỏ hàng 0  đang dùng; 1  đã thanh toán
FOREIGN KEY (Username) REFERENCES Users(Username)
);
create table Cart_items(
Id INT PRIMARY KEY IDENTITY(5000,1),
Status int ,
/*
0	Đã thêm vào giỏ  chưa chọn thanh toán
1	Đã chọn để thanh toán
2	Đã thanh toán thành công
3	Đã huỷ (người dùng bỏ qua hoặc hệ thống tự huỷ nếu quá hạn) */
CartId int ,
FOREIGN KEY (CartId) REFERENCES Carts(Id),
Product_id varchar(50),
FOREIGN KEY (Product_id) REFERENCES Products(Id),
Quantity  int ,
UnitPrice money
);

create table Orders(
Id INT PRIMARY KEY IDENTITY(6000,1),
CartId int ,
FOREIGN KEY (CartId) REFERENCES Carts(Id),
OrderDate datetime default getdate(),
TotalAmount  money,
Status varchar(50)
);
create table Order_items(
Id INT PRIMARY KEY IDENTITY(7000,1),
OrderId int ,
FOREIGN KEY (OrderId) REFERENCES Orders(Id),
Product_id varchar(50),
FOREIGN KEY (Product_id) REFERENCES Products(Id),
Quantity  int ,
UnitPrice money
);

INSERT INTO Users (Username, Password, Roles, Otp, Email,Status) VALUES
(N'ln678090', N'123456', 1, null, N'ln678090@gmail.com',1),
(N'lamkodeptrai2', N'123456', 2, null, N'lamkodeptrai2@gmail.com',1),
(N'hi8675793', N'123456', 3,null, N'hi8675793@gmail.com',1),
(N'phamthid', N'123abc', 3, null, N'hi8675793@gmail.com',1),
(N'vuquang', N'zxcvbn', 3,null, N'hi8675793@gmail.com',0);



INSERT INTO User_details (Fullname, Phone, Gender, Address, username, Dob, Photo) VALUES
(N'Nguyễn Phúc Lâm', N'0901234001', N'Nam', N'Hà Nội', 'ln678090', '1990-01-01', N'lam.jpg'),
(N'Lâm dẹp zai', N'0901234002', N'Nữ', N'Hải Phòng', 'lamkodeptrai2', '1995-02-02', N'lam2.jpg'),
(N'Trần Văn C', N'0901234003', N'Nam', N'Đà Nẵng', 'hi8675793', '1992-03-03', N'chi.jpg'),
(N'Phạm Thị D', N'0901234004', N'Nữ', N'Nghệ An', 'phamthid', '1993-04-04', N'muahang.jpg'),
(N'Vũ Quang', N'0901234005', N'Nam', N'Hải Dương', 'vuquang', '1991-05-05', N'muahang.jpg');

INSERT INTO Categories (Id, Name)
VALUES
('C001', N'Laptop'),
('C002', N'Điện thoại'),
('C003', N'Phụ kiện'),
('C004', N'Đồng hồ'),
('C005', N'Tablet');
INSERT INTO Brands (Id, Name, Origin) VALUES
('BR01', N'Dell', N'Mỹ'),
('BR02', N'Apple', N'Mỹ'),
('BR03', N'Acer', N'Taiwan'),
('BR04', N'Asus', N'Taiwan'),
('BR05', N'Lenovo', N'Trung Quốc'),
('BR06', N'MSI', N'Taiwan'),
('BR07', N'HP', N'Mỹ'),
('BR08', N'Samsung', N'Hàn Quốc'),
('BR09', N'Realme', N'Trung Quốc'),
('BR10', N'Redmi', N'Trung Quốc'),
('BR11', N'Honor', N'Trung Quốc'),
('BR12', N'Xiaomi', N'Trung Quốc'),
('BR13', N'Havit', N'Trung Quốc'),
('BR14', N'Xmobile', N'Việt Nam'),
('BR15', N'Dareu', N'Trung Quốc'),
('BR16', N'JM Bayer', N'Đức');

-- Laptop
INSERT INTO Products (Id, Name, import_price, market_price, sale_price, discount, category_id, brand_id) VALUES 
('LAP01', N'Dell XPS 13', 25999000, 30999000, 28999000, 6, 'C001', 'BR01'),
('LAP02', N'MacBook Air M2', 23999000, 26999000, 24999000, 7, 'C001', 'BR02'),
('LAP03', N'Acer predator helios neo 16', 23999000, 26999000, 24999000, 7, 'C001', 'BR03'),
('LAP04', N'Asus Vivobook Go 15 E1504FA', 22999000, 25999000, 24999000, 4, 'C001', 'BR04'),
('LAP05', N'Lenovo Ideapad Slim 3 16IRH10', 22999000, 25999000, 24999000, 4, 'C001', 'BR05'),
('LAP06', N'MSI Gaming Thin 15 B12UCX', 22999000, 25999000, 24999000, 4, 'C001', 'BR06'),
('LAP07', N'HP 240 G9', 21999000, 25999000, 24999000, 4, 'C001', 'BR07');

-- Điện thoại
INSERT INTO Products (Id, Name, import_price, market_price, sale_price, discount, category_id, brand_id) VALUES 
('DT01', N'iPhone 14', 20999000, 23999000, 22999000, 4, 'C002', 'BR02'),
('DT02', N'Samsung S23 Ultra', 21999000, 25999000, 24999000, 4, 'C002', 'BR08'), 
('DT03', N'Xiaomi Redmi Note 14 Pro', 20999000, 23999000, 22999000, 4, 'C002', 'BR10'),
('DT04', N'realme 14T 5G', 20999000, 23999000, 22999000, 4, 'C002', 'BR09'),
('DT05', N'HONOR X9c Smart 5G', 20999000, 23999000, 22999000, 4, 'C002', 'BR11');

-- Phụ kiện
INSERT INTO Products (Id, Name, import_price, market_price, sale_price, discount, category_id, brand_id) VALUES 
('PK01', N'Tai nghe Bluetooth Havit Hakii Light', 700000, 1090000, 990000, 17, 'C003', 'BR13'),
('PK02', N'Cáp Type C - Type C 2m Xmobile DR-T11', 250000, 450000, 350000, 22, 'C003', 'BR14'),
('PK03', N'Sạc dự phòng Polymer 20000mAh', 390000, 690000, 590000, 14, 'C003', 'BR12'),
('PK04', N'Ốp lưng JM Bayer II', 100000, 190000, 150000, 21, 'C003', 'BR16'),
('PK05', N'Chuột Dareu EM911', 590000, 890000, 790000, 11, 'C003', 'BR15'),
('PK06', N'Bàn Phím Dareu EK98 Pro', 990000, 1390000, 1290000, 7, 'C003', 'BR15');

-- Đồng hồ
INSERT INTO Products (Id, Name, import_price, market_price, sale_price, discount, category_id, brand_id) VALUES 
('DH01', N'Apple Watch Series 8', 9990000, 12999000, 11999000, 8, 'C004', 'BR02'),
('DH02', N'Xiaomi Watch S1', 2990000, 4990000, 3990000, 20, 'C004', 'BR12');

-- Tablet
INSERT INTO Products (Id, Name, import_price, market_price, sale_price, discount, category_id, brand_id) VALUES 
('TB01', N'iPad Air 2022', 14999000, 18999000, 17999000, 5, 'C005', 'BR02'),
('TB02', N'Samsung Tab S8', 13999000, 16999000, 15999000, 6, 'C005', 'BR08');



INSERT INTO Products_detail (Product_id, description, Icon) VALUES
-- Laptop
('LAP01', N'Laptop Dell – XPS 13 với thiết kế cao cấp bằng nhôm, màn hình 13", độ phân giải Full HD, viền siêu mỏng; trang bị vi xử lý Intel Core i7, RAM 16GB, ổ SSD 512GB, pin tối ưu lên đến 12 giờ. Máy siêu nhẹ và phù hợp cho dân văn phòng di chuyển nhiều.', 'dell_xps13.jpg'),
('LAP02', N'Laptop Apple – MacBook Air M2 2023 với chip Apple Silicon M2 cho hiệu năng vượt trội, thiết kế mỏng nhẹ 15 inch Retina, dung lượng RAM 8GB, SSD 512GB, thời lượng pin lên đến 18 giờ. Lý tưởng cho học tập, làm đồ họa nhẹ, và công việc di động.', 'macbook_air_m2.jpg'),
('LAP03', N'Laptop Acer – Predator Helios Neo 16 chuyên game: card đồ họa RTX 4060 mạnh mẽ, màn hình 16" IPS 165Hz, RAM 16GB, SSD NVMe 512gb. Hệ thống tản nhiệt kép AeroBlade 3D giúp máy vận hành mát mẻ kể cả khi chiến game nặng.', 'acer_helios_neo16.jpg'),
('LAP04', N'Laptop Asus – Vivobook Go 15 với chip AMD Ryzen 5, RAM 8GB, SSD 512GB, màn hình Full HD 15.6", thiết kế mỏng và nhẹ với pin 42Wh cho thời gian làm việc kéo dài. Thích hợp cho sinh viên, văn phòng và người dùng phổ thông.', 'vivobook_go15.jpg'),
('LAP05', N'Laptop Lenovo – Ideapad Slim 3 16IRH10 trang bị Intel Core i5 Gen 13, RAM 8GB nâng cấp được, SSD 512GB. Màn hình IPS 16 inch rộng rãi, chất lượng hiển thị tốt, thiết kế hiện đại, phù hợp đa mục đích từ học tập đến công việc.', 'ideapad_slim3.jpg'),
('LAP06', N'Laptop MSI – Gaming Thin 15 B12UCX với card RTX 2050, chip Intel Core i5 Gen 12, RAM 16GB, SSD 512GB. Màn hình 15.6" FHD 144Hz cực mượt, tản nhiệt Cooler Boost cho game thủ. Thiết kế mạnh mẽ, chuyên dụng cho gaming và đồ họa.', 'msi_thin15.jpg'),
('LAP07', N'Laptop HP – HP 240 G9 văn phòng: chip Intel Core i3 Gen 12, RAM 8GB, SSD 256GB, màn hình 14" HD, trọng lượng nhẹ và pin lâu. Máy lý tưởng cho làm việc cơ bản, học online, xử lý văn bản và sử dụng hàng ngày.', 'hp_240_g9.jpg'),

-- Điện thoại
('DT01', N'Điện thoại Apple – iPhone 14 với chip A15 Bionic, màn hình OLED Super Retina 6.1", hệ thống camera kép 12MP, chống rung quang học, pin trâu và Face ID bảo mật. Hệ điều hành iOS, hỗ trợ cập nhật lâu dài.', 'iphone14.jpg'),
('DT02', N'Điện thoại Samsung – Galaxy S23 Ultra màn hình Dynamic AMOLED 6.8", camera chính 200MP với zoom quang học, chip Snapdragon 8 Gen 2, RAM 8GB, pin 5000mAh, hỗ trợ bút S-Pen. Dành cho người thích chụp ảnh và dùng nhiều tính năng cao cấp.', 'samsung_s23_ultra.jpg'),
('DT03', N'Điện thoại Redmi – Redmi Note 14 Pro trang bị màn AMOLED 6.67", tần số quét 120Hz, chip Snapdragon mạnh mẽ, camera 108MP AI, RAM 8GB, pin 5000mAh, sạc nhanh 67W. Thiết kế trẻ trung phù hợp với người dùng tầm trung.', 'redmi_note14pro.jpg'),
('DT04', N'Điện thoại Realme – Realme 14T 5G với màn 6.6", chip Dimensity tiết kiệm năng lượng, kết nối 5G tốc độ cao, camera 50MP, pin 5000mAh. Máy có thiết kế hiện đại và giao diện thân thiện dễ sử dụng.', 'realme_14t_5g.jpg'),
('DT05', N'Điện thoại Honor – Honor X9c với màn AMOLED cong 6.8", RAM 12GB, pin khủng 5100mAh, camera AI 64MP, hỗ trợ sạc nhanh. Thiết kế sang trọng, bảo vệ màn hình tốt, dùng ổn định cho cả giải trí và công việc.', 'honor_x9c.jpg'),

-- Phụ kiện
('PK01', N'Phụ kiện Tai nghe – Havit Hakii Light Bluetooth 5.3 chống ồn, có mic gọi thoại, pin 8h, thiết kế nhét tai thể thao thoải mái. Âm thanh rõ ràng, thích hợp cho học online, làm việc và nghe nhạc.', 'havit_hakii_light.jpg'),
('PK02', N'Phụ kiện Cáp sạc – Xmobile DR-T11 dài 2m chuẩn Type-C to Type-C, hỗ trợ sạc nhanh PD 60W, vỏ nylon bền, chống rối. Sử dụng cho laptop, điện thoại, máy tính bảng có cổng USB-C.', 'xmobile_drt11.jpg'),
('PK03', N'Phụ kiện Sạc dự phòng – Polymer dung lượng 20.000mAh, hỗ trợ sạc nhanh QC/PD, có 2 cổng ra USB-A, vỏ chống cháy, hiển thị phần trăm pin. Dùng cho điện thoại, máy ảnh, tablet mọi lúc mọi nơi.', 'polymer_20000.jpg'),
('PK04', N'Phụ kiện Ốp lưng – Ốp JM Bayer II chất liệu nhựa cứng, viền dẻo giúp chống sốc, bảo vệ iPhone 16 Pro Max toàn diện, chống xước và bám vân tay. Thiết kế trong suốt khoe máy.', 'oplung_iphone16.jpg'),
('PK05', N'Phụ kiện Chuột – Dareu EM911 có dây, cảm biến quang học DPI lên đến 7200, hiệu ứng LED RGB chuyển màu đẹp, thiết kế công thái học thoải mái khi sử dụng lâu. Phù hợp chơi game và làm việc.', 'dareu_em911.jpg'),
('PK06', N'Phụ kiện Bàn phím – Dareu EK98 Pro là bàn phím cơ không dây Bluetooth layout 98 phím, switch đỏ yên tĩnh, pin trâu, hỗ trợ 3 thiết bị cùng lúc. Dành cho người dùng đa nền tảng: Win, Mac, Android.', 'dareu_ek98pro.jpg'),

-- Đồng hồ
('DH01', N'Đồng hồ Apple – Watch Series 8 màn hình Always-On Retina, tính năng đo ECG, oxy trong máu, theo dõi giấc ngủ, chống nước 50m. Kết nối iPhone, hỗ trợ thông báo và điều khiển nhạc.', 'apple_watch_s8.jpg'),
('DH02', N'Đồng hồ Xiaomi – Watch S1 màn AMOLED 1.43", hỗ trợ đo nhịp tim, SpO2, GPS, theo dõi thể thao, pin 12 ngày, kháng nước 5ATM. Hỗ trợ điều khiển camera và theo dõi sức khỏe toàn diện.', 'xiaomi_watch_s1.jpg'),

-- Tablet
('TB01', N'Tablet Apple – iPad Air 2022 chip M1 mạnh mẽ, màn Liquid Retina 10.9", hỗ trợ Apple Pencil 2, RAM 8GB, camera 12MP, Touch ID và iPadOS tối ưu cho học tập, vẽ, ghi chú.', 'ipad_air_2022.jpg'),
('TB02', N'Tablet Samsung – Galaxy Tab S8 chip Snapdragon 8 Gen 1, màn hình AMOLED 11", tần số quét 120Hz, RAM 8GB, bút S Pen đi kèm, camera góc rộng, bảo mật vân tay, pin 8000mAh.', 'samsung_tab_s8.jpg');


INSERT INTO Inventory (Product_id, Quantity, Unit, LastUpdated) VALUES
-- Laptop (7 sản phẩm × 20 cái)
('LAP01', 20, N'chiếc', GETDATE()),
('LAP02', 20, N'chiếc', GETDATE()),
('LAP03', 20, N'chiếc', GETDATE()),
('LAP04', 20, N'chiếc', GETDATE()),
('LAP05', 20, N'chiếc', GETDATE()),
('LAP06', 20, N'chiếc', GETDATE()),
('LAP07', 20, N'chiếc', GETDATE()),

-- Điện thoại (5 sản phẩm × 20 cái)
('DT01', 20, N'chiếc', GETDATE()),
('DT02', 20, N'chiếc', GETDATE()),
('DT03', 20, N'chiếc', GETDATE()),
('DT04', 20, N'chiếc', GETDATE()),
('DT05', 20, N'chiếc', GETDATE()),

-- Phụ kiện (6 sản phẩm × 20 cái)
('PK01', 20, N'cái', GETDATE()),
('PK02', 20, N'cái', GETDATE()),
('PK03', 20, N'cái', GETDATE()),
('PK04', 20, N'cái', GETDATE()),
('PK05', 20, N'cái', GETDATE()),
('PK06', 20, N'cái', GETDATE()),

-- Đồng hồ (2 sản phẩm × 10 cái)
('DH01', 10, N'chiếc', GETDATE()),
('DH02', 0, N'chiếc', GETDATE()),

-- Tablet (2 sản phẩm × 10 cái)
('TB01', 10, N'chiếc', GETDATE()),
('TB02', 10, N'chiếc', GETDATE());

