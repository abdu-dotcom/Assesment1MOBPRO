package org.d3if3067.kalkulatordiskon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import org.d3if3067.kalkulatordiskon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonHitung.setOnClickListener{
            hitungDiskon()
        }
    }
    private fun hitungDiskon(){
        val biaya = binding.biayaAsliEdit.text.toString()

        //kode validasi kolom yang diisi user
        if (TextUtils.isEmpty(biaya))
        {
            Toast.makeText(this,"Masukan kolom biaya asli anda", Toast.LENGTH_LONG).show()
            return
        }
        val diskon = binding.diskonEditText.text.toString()
        if (TextUtils.isEmpty(diskon))
        {
            Toast.makeText(this,"Masukan jumlah diskon barang", Toast.LENGTH_LONG).show()
            return
        }

        val nilaiDiskon = biaya.toFloat() * (diskon.toFloat()/100)
        val biayaSetelahDiskon = biaya.toFloat() - nilaiDiskon

        binding.hasilBiayaSetelah.text = biayaSetelahDiskon.toString()
        binding.textView6.text = nilaiDiskon.toString()
    }
}