package com.jcellomarcano.fixercurrency.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.jcellomarcano.fixercurrency.adapter.FixerItemAdapter
import com.jcellomarcano.fixercurrency.databinding.FragmentMainBinding
import com.jcellomarcano.fixercurrency.viewmodel.MainViewModel


class MainFragment : Fragment() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewmodel = mainViewModel

        val recycleViewAdapter = FixerItemAdapter(FixerItemAdapter.OnClickListener {
            mainViewModel.onSymbolSelected(it)
        })

        binding.currenciesRecyclerview.adapter = recycleViewAdapter

        mainViewModel.symbolList.observe(viewLifecycleOwner, Observer {
            recycleViewAdapter.symbolsList = it
        })

        mainViewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            it?.let {
                val action = MainFragmentDirections.actionMainFragmentToCurrencyFragment(it)
                findNavController(this).navigate(action)
                mainViewModel.displayCurrencyViewComplete()
            }

        })

        return binding.root
    }
}
