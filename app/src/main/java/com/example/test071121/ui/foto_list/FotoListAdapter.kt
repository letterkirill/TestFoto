package com.example.test071121.ui.foto_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test071121.R

class FotoListAdapter(private val layoutInflater: LayoutInflater, private val clickListener: OnFotoClickListener?) : RecyclerView.Adapter<FotoListAdapter.FotoListViewHolder>() {

    private val items = ArrayList<String>()

    class FotoListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val imageFoto: ImageView = itemView.findViewById(R.id.image_foto)

        fun bind(url: String, clickListener: OnFotoClickListener?){
            Glide.with(imageFoto.context)
                .load(url)
                .placeholder(R.drawable.ic_baseline_insert_photo_24)
                .error(R.drawable.ic_baseline_error_outline_24)
                .centerCrop()
                .into(imageFoto)

            imageFoto.setOnClickListener{(clickListener?.onClickFoto(url))}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FotoListViewHolder {
        return FotoListViewHolder(layoutInflater.inflate(R.layout.item_foto, parent, false))
    }

    override fun onBindViewHolder(holder: FotoListViewHolder, position: Int) {
        holder.bind(items[position], clickListener)
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(urlList: List<String>){
        items.clear()
        items.addAll(urlList)
        notifyDataSetChanged()
    }

    interface OnFotoClickListener {
        fun onClickFoto(url: String)
    }
}
