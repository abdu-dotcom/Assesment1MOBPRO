package org.d3if3067.kalkulatordiskon.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if3067.kalkulatordiskon.databinding.FragmentDiskonBinding

class DiskonFragment : Fragment() {
    private lateinit var binding : FragmentDiskonBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

       binding = FragmentDiskonBinding. inflate(layoutInflater,container,false)
        binding.buttonHitung.setOnClickListener{
            hitungDiskon()
        }
        binding.buttonSelengkap.setOnClickListener{view : View ->
            val biaya = binding.biayaAsliEdit.text.toString()
            val diskon = binding.diskonEditText.text.toString()
            val biayaSetelahDiskon = binding.hasilBiayaSetelah.text.toString()
            val biayaDiskon = binding.textView6.text.toString()
            val action = DiskonFragmentDirections.
            actionDiskonFragmentToSelengkapnyaFragment(biaya,diskon,biayaSetelahDiskon,biayaDiskon)

           findNavController().navigate(action)
        }
        return binding.root
    }
    private fun hitungDiskon(){
        val biaya = binding.biayaAsliEdit.text.toString()

        //kode validasi kolom yang diisi user
        if (TextUtils.isEmpty(biaya))
        {
            Toast.makeText(context,"Masukan kolom biaya asli anda", Toast.LENGTH_LONG).show()
            return
        }
        val diskon = binding.diskonEditText.text.toString()
        if (TextUtils.isEmpty(diskon))
        {
            Toast.makeText(context,"Masukan jumlah diskon barang", Toast.LENGTH_LONG).show()
            return
        }

        val nilaiDiskon = biaya.toFloat() * (diskon.toFloat()/100)
        val biayaSetelahDiskon = biaya.toFloat() - nilaiDiskon

        binding.hasilBiayaSetelah.text = biayaSetelahDiskon.toString()
        binding.textView6.text = nilaiDiskon.toString()
    }

}