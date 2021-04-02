package org.d3if3067.kalkulatordiskon.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.d3if3067.kalkulatordiskon.R
import org.d3if3067.kalkulatordiskon.databinding.FragmentDiskonBinding

class DiskonFragment : Fragment() {
    private lateinit var binding : FragmentDiskonBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       binding = FragmentDiskonBinding. inflate(layoutInflater,container,false)
        binding.buttonHitung.setOnClickListener{
            hitungDiskon()
        }

        binding.buttonSelengkap.setOnClickListener{
            val biaya = binding.biayaAsliEdit.text.toString()
            val diskon = binding.diskonEditText.text.toString()
            val biayaSetelahDiskon = binding.hasilBiayaSetelah.text.toString()
            val biayaDiskon = binding.textView6.text.toString()
            val action = DiskonFragmentDirections.
            actionDiskonFragmentToSelengkapnyaFragment(biaya,diskon,biayaSetelahDiskon,biayaDiskon)

           findNavController().navigate(action)
        }
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.pilihan_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_about)
        {
            findNavController().navigate(
                R.id.action_diskonFragment_to_tentangFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
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