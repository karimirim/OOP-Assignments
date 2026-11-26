# 5A - Java Coding Practice: Thread and Database (CRUD Mahasiswa)

Aplikasi GUI Java Swing untuk manajemen data mahasiswa yang mengimplementasikan interaksi database MySQL menggunakan arsitektur **DAO (Data Access Object)**, **Transaction Management**, serta pola eksekusi asinkronus **Multithreading** dan **`SwingUtilities.invokeLater`**.

---

## 📌 Fitur & Pemenuhan Kebutuhan Tugas

Aplikasi ini dirancang untuk memenuhi seluruh kriteria tugas PBO:

1. **Database Connector (`mysql-connector-j-8.4.0.jar`)**
   - Driver JDBC MySQL disertakan langsung dalam folder project (`mysql-connector-j-8.4.0.jar`).
2. **Implementasi DAO Lengkap (CRUD)**
   - **`Connection`**: Dikelola secara terpusat melalui class `DatabaseConnection.java`.
   - **`PreparedStatement`**: Digunakan pada seluruh operasi SQL (`SELECT`, `INSERT`, `UPDATE`, `DELETE`) guna mencegah SQL Injection.
   - **`Transaction`**: Diterapkan secara eksplisit pada operasi penulisan data (`insert`, `update`, `delete`) menggunakan `conn.setAutoCommit(false)`, `conn.commit()`, dan `conn.rollback()` pada blok `catch`.
   - **`ResultSet`**: Digunakan untuk membaca baris data dari database dan memetakannya ke objek `Mahasiswa`.
3. **Penerapan Pola: `DB on a thread > invokeLater > UI/GUI`**
   - Operasi database yang memakan waktu dijalankan di **Background Worker Thread** terpisah (`new Thread(...)`).
   - Hasil operasi dikirimkan kembali ke UI Thread secara aman melalui **`SwingUtilities.invokeLater(...)`**.
4. **GUI Responsiveness & Thread Feedback**
   - Saat tombol diklik (Simpan/Ubah/Hapus/Muat):
     - Seluruh input dan tombol dinonaktifkan (`setEnabled(false)`).
     - Label status menampilkan teks progres (contoh: *"Status: Menyimpan data ke database (Saving...)..."*).
     - Setelah selesai, data tabel diperbarui, form direset, tombol diaktifkan kembali, dan label status menampilkan konfirmasi sukses.

---

## 📂 Struktur File Project

```text
Tugas Thread/
├── DatabaseConnection.java     # Pengelola koneksi JDBC ke MySQL
├── Mahasiswa.java              # Model data entity Mahasiswa
├── MahasiswaDAO.java           # Data Access Object (CRUD, PreparedStatement, Transaction)
├── MahasiswaFrame.java         # Antarmuka GUI Swing, Event Handler, & Thread Worker
├── Main.java                   # Entry point aplikasi (Event Dispatch Thread)
├── database.sql                # Skrip DDL tabel database
├── mysql-connector-j-8.4.0.jar # Driver JDBC MySQL
└── README.md                   # Dokumentasi aplikasi dan screenshot
```

---

## 🛠️ Persiapan Database

1. Pastikan **MySQL** sudah berjalan di XAMPP / Laragon.
2. Buka **phpMyAdmin** (`http://localhost/phpmyadmin`) atau terminal MySQL.
3. Eksekusi script SQL berikut (atau import file `database.sql`):

```sql
CREATE DATABASE IF NOT EXISTS db_pbo;
USE db_pbo;

CREATE TABLE IF NOT EXISTS mahasiswa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nim VARCHAR(20) NOT NULL UNIQUE,
    nama VARCHAR(100) NOT NULL,
    jurusan VARCHAR(100) NOT NULL
);

-- Data Awal (Opsional)
INSERT INTO mahasiswa (nim, nama, jurusan) VALUES 
('22001', 'Ahmad Pratama', 'Informatika'),
('22002', 'Siti Rahma', 'Sistem Informasi');
```

---

## 🚀 Cara Menjalankan Program

### Cara 1: Menggunakan Terminal / CMD

1. Buka Command Prompt (CMD) atau Terminal di folder project ini:
   ```cmd
   d:
   cd "semester 5 gacor\PBO\Tugas Thread"
   ```
2. Compile seluruh file Java beserta driver MySQL:
   ```cmd
   javac -cp ".;mysql-connector-j-8.4.0.jar" *.java
   ```
3. Jalankan aplikasi:
   ```cmd
   java -cp ".;mysql-connector-j-8.4.0.jar" Main
   ```

### Cara 2: Menggunakan Visual Studio Code (VS Code)

1. Buka folder `Tugas Thread` di VS Code.
2. Buka panel explorer di sebelah kiri, scroll ke bawah ke bagian **JAVA PROJECTS**.
3. Pada bagian **Referenced Libraries**, klik ikon tanda tambah (**`+`**).
4. Pilih file `mysql-connector-j-8.4.0.jar` yang ada di folder project.
5. Buka file `Main.java` lalu klik **Run** (atau tekan `F5`).

---

## 🖼️ Tangkapan Layar (Screenshots)

### 1. Tampilan Awal & Memuat Data (Read via Thread)
<!-- Simpan screenshot tampilan awal saat program baru dibuka dengan nama screenshot_1_load.png -->
![Tampilan Awal dan Read Data](screenshot_1_load.png)

### 2. Menyimpan Data (Disable Button & Status "Saving...")
<!-- Simpan screenshot saat tombol Simpan ditekan (tombol disable & status Saving...) dengan nama screenshot_2_saving.png -->
![Proses Simpan Data](screenshot_2_saving.png)

### 3. Mengubah Data (Update via Transaction)
<!-- Simpan screenshot proses ubah data dengan nama screenshot_3_update.png -->
![Proses Ubah Data](screenshot_3_update.png)

### 4. Menghapus Data (Delete via Transaction)
<!-- Simpan screenshot dialog konfirmasi dan proses hapus data dengan nama screenshot_4_delete.png -->
![Proses Hapus Data](screenshot_4_delete.png)

---

## 💡 Panduan Menjawab Pertanyaan Dosen (Cheat Sheet)

* **Q: Mengapa operasi database harus ditaruh di dalam Thread terpisah?**  
  * **A:** *"Komponen GUI Java Swing berjalan di satu thread bernama Event Dispatch Thread (EDT). Jika operasi database (I/O ke MySQL) yang memakan waktu dijalankan langsung di EDT, tampilan aplikasi akan membeku (freeze/Not Responding). Dengan background thread, aplikasi tetap responsif."*

* **Q: Mengapa perlu menggunakan `SwingUtilities.invokeLater()`?**  
  * **A:** *"Komponen Swing bersifat tidak thread-safe. Artinya, elemen visual tidak boleh dimodifikasi langsung dari background thread. `SwingUtilities.invokeLater()` bertugas mengirim tugas pembaruan UI kembali ke Event Dispatch Thread secara aman."*

* **Q: Di mana letak implementasi Transaction pada kode?**  
  * **A:** *"Pada `MahasiswaDAO.java` di method `insert`, `update`, dan `delete`. Kami menonaktifkan auto-commit dengan `conn.setAutoCommit(false)`, mengeksekusi query dengan `PreparedStatement`, lalu memanggil `conn.commit()` jika berhasil, atau `conn.rollback()` jika terjadi SQLException."*

* **Q: Mengapa tombol dinonaktifkan (`setEnabled(false)`) saat proses simpan?**  
  * **A:** *"Untuk mencegah aksi ganda (double-click / race condition) dari pengguna saat query database sebelumnya masih diproses di latar belakang."*
