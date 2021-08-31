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
import com.jcellomarcano.fixercurrency.model.Symbol
import com.jcellomarcano.fixercurrency.viewmodel.CurrencyViewModel
import com.jcellomarcano.fixercurrency.viewmodel.CurrencyViewModelFactory

class CurrencyFragment : Fragment() {
    private val args: CurrencyFragmentArgs by navArgs()
    lateinit var currencyViewModel: CurrencyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCurrencyBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        val symbol: Symbol = args.selectedProperty
        val currency = Currency(symbol.symbol, "3.1", "26/08/2021")


        val viewModelFactory = CurrencyViewModelFactory(currency, symbol)
        currencyViewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(CurrencyViewModel::class.java)
        binding.viewModel = currencyViewModel
        binding.exchangeAmount.addTextChangedListener {
            currencyViewModel.onRequestCurrencyConversion(it.toString())
        }
        return binding.root
    }

}