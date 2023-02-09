package com.example.challengeroom2aurelliajasmine.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TbPeminjam (
    @PrimaryKey(autoGenerate = true)
    val id_pinjam:Int,
    val nama_peminjam:String,
    val tanggal_pinjam:String,
    val tanggal_kembali:String,
    val judul_buku:String,
    val jumlah_PinjamBuku:String
)