package com.example.latihanactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    //Buat variabel
    private lateinit var edtWidth: EditText //untuk mendeklarasikan variabel edtWidth sebagai objek EditText akses private. lateinit untuk menandai variabel akan diinisialisasi
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    companion object { //untuk menyimpan nilai konstan "STATE RESULT" bernilai "state_result"
        private const val STATE_RESULT = "state_result" //namun pada saaat menyimpan terjadi rotasi
        //ketika terjadi akan memanggil onSaveInstanceSatate untuk menyimpan state aplikasi
        //dan nilai tvResult akan disimpan pada outState menggunakan kunci 'STATE_RESULT'
    }



    override fun onCreate(savedInstanceState: Bundle?) { //objek Bundle yang digunakan untuk menyimpan data sementara saat terjadi perubahan konfigurasi
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //menentapkan layout yang akan digunakan yaitu activity_main.xml

        //Inisialisasi variabel
        edtWidth =
            findViewById(R.id.edt_width) //R.id : resource identifier, R.layout dan R.id, dapat mengakses sumber daya (resource) seperti file layout dan komponen View
        edtHeight =
            findViewById(R.id.edt_height) //findViewById untuk mengaitkan variabel edtHeight dengan elemen View pada layout
        edtLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        btnCalculate.setOnClickListener(this)  //setOnClickListener(), dapat menetukan tindakan saat button btnCalculate diklik.  saat button btnCalculate diklik, fungsi onClick() akan dipanggil pada kelas MainActivity

        if (savedInstanceState != null) { //jika tidak null akan diambil savedInstateState menggunakan kunci yang sama dan ditampilkan di tvResult
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState) //memanggil metode OnSaveInstance
        outState.putString(STATE_RESULT, tvResult.text.toString()) //nilai yang disimpan  yang terdapat pada tvResult dalam objek outState dengan kata kunci "STATE_RESULT"

        //OnSaveInstance dipanggil oleh disistem android ketika aktivitas akan berhenti dan akan disimpan
        //dimemori. Tujuannya menyimpan status aktivitas sehinga ketika activity dibuka kembali, status tersebut dapat dipulihakn kembali
    }

    //Tambahkan kode pada onclick
    override fun onClick(v: View?) { //Method onClick() di-overwrite dan menerima parameter View yang dapat bernilai null.
        if (v?.id == R.id.btn_calculate) { //Melakukan pengecekan apakah View yang diklik memiliki id yang sama dengan id yang diberikan pada button "btn_calculate"
            val inputLength = edtLength.text.toString()
                .trim() //Jika View diklik button "btn_calculate", maka kode ini akan dijalankan. Variabel inputLength akan berisi input dari EditText "edtLength" yang diambil dengan memanggil method text.toString()
            val inputWidth = edtWidth.text.toString()
                .trim()    //kemudian menghapus spasi di awal dan akhir string dengan memanggil method trim().
            val inputHeight = edtHeight.text.toString().trim()

            //Untuk mengecek inputan apakah sudah diisi
            var isEmptyFields = false //Untuk menetukan apakah filed masih kosong atau tidak

            if (inputLength.isEmpty()) { //Memeriksa masih kosong atau tidak. Jika kosong isEmtpy diubah true
                isEmptyFields = true
                edtLength.error = "Field ini tidak boleh kosong" //edtLenght untuk menampilkan pesan error
            }
            if (inputWidth.isEmpty()) {
                isEmptyFields = true
                edtWidth.error = "Field ini tidak boleh kosong"
            }
            if (inputHeight.isEmpty()) {
                isEmptyFields = true
                edtHeight.error = "Field ini tidak boleh kosong"
            }
            if (!isEmptyFields) { //memerika apakah isEmtpyFields bernilai false. Jika false program melanjutkan proses perhitungan volume. Jika tidak isEmpty akan berinai true maka proses perhitungan tidak dilakuaan karena masih ada field kosong
                val volume =
                    inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble() //dikonversi nilai kedouble kemudian dikalikan untuk menghitung volume dan disimpan dalam variabel volume .
                tvResult.text =
                    volume.toString() //menampilkan hasil vlume ke textview tvResult dan mengkonversi volume menjadi String dengan toString()
            }
        }
    }
}