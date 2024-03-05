package com.duktiv.gameku

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class GameAdapter(private val listGame: ArrayList<Game>) : RecyclerView.Adapter<GameAdapter.ListHolder>() {

    companion object {
        const val KEY_GAME = "key_game"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return ListHolder(view)
    }

    class ListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imgPoto: ImageView = itemView.findViewById(R.id.img_item)
    val tvNama: TextView = itemView.findViewById(R.id.text_judul)
    val tvIsi: TextView = itemView.findViewById(R.id.text_desc)
    }

    override fun getItemCount(): Int = listGame.size


    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        val (poto, nama, desc) = listGame[position]
        holder.imgPoto.setImageResource(poto)
        holder.tvNama.text = nama
        holder.tvIsi.text = desc

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra(KEY_GAME, listGame[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }
}
