package org.d3if3067.kalkulatordiskon.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.d3if3067.kalkulatordiskon.data.HitungDiskon
import org.d3if3067.kalkulatordiskon.databinding.ItemHistoriBinding
import org.d3if3067.kalkulatordiskon.db.DiskonEntity

class HistoriAdapter : RecyclerView.Adapter<HistoriAdapter.ViewHolder>() {


    private val data = mutableListOf<DiskonEntity>()
    fun updateData(newData: List<DiskonEntity>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(
            private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DiskonEntity) = with(binding) {

            val hasilDiskon = HitungDiskon.Hitung(item)

            VHBiayaAwal.text = hasilDiskon.biaya.toString();
            VHDiskon.text = hasilDiskon.biayaDiskon.toString();
            VHBiayaAkhir.text = hasilDiskon.biayaSetelahDiskon.toString();
        }
    }
}