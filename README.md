# eshop

<details>
  <summary>
    Module 1
  </summary>
  
## Reflection 1

Saya sudah berusaha untuk mengikuti clean code principles dengan menggunakan nama function, dan variable yang bisa dibaca secara langsung sehingga kode jika ingin di lanjutkan tidak butuh konteks yang lebih lanjut. Selain itu untuk menghindari error, saya memvalidasikan input untuk edit dan delete. Namun perlu diterapkan validasi tambahan dari sisi user menggunakan javascript agar tidak bisa terkena XSS. Agar lebih aman dibutuhkan pembersihan pada back-end agar special characters tidak tembus. Selain itu saya menggunakan post agar lebih aman.

## Reflection 2

Setelah membuat unit tests, saya merasa lebih percaya dengan program saya. Jumlah dari unit test menurut saya bervariasi dengan jumlah fungsi yang ditawarkan oleh aplikasinya. 100% Code coverage menurut saya bukan berarti kode memiliki tidak ada bug namun hanya berarti bahwa kode sudah di test.

Jika disuruh untuk menuliskan test case baru yang mengharuskan verifikasi jumlah item, dan kita lakukan dengan melakukan setup yang sama, ini bisa mengurangi kualitas kode karena bisa terjadi duplikasi. Lebih baik jika dilakukan secara bersamaan dengan unit test yang lain.

</details>

<details>
  <summary>
    Module 2
  </summary>

## Reflection
1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.
2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

Beberapa kesalahan dari kode yang saya temui adalah Unecessary Constructor dan Unused Import. Strategi yang saya gunakan untuk memperbaiki kesalahan tersebut adalah dengan melakukan code analysis menggunakan PMD, dan kemudian melihat summary setelah di scan. Selain dari itu workflow dari repository ini sudah mencakup beberapa dari Continous Integration seperti testing unit menggunakan JUnit. Selain dari itu telah menggunakan Scorecard dan juga PMD untuk beberapa branch. Untuk deployments kodenya menggunakan layanan koyeb untuk Continuous Deployment dengan melihat apakah ada ada perubahan pada main dan jika terdapat perubahan maka akan melakukan auto deployment. Menurut saya sudah mencakup kedua dari CI dan CD.

</details>

<details>
  <summary>
    Module 3
  </summary>

## Reflection 1

1) Explain what principles you apply to your project!
   SRP: Saya telah memisahkan CarController dari ProductController, sehingga masing masing class mempunyai tanggung jawab sendiri
   OCP: Sebelumnya CarRepository merupakan implementasion langsung, lalu saya ubah menjadi agar CarRepository menjadi sebuah interface dan membuat sebuah CarRepositoryImpl menjadi implementasi konkret.
   DIP: CarServiceImpl bergantung pada CarRepository bukan implementasinya langsung. 

2) Explain the advantages of applying SOLID principles to your project with examples.
   Dengan menggunakan SOLID principles, proyek saya terlihat lebih rapih dan juga lebih maintainable. Contohnya adalah dengan memisahkan car controller dan product controller, jika ada developer yang ingin menambahkan sesuatu ke program saya, mereka bisa langsung memahami fungsi masing-masing class. Dengan menerapkan OCP di CarRepository, kita dapat mengganti CarRepositoryImpl dengan hal lain seperti database tanpa harus mengubah CarRepositorynya sendiri. Dengan menerapkan DIP CarServiceImpl bergantung pada interface CarRepository, sehingga bisa diuji dengan mock repository tanpa harus menggunakan impelementasi konkret.

3) Explain the disadvantages of not applying SOLID principles to your project with examples.
   Jika SOLID principles tidak diterapkan maka proyek ini berpotensi memiliki beberapa masalah. Contohnya adalah jika CarController masih menyatu dengan ProductController, jika kita mengubah sesuatu pada product kita harus memeriksa apakah ada dampak terhadap Car. Jika CarServiceImpl langsung menggunakan implementasi dari CarRepositoryImpl, maka saat testing kita harus pakai data asli,dan tidak bisa menggunakan mock repository. Jika CarServiceImpl masih menggunakan CarRepository, jika kita ingin mengubah penyimpanan data, kita harus mengubah semua yang menggunakan CarRepository.
   
</details>

<details>
  <summary>
    Module 3
  </summary>

## Reflection 1

TDD membantu saya mendapatkan feedback lebih awal, membuat desain kode yang modular, dan menghasilkan dokumentasi hidup melalui unit tests. Dengan menulis tes sebelum implementasi, saya dapat mengidentifikasi masalah desain dan fungsionalitas sejak dini, yang sangat berguna untuk mengurangi kesalahan dan meningkatkan kualitas kode.

## Reflection 2

Tes yang saya buat cukup cepat, independen, dan repeatable, karena menggunakan mock untuk mengisolasi dependensi dan memastikan setiap tes dapat dijalankan secara terpisah. Hasil tes juga self-validating karena pass/fail-nya langsung memberikan feedback tanpa memerlukan interpretasi manual, sehingga memudahkan proses debugging.
   
</details>
