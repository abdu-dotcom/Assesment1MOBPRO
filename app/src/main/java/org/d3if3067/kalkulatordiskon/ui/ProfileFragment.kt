package org.d3if3067.kalkulatordiskon.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if3067.kalkulatordiskon.databinding.FragmentProfileBinding
import org.d3if3067.kalkulatordiskon.network.ApiStatus

class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by lazy {
        ViewModelProvider(this).get(ProfileViewModel::class.java)
    }


    private lateinit var binding : FragmentProfileBinding
    private lateinit var myAdapter : ProfileAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        myAdapter = ProfileAdapter()

        with(binding.recycleviewProfile){
            addItemDecoration(DividerItemDecoration(context,
                    RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
        })
        viewModel.getStatus().observe(viewLifecycleOwner,{
            updateProgres(it)
        })
    }

    private fun updateProgres(status: ApiStatus){
        when(status){
            ApiStatus.Loading ->{
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.Succes -> {
                binding.progressBar.visibility = View.GONE
            }
            ApiStatus.Failure -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }

        }
    }



}