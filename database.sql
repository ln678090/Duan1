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
