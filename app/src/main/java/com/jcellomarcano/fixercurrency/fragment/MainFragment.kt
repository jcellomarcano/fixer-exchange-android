package com.jcellomarcano.fixercurrency.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jcellomarcano.fixercurrency.FixerItemAdapter
import com.jcellomarcano.fixercurrency.viewmodel.MainViewModel
import com.jcellomarcano.fixercurrency.databinding.FragmentMainBinding
import com.jcellomarcano.fixercurrency.viewmodel.MainViewModel.UpdateStatus


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
            mainViewModel.displayCurrencyView(it)
        })

        binding.currenciesRecyclerview.adapter = recycleViewAdapter

        mainViewModel.currenciesList.observe(viewLifecycleOwner, Observer {
            recycleViewAdapter.currenciesList = it
        })

        mainViewModel.currentFixerResponse.observe(viewLifecycleOwner, Observer {
            if (mainViewModel.currenciesList.value?.size == 0) {
                mainViewModel.getUpdatedCurrenciesList()
            }
        })

        binding.currenciesRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE
                    && mainViewModel.dayFromBeforeUpdateStatus == UpdateStatus.COMPLETED) {
                    mainViewModel.getCurrencyListFromDayBefore()
                }
            }
        })

        mainViewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (it != null && it.exchangeRate != "") {
                val action = MainFragmentDirections.actionMainFragmentToCurrencyFragment(it)
                findNavController(this).navigate(action)
                mainViewModel.displayCurrencyViewComplete()
            }
        })
        return binding.root
    }
}
