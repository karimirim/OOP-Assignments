# PBO 5A - Self Exercise / Exploration of Array and ArrayList

## Deskripsi

Program ini dibuat untuk memenuhi tugas **5A - Self Exercise/Exploration of Array and ArrayList** pada mata kuliah Pemrograman Berorientasi Objek (PBO).

Program merupakan simulasi sederhana sistem perbankan yang menggunakan konsep Object-Oriented Programming, khususnya penggunaan **Array**, object, constructor, method, dan accessor.

Program juga dilengkapi dengan menu sederhana menggunakan `Scanner` untuk melakukan beberapa operasi pada akun bank.

## Struktur Program

Program terdiri dari beberapa class:

- `account.java`  
  Menyimpan informasi saldo dan menyediakan method untuk deposit, withdraw, dan melihat saldo.

- `customer.java`  
  Menyimpan informasi customer berupa nama depan, nama belakang, dan account yang dimiliki.

- `bank.java`  
  Menyimpan customer dalam sebuah array dan menyediakan method untuk menambahkan serta mengambil customer.

- `tugas3.java`  
  Merupakan main program untuk membuat object dan mengeksplorasi penggunaan class, array, dan method.

## Fitur Program

Program menyediakan beberapa menu:

1. Deposit
2. Withdraw
3. Check Balance
4. Exit

Program juga memberikan saldo awal sebesar `50000` kepada customer baru.

## Screenshot

### 1. Input Nama Customer

Screenshot saat program meminta input nama depan dan nama belakang customer.

> (nama.png)

### 2. Menu Utama

Screenshot saat menu utama ditampilkan.

(menu.png)

### 3. Deposit

Screenshot saat memilih menu **Deposit** dan memasukkan jumlah uang.

(depo.png)

### 4. Withdraw

Screenshot saat memilih menu **Withdraw** dan memasukkan jumlah uang.

(withdraw.png)

### 5. Check Balance dan Exit

Screenshot saat memilih **Check Balance** dan saat memilih **Exit**.

(check balance.png)
(exit.png)

## Cara Menjalankan

Pastikan Java sudah terinstall pada komputer.

Compile seluruh file Java:

```bash
javac tugas3\*.java
