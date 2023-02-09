package com.example.challengeroom2aurelliajasmine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengeroom2aurelliajasmine.Room.Constant
import com.example.challengeroom2aurelliajasmine.Room.TbBuku
import com.example.challengeroom2aurelliajasmine.Room.dbPerpus
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val db by lazy { dbPerpus(this) }
    lateinit var bukuAdapter : BukuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        halEdit()
        halEdit2()
        setupRV()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val buku = db.tbBukuDao().getTbBuku()
            Log.d("MainActivity", "dbResponse: $buku")
            withContext(Dispatchers.Main) {
                bukuAdapter.setData(buku)
            }
        }
    }

    private fun halEdit() {
        btnInput.setOnClickListener{
            intentEdit(0,Constant.TYPE_CREATE)
        }
    }

    private fun halEdit2() {
        btnInput.setOnClickListener{
            startActivity(Intent(this,EditActivity2::class.java))
        }
    }

    private fun intentEdit(tbBukuID: Int, intentType: Int) {
        startActivity(
            Intent(applicationContext,EditActivity::class.java)
                .putExtra("intent_id",tbBukuID)
                .putExtra("intent_type",intentType)
        )
    }

    fun setupRV () {
        bukuAdapter = BukuAdapter(arrayListOf(), object : BukuAdapter.OnAdapterListener{
            override fun onClick(tbBuku: TbBuku) {
                intentEdit(tbBuku.id_buku,Constant.TYPE_READ)
            }

            override fun onUpdate(tbBuku: TbBuku) {
                intentEdit(tbBuku.id_buku,Constant.TYPE_UPDATE)
            }

            override fun onDelete(tbBuku: TbBuku) {
                hapusBuku(tbBuku)
            }

        })

        list_RV.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = bukuAdapter
        }
    }

    fun hapusBuku(tbBuku: TbBuku) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin Hapus ${tbBuku.judul}?")
            setNegativeButton("NO") {dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("YES") {dialogInterface, i ->
                CoroutineScope(Dispatchers.IO).launch {
                    db.tbBukuDao().deleteTbBuku(tbBuku)
                    dialogInterface.dismiss()
                    loadData()
                }
            }
        }
        alertDialog.show()
    }
}
