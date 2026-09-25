# Tugas 4 — OOP: Encapsulation, Inheritance, Polymorphism

Program Java sederhana untuk mendemonstrasikan tiga konsep utama Object-Oriented
Programming: **Encapsulation**, **Inheritance**, dan **Polymorphism**, menggunakan
studi kasus bangun ruang/datar (`bentuk`, `bujurSangkar`, `lingkaran`, `silinder`).

## Struktur Class

```
bentuk (parent)
 ├── bujurSangkar
 └── lingkaran
      └── silinder
```

| File | Deskripsi |
|---|---|
| `bentuk.java` | Class dasar, punya atribut `warna` beserta getter/setter dan method `printInfo()`. |
| `bujurSangkar.java` | Turunan dari `bentuk`, menambahkan atribut `sisi` dan method `hitungLuas()`. |
| `lingkaran.java` | Turunan dari `bentuk`, menambahkan atribut `radius` dan method `hitungLuas()`. |
| `silinder.java` | Turunan dari `lingkaran` (turunan berjenjang), menambahkan atribut `tinggi` dan method `hitungVolume()`. |
| `tugas4.java` | Class `main`, menjalankan demo ketiga konsep OOP di atas secara berurutan, dengan satu input dari Scanner. |

## 1. Encapsulation

Atribut `warna` pada class `bentuk` tidak diakses langsung dari luar, melainkan
melalui method `getWarna()` dan `setWarna()`:

```java
public String warna;

public String getWarna() {
    return warna;
}

public void setWarna(String warna) {
    this.warna = warna;
}
```

Di `tugas4.java`, user diminta memasukkan warna baru lewat `Scanner`, lalu
perubahannya dilakukan lewat `setWarna()`, bukan dengan mengubah field secara
langsung — ini contoh encapsulation.

## 2. Inheritance

`bujurSangkar` dan `lingkaran` mewarisi (`extends`) class `bentuk`, sehingga
otomatis mendapat atribut `warna` beserta getter/setter-nya tanpa perlu menulis
ulang:

```java
public class bujurSangkar extends bentuk {
    public bujurSangkar(double sisi, String warna) {
        super(warna);
        this.sisi = sisi;
    }
}
```

`silinder` bahkan mewarisi `lingkaran` (bukan langsung dari `bentuk`), sehingga
mendapatkan atribut `radius` dan method `hitungLuas()` milik `lingkaran`, lalu
memanfaatkannya untuk menghitung volume:

```java
public class silinder extends lingkaran {
    public double hitungVolume() {
        return hitungLuas() * tinggi;
    }
}
```

## 3. Polymorphism

Setiap subclass meng-override method `printInfo()` milik `bentuk` dengan
implementasinya masing-masing. Saat dipanggil lewat referensi bertipe `bentuk`,
Java otomatis menjalankan versi override sesuai object aslinya (runtime
polymorphism):

```java
bentuk[] semuaBentuk = { shape, square, circle, cylinder };
for (bentuk b : semuaBentuk) {
    b.printInfo();
}
```

## Cara Menjalankan

```bash
javac tugas4/*.java
java tugas4.tugas4
```

Program akan meminta satu input warna baru lewat Scanner, lalu langsung
menampilkan seluruh demo secara berurutan tanpa menu.

## Screenshot Output

_(tempel screenshot hasil run program di sini)_
