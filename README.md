# TP2DPBO2023

## Janji
Saya Azzahra SIti Hadjar NIM 2100901 mengerjakan soal TP 2 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.
## Desain Program
![tp2](https://user-images.githubusercontent.com/100898963/232195019-5190a9cc-df34-4002-accc-3a3fbe6c3b07.jpg)


## Penjelasan Alur
Saat aplikasi di jalankan, maka halaman login akan ditampilkan
  
### Login
 - Jika sudah memiliki akun
   - Mengisi field Username dan password 
   - Menekan button 'LOGIN'
   - Program mengecek validasi akun
     - Jika data yang dimasukan valid masuk ke halaman Menu 
     - Jika tidak muncul pesan "Username atau Password Tidak Valid"
    
- Jika Belum memiliki akun
   - Menekan button 'REGISTER'
   - Masuk halaman register
  
### Register
   - Mengisi field Nama, Username, Password dan re-type Password
   - Menekan button 'REGISTER'
   - Program mengecek kecocokan password yang diinput pada field Password dan re-type Password
     - Jika cocok program akan insert data user ke database
     - Jika tidak muncul pesan "Password Tidak Cocok"
   - Menekan button 'LOGIN' untuk login akun
    
### Menu
    Saat halaman menu di tampilkan maka list album akan diperlihatkan terlebih dahulu
   
   - Menekan button 'ALBUM' untuk melihat list album yang ada di dalam database (berbentuk card), dan dapat melakukan CRUD album
   
   - Menekan button 'GRUP' untuk melihat list grup yang ada di dalam database (berbentuk card), dan dapat melakukan CRUD grup
   
   - Menekan button 'ADD' untuk Create Data
     - Masuk ke halaman formAlbum atau formGrup untuk Add Data
     - Mengisi data yang diperlukan
       - Jika Album :
         - mengisi field Judul, Harga, Gambar
         - memilih grup pada comboBox
         - menekan button 'Browse' untuk memilih gambar
       - Jika Grup :
         - mengisi field Nama, Agensi, Jumlah Member
         - menekan button 'Browse' untuk memilih gambar
     - Menekean Button 'Add'
     - Program akan insert data Album/Grup ke database dan insert gambar pada folder gambar
   
   - Menekan button 'Update' pada card untuk Update Data yang dipilih
     - Masuk ke halaman formAlbum atau formGrup untuk Update Data
     - Mengisi data yang diperlukan
       - Jika Album :
         - mengisi field Judul, Harga, Gambar
         - memilih grup pada comboBox
         - menekan button 'Browse' untuk memilih gambar
       - Jika Grup :
         - mengisi field Nama, Agensi, Jumlah Member
         - menekan button 'Browse' untuk memilih gambar
     - Menekan Button 'Update'
     - Program akan update data Album/Grup pada database dan insert gambar pada folder gambar
   
   - Menekan button 'Delete' pada card untuk Hapus Data yang dipilih
     - Muncul pesan "Yakin menghapus data ini?"
     - Jika ya, program akan delete data pada database dan delete gambar pada folder gambar
   
   - Menekan button 'X' untuk Logout
     - Masuk ke halaman Login
 
## Dokumentasi
<p align="center">
  <img src="https://github.com/Azzahrasth/TP2DPBO2023/blob/main/record.gif" alt="gif format testing"/>
</p>




