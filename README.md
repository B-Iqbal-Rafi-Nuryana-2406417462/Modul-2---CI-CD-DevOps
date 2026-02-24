Exercise 1

Prinsip clean code diterapkan dalam beberapa hal
1. Penamaan yang jelas dan deskriptif untuk variabel, fungsi, dan kelas.
2. Fungsi yang pendek dan melakukan satu tugas spesifik.
3. Struktur proyek yang terorganisir dengan baik(controller,model,repository,service).

Exercise 2

1. Setelah membuat unit test, saya menjadi lebih yakin bahwa kode yang saya tulis berfungsi sesuai harapan.
Unit test membantu mendeteksi bug lebih awal dan memastikan bahwa perubahan kode di masa depan tidak merusak fungsionalitas yang sudah ada.
Jumlah unit test biasanya disesuikan dengan banyaknya fungsi atau metode yang ada dalam kode.
Jika saya punya code coverage 100%, saya merasa sangat percaya diri bahwa kode saya telah diuji secara menyeluruh dan kemungkinan besar bebas dari bug.
2. - Membuat abstract base test class
- Memindahkan setup umum ke sana
- Lalu setiap test suite (misalnya CreateProductFunctionalTest dan HomePageFunctionalTest) extends base class tersebut
- Dengan begitu, kode setup tidak perlu diulang di setiap test suite, sehingga mengurangi duplikasi kode dan membuatnya lebih mudah untuk dikelola.

Exercise 2

1. Code quality issues fixed:
- Mengganti beberapa parameter yang tidak pernah di ubah menjadi final
- Method memiliki terlalu banyak return statement.
- Mengganti beberapa nama variabel menjadi lebih pendek
- Menghapus modifier yang tidak diperlukan pada beberapa method 
- Menambahkan utility class warning
2. CI/CD implementations based on definitions
- Continuous Integration (CI): Setiap push/pull-request ke setiap branch akan trigger otomatis ci.yml yang akan menjalankan unit test dan PMD static analysis, untuk memastikan kode selalu terferifikasi sebelum melakukan merging.
- Continuous Development (CD): Setelah branch di merge ke main, KOYEB akan melakukan redeployment otomatis, sehingga setiap perubahan yang sudah di merge akan langsung diterapkan ke production tanpa perlu melakukan manual deployment.
- Full Pipeline: Workflows mencakup build -> test -> code quality check -> deploy, sehingga memastikan setiap perubahan kode melewati proses yang ketat sebelum diterapkan ke production.