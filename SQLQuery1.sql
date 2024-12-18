CREATE DATABASE QuanLyHocVien;
USE QuanLyHocVien;

CREATE TABLE User (
    UserID INT PRIMARY KEY AUTO_INCREMENT,
    Username VARCHAR(50) NOT NULL UNIQUE,
    Password VARCHAR(50) NOT NULL
);


CREATE TABLE MonHoc (
    MonHocID INT PRIMARY KEY AUTO_INCREMENT,
    TenMonHoc VARCHAR(100) NOT NULL
);

CREATE TABLE GiaoVien (
    GiaoVienID INT PRIMARY KEY AUTO_INCREMENT,
    TenGiaoVien VARCHAR(100) NOT NULL,
    UserID INT,
    FOREIGN KEY (UserID) REFERENCES User(UserID)
);

CREATE TABLE HocPhan (
    HocPhanID INT PRIMARY KEY AUTO_INCREMENT,
    MonHocID INT,
    GiaoVienID INT,
    HocKy INT,
    SoTinChi INT,
    NamHoc YEAR,
    FOREIGN KEY (MonHocID) REFERENCES MonHoc(MonHocID),
    FOREIGN KEY (GiaoVienID) REFERENCES GiaoVien(GiaoVienID)
);

CREATE TABLE DangKy (
    DangKyID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    TX1 FLOAT;
    TX2 FLOAT;
	HocPhanID INT,
    Diem FLOAT,
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (HocPhanID) REFERENCES HocPhan(HocPhanID)
);