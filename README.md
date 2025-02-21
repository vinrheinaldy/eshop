# eshop

## Reflection 1

Saya sudah berusaha untuk mengikuti clean code principles dengan menggunakan nama function, dan variable yang bisa dibaca secara langsung sehingga kode jika ingin di lanjutkan tidak butuh konteks yang lebih lanjut. Selain itu untuk menghindari error, saya memvalidasikan input untuk edit dan delete. Namun perlu diterapkan validasi tambahan dari sisi user menggunakan javascript agar tidak bisa terkena XSS. Agar lebih aman dibutuhkan pembersihan pada back-end agar special characters tidak tembus. Selain itu saya menggunakan post agar lebih aman.

## Reflection 2

Setelah membuat unit tests, saya merasa lebih percaya dengan program saya. Jumlah dari unit test menurut saya bervariasi dengan jumlah fungsi yang ditawarkan oleh aplikasinya. 100% Code coverage menurut saya bukan berarti kode memiliki tidak ada bug namun hanya berarti bahwa kode sudah di test.

Jika disuruh untuk menuliskan test case baru yang mengharuskan verifikasi jumlah item, dan kita lakukan dengan melakukan setup yang sama, ini bisa mengurangi kualitas kode karena bisa terjadi duplikasi. Lebih baik jika dilakukan secara bersamaan dengan unit test yang lain.
