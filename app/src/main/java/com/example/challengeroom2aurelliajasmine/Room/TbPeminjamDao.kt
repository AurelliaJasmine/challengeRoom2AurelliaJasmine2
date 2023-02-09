package com.example.challengeroom2aurelliajasmine.Room

import androidx.room.*

@Dao
interface TbPeminjamDao {
    @Insert
    fun addTbPinjam(tbpeminjam: TbPeminjam)

    @Update
    fun updateTbPinjam(tbpeminjam: TbPeminjam)

    @Delete
    fun deleteTbPinjam(tbpeminjam: TbPeminjam)

    @Query("SELECT*FROM TbPeminjam")
    fun getTbPinjam(): List<TbPeminjam>

    @Query("SELECT*FROM TbPeminjam WHERE id_pinjam=:tbPinjam_id")
    fun tampilId(tbPinjam_id: Int): List<TbPeminjam>
}