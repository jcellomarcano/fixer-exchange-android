package com.jcellomarcano.fixercurrency.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.jcellomarcano.fixercurrency.databinding.FragmentCurrencyBinding
import com.jcellomarcano.fixercurrency.model.Currency
import com.jcellomarcano.fixercurrency.viewmodel.CurrencyViewModel
import com.jcellomarcano.fixercurrency.viewmodel.CurrencyViewModelFactory

class CurrencyFragment : Fragment() {
    private val args: CurrencyFragmentArgs by navArgs()
    lateinit var currencyFragmentViewModel: CurrencyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCurrencyBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        val currency: Currency = args.selectedProperty

        val viewModelFactory = CurrencyViewModelFactory(currency)
        currencyFragmentViewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(CurrencyViewModel::class.java)
        binding.viewModel = currencyFragmentViewModel
        binding.exchangeAmount.addTextChangedListener {
            currencyFragmentViewModel.onRequestCurrencyConversion(it.toString())
        }
        return binding.root
    }

}