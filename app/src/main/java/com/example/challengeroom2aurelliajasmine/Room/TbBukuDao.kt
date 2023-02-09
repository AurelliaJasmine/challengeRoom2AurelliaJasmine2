package com.example.challengeroom2aurelliajasmine.Room
import androidx.room.*

@Dao
interface TbBukuDao {
    @Insert
    fun addTbBuku(tbBuku: TbBuku)

    @Update
    fun updateTbBuku(tbBuku: TbBuku)

    @Delete
    fun deleteTbBuku(tbBuku: TbBuku)

    @Query("SELECT*FROM TbBuku")
    fun getTbBuku(): List<TbBuku>

    @Query("SELECT*FROM TbBuku WHERE id_buku=:tbBuk_id")
    fun tampilbuku(tbBuk_id: Int): List<TbBuku>

}