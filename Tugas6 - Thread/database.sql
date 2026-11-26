-- Script Pembuatan Database & Tabel
CREATE DATABASE IF NOT EXISTS db_pbo;
USE db_pbo;

CREATE TABLE IF NOT EXISTS mahasiswa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nim VARCHAR(20) NOT NULL UNIQUE,
    nama VARCHAR(100) NOT NULL,
    jurusan VARCHAR(100) NOT NULL
);

-- Data Contoh Awal
INSERT INTO mahasiswa (nim, nama, jurusan) VALUES 
('22001', 'Ahmad Pratama', 'Informatika'),
('22002', 'Siti Rahma', 'Sistem Informasi')
ON DUPLICATE KEY UPDATE nim=nim;
