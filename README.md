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
