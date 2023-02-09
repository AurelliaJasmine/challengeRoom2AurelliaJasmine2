package com.example.challengeroom2aurelliajasmine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.challengeroom2aurelliajasmine.Room.TbBuku
import kotlinx.android.synthetic.main.activity_buku_adapter.view.*

class BukuAdapter (private val buku: ArrayList<TbBuku>, private val listener: OnAdapterListener)
    : RecyclerView.Adapter <BukuAdapter.BukuViewHolder>() {

    class BukuViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuViewHolder {
        return BukuViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_buku_adapter,parent,false)
        )
    }

    override fun onBindViewHolder(holder: BukuViewHolder, position: Int) {
        val TbBuk = buku [position]
        holder.view.textKategori.text = TbBuk.kategori
        holder.view.textJudul.text = TbBuk.judul
        holder.view.textKategori.setOnClickListener{
            listener.onClick(TbBuk)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(TbBuk)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(TbBuk)
        }
    }

    override fun getItemCount() = buku.size

    fun setData(list: List <TbBuku>){
        buku.clear()
        buku.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick (tbBuku: TbBuku)
        fun onUpdate (tbBuku: TbBuku)
        fun onDelete (tbBuku: TbBuku)
    }

}
