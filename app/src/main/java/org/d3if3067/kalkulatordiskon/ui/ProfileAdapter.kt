package org.d3if3067.kalkulatordiskon.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if3067.kalkulatordiskon.R
import org.d3if3067.kalkulatordiskon.data.dataDiri
import org.d3if3067.kalkulatordiskon.databinding.ItemProfileBinding
import org.d3if3067.kalkulatordiskon.network.DataApi

class ProfileAdapter : RecyclerView.Adapter<ProfileAdapter.ViewHolder>()  {

    private val data = mutableListOf<dataDiri>()

    fun updateData(newData: List<dataDiri>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemProfileBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(data: dataDiri){
        with(binding) {
            textView7.text = data.nama
            textView8.text = data.jurusan
            textView9.text = data.kampus
            textView10.text = data.noTelp
            textView11.text = data.Desc
            Glide.with(imageViewProfile.context)
                    .load(DataApi.getImgProfile(data.img))
                    .error(R.drawable.ic_baseline_broken_image_24)
                    .into(imageViewProfile)
        }
    }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProfileBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}