package org.d3if3067.kalkulatordiskon.ui


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.d3if3067.kalkulatordiskon.R
import org.d3if3067.kalkulatordiskon.databinding.FragmentSelengkapnyaBinding

class SelengkapnyaFragment : Fragment() {

    private lateinit var binding : FragmentSelengkapnyaBinding
    private val args:SelengkapnyaFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSelengkapnyaBinding.inflate(layoutInflater,container,false)

        binding.textView2.text = getString(R.string.infoSelengkapnya, args.biaya, args.diskon, args.biayaDiskon,args.biayaSetelahDiskon)
        binding.buttonShare.setOnClickListener{
            shareData()
        }
        return binding.root
    }
    private fun shareData(){

        val message = getString(R.string.infoSelengkapnya, args.biaya, args.diskon, args.biayaDiskon,args.biayaSetelahDiskon)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }

}