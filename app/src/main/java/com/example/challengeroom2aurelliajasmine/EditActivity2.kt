package com.example.challengeroom2aurelliajasmine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challengeroom2aurelliajasmine.Room.TbPeminjam
import com.example.challengeroom2aurelliajasmine.Room.dbPerpus
import kotlinx.android.synthetic.main.activity_edit2.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity2 : AppCompatActivity() {

    val db by lazy { dbPerpus(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit2)
        simpandata()
    }

    fun simpandata() {
        btnSave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.tbPeminjamDao().addTbPinjam(
                    TbPeminjam(
                        etId_pinjam.text.toString().toInt(),
                        etNama_peminjam.text.toString(),
                        etTgl_pinjam.text.toString(),
                        etTgl_kembali.text.toString(),
                        etJudul.text.toString(),
                        etjml_PinjamBuku.text.toString()
                    )
                )
                finish()
            }
        }
    }

}