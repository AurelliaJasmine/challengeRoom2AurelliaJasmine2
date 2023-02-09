package com.example.challengeroom2aurelliajasmine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.challengeroom2aurelliajasmine.Room.Constant
import com.example.challengeroom2aurelliajasmine.Room.TbBuku
import com.example.challengeroom2aurelliajasmine.Room.dbPerpus
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    val db by lazy { dbPerpus(this) }
    private var tbBukuID : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setupView()
        tombolPerintah()
        tbBukuID = intent.getIntExtra("intent_id",tbBukuID)
        Toast.makeText(this,tbBukuID.toString(),Toast.LENGTH_SHORT).show()
    }

    fun setupView() {

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type",0)
        when(intentType) {
            Constant.TYPE_CREATE -> {
                btnUpdate.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btnSave.visibility = View.GONE
                btnUpdate.visibility = View.GONE
                etId.visibility = View.GONE
                tampilBuku()
            }
            Constant.TYPE_UPDATE -> {
                btnSave.visibility = View.GONE
                etId.visibility = View.GONE
                tampilBuku()
            }
        }
    }

    fun tombolPerintah() {
        btnSave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.tbBukuDao().addTbBuku(
                    TbBuku(
                        etId.text.toString().toInt(),
                        etKategori.text.toString(),
                        etJudul.text.toString(),
                        etPengarang.text.toString(),
                        etPenerbit.text.toString(),
                        etJml_buku.text.toString()
                    )
                )
                finish()
            }
        }

        btnUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.tbBukuDao().updateTbBuku(
                    TbBuku(
                        tbBukuID,
                        etKategori.text.toString(),
                        etJudul.text.toString(),
                        etPengarang.text.toString(),
                        etPenerbit.text.toString(),
                        etJml_buku.text.toString()
                    )
                )
                finish()
            }
        }
    }

    fun tampilBuku(){
        tbBukuID = intent.getIntExtra("intent_id",0)
        CoroutineScope(Dispatchers.IO).launch {
            val buku = db.tbBukuDao().tampilbuku( tbBukuID)[0]
            val dataId : String = buku.id_buku.toString()
            etId.setText(dataId)
            etKategori.setText(buku.kategori)
            etJudul.setText(buku.judul)
            etPengarang.setText(buku.pengarang)
            etPenerbit.setText(buku.penerbit)
            etJml_buku.setText(buku.jumlah_buku)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}