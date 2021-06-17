package org.d3if3067.kalkulatordiskon.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if3067.kalkulatordiskon.R
import org.d3if3067.kalkulatordiskon.databinding.FragmentDiskonBinding
import org.d3if3067.kalkulatordiskon.db.hitungDiskonDb

class DiskonFragment : Fragment() {
    private lateinit var binding : FragmentDiskonBinding
    private val viewModel: DiskonViewModel by lazy {
        val db = hitungDiskonDb.getInstance(requireContext())
        val factory = DiskonViewModelFactory(db.dao)
        ViewModelProvider(this, factory).get(DiskonViewModel::class.java)
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.gethasilDiskon().observe(viewLifecycleOwner,{
            if (it == null) return@observe
            binding.hasilBiayaSetelah.text = it.biayaSetelahDiskon.toString()
            binding.textView6.text = it.biayaDiskon.toString()
        })


    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.pilihan_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_about -> {
                findNavController().navigate(R.id.action_diskonFragment_to_tentangFragment)
                return true
            }
            R.id.menu_histori -> {
                findNavController().navigate(R.id.action_diskonFragment_to_hisotriFragment)
                return true
            }
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

        viewModel.hitungDiskon(biaya, diskon);
    }


}