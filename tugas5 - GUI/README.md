# Tugas GUI & Event Handling (PBO)

Program Java Swing sederhana yang menerapkan komponen antarmuka grafis (GUI) dan event handling dalam bahasa Java.

---

## 5 Komponen / Bagian GUI pada Kode

Berikut adalah 5 komponen/bagian GUI yang diterapkan pada program beserta letak baris kodenya di `MainFrame.java`:

### 1. Top-Level Container (`JFrame`)
* **Baris Kode:**
  ```java
  public class MainFrame extends JFrame implements ActionListener {
  ```
  dan konfigurasi di konstruktor:
  ```java
  setTitle("Form Input Sederhana");
  setSize(360, 220);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  ```
* **Penjelasan:** Jendela/window utama aplikasi yang menjadi wadah terluar (top-level) tempat semua panel dan elemen antarmuka diletakkan.

---

### 2. Intermediate Container (`JPanel`)
* **Baris Kode:**
  ```java
  JPanel panelForm = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
  JPanel panelAksi = new JPanel(new GridLayout(2, 1, 5, 5));
  JPanel panelTombol = new JPanel(new FlowLayout());
  ```
* **Penjelasan:** Wadah perantara yang berfungsi mengelompokkan beberapa komponen ke dalam layout tertentu sebelum dipasangkan ke dalam `JFrame` utama.

---

### 3. Atomic Components: Text Display & Input (`JLabel` & `JTextField`)
* **Baris Kode:**
  ```java
  labelNama = new JLabel("Masukkan Nama:");
  textNama = new JTextField(15);
  labelHasil = new JLabel("Halo, ...");
  ```
* **Penjelasan:** Komponen atomik untuk menampilkan teks statis/keterangan (`JLabel`) dan kolom input interaktif bagi pengguna untuk mengetikkan nama (`JTextField`).

---

### 4. Atomic Component: Action Button (`JButton`)
* **Baris Kode:**
  ```java
  btnSubmit = new JButton("Kirim");
  ```
* **Penjelasan:** Komponen atomik berupa tombol klik yang berfungsi sebagai pemicu aksi saat pengguna selesai menginput nama.

---

### 5. Event Handling (`ActionListener` & `actionPerformed`)
* **Baris Kode:**
  * Pendaftaran event listener:
    ```java
    btnSubmit.addActionListener(this);
    ```
  * Method penanganan aksi:
    ```java
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            String input = textNama.getText().trim();
            if (input.isEmpty()) {
                labelHasil.setText("Nama tidak boleh kosong!");
            } else {
                labelHasil.setText("Halo, " + input + "! Selamat belajar PBO.");
            }
        }
    }
    ```
* **Penjelasan:** Mekanisme penanganan event interaksi user. Saat tombol "Kirim" diklik, event ditangkap oleh `actionPerformed` untuk memvalidasi teks input dan memperbarui teks pada `labelHasil`.

---

## Screenshot Hasil Eksekusi

### 1. Tampilan Awal Program
<!-- Masukkan screenshot tampilan awal saat program baru dibuka di bawah ini -->
![Tampilan Awal](screenshot_awal.png)

### 2. Tampilan Validasi Input Kosong
<!-- Masukkan screenshot saat tombol kirim ditekan tanpa mengisi nama di bawah ini -->
![Input Kosong](screenshot_kosong.png)

### 3. Tampilan Input Berhasil
<!-- Masukkan screenshot saat nama diisi lalu tombol kirim ditekan di bawah ini -->
![Input Berhasil](screenshot_berhasil.png)

---

## Struktur File Proyek

```text
tugas GUI & eventhandler/
├── MainFrame.java   # Desain GUI, inisialisasi komponen, dan event handling
├── Main.java        # Runner / entry point (public static void main)
└── README.md        # Dokumentasi 5 komponen GUI dan screenshot program
```

---

## Cara Menjalankan Program

### Lewat Terminal / Command Prompt:
```bash
javac MainFrame.java Main.java
java Main
```

### Lewat IDE (VS Code / IntelliJ / NetBeans):
Buka file `Main.java` lalu jalankan method `main`.
