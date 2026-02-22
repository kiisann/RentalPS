<div align="center">

# 🎮 RentalPS  
### Sistem Manajemen Rental PlayStation Berbasis Desktop

<img src="https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=java&logoColor=white"/>
<img src="https://img.shields.io/badge/MySQL-00758F?style=flat-square&logo=mysql&logoColor=white"/>
<img src="https://img.shields.io/badge/Desktop-GUI-2ecc71?style=flat-square"/>
<img src="https://img.shields.io/badge/OOP-Architecture-blue?style=flat-square"/>
<img src="https://img.shields.io/badge/Status-Academic%20Project-black?style=flat-square"/>

<br><br>

Aplikasi desktop modern untuk mengelola sistem penyewaan PlayStation  
secara terstruktur, efisien, dan berbasis Object-Oriented Programming.

</div>

---

## ✨ Tentang Project

<strong>RentalPS</strong> adalah aplikasi manajemen penyewaan PlayStation berbasis <strong>Java GUI</strong> yang dirancang untuk membantu pengelolaan usaha rental secara digital.

Project ini dikembangkan sebagai <strong>Tugas Akhir (UAS)</strong> mata kuliah <em>Pemrograman Berorientasi Objek</em> dengan arsitektur modular yang terorganisir.

---

## 🚀 Fitur Utama

### 🎮 Manajemen Unit PlayStation
- Tambah, edit, dan hapus data PS  
- Pengaturan harga sewa  
- Update status (Tersedia / Disewa)

### 👤 Manajemen Pelanggan
- Input dan pembaruan data pelanggan  
- Penyimpanan data secara permanen  

### 🧾 Sistem Transaksi
- Pemilihan pelanggan dan unit PS  
- Input durasi sewa  
- Perhitungan biaya otomatis  
- Penyimpanan riwayat transaksi  

### ⚡ Otomatisasi Sistem
Total biaya dihitung secara dinamis berdasarkan durasi dan harga per jam.

---

## 🧠 Arsitektur OOP

Project ini menerapkan konsep utama Pemrograman Berorientasi Objek:

- <strong>Encapsulation</strong> → Pengamanan atribut dengan akses terkontrol  
- <strong>Inheritance</strong> → Struktur class yang efisien  
- <strong>Polymorphism</strong> → Fleksibilitas implementasi method  
- <strong>Abstraction</strong> → Pemisahan Model, View, dan Controller  

---

## 🏗️ Struktur Project

<pre>
RentalPS/
│
├── model/
├── view/
├── controller/
├── database/
└── Main.java
</pre>

Struktur ini memisahkan:
- <strong>Model</strong> → Representasi data  
- <strong>View</strong> → Antarmuka GUI  
- <strong>Controller</strong> → Logika bisnis  
- <strong>Database</strong> → Koneksi & pengolahan data  

---

## ⚙️ Cara Menjalankan

### 1️⃣ Clone Repository

<pre>
git clone https://github.com/username/rental-ps.git
</pre>

---

### 2️⃣ Import Database ke phpMyAdmin

1. Jalankan XAMPP / Laragon dan aktifkan <strong>Apache</strong> serta <strong>MySQL</strong>  
2. Buka browser dan akses:  
   <pre>http://localhost/phpmyadmin</pre>
3. Klik <strong>New</strong> → buat database baru dengan nama:  
   <pre>rental_ps</pre>
4. Pilih database tersebut → klik tab <strong>Import</strong>  
5. Upload file <strong>database.sql</strong> yang tersedia di project  
6. Klik <strong>Go</strong> untuk menjalankan proses import  

Pastikan tidak ada error saat proses import.

---

### 3️⃣ Konfigurasi Koneksi Database

Buka file <strong>Koneksi.java</strong> lalu sesuaikan:

<pre>
String url = "jdbc:mysql://localhost:3306/rental_ps";
String user = "root";
String password = "";
</pre>

Sesuaikan username dan password jika berbeda.

---

### 4️⃣ Jalankan Aplikasi

1. Import project ke IDE (NetBeans / IntelliJ / Eclipse)  
2. Pastikan JDBC Driver sudah ditambahkan  
3. Jalankan <strong>Main.java</strong>  

---

## 💻 Teknologi yang Digunakan

- Java  
- Java GUI (Swing / JavaFX)  
- MySQL  
- JDBC  
- Object-Oriented Programming  

---

## 👨‍💻 Developer

<strong>Fiki Sulistiawan</strong>  
Pemrograman Berorientasi Objek – UAS  
2026  

---

<div align="center">

© 2026 RentalPS — Academic Project

</div>
