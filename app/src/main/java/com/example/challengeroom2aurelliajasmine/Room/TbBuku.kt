package com.example.challengeroom2aurelliajasmine.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TbBuku (
    @PrimaryKey(autoGenerate = true)
    val id_buku:Int,
    val kategori:String,
    val judul:String,
    val pengarang:String,
    val penerbit:String,
    val jumlah_buku:String
)
