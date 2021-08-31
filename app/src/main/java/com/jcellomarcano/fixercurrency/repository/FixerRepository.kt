package com.jcellomarcano.fixercurrency.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jcellomarcano.fixercurrency.model.FixerResponse
import com.jcellomarcano.fixercurrency.model.RatesX
import com.jcellomarcano.fixercurrency.model.Symbol
import com.jcellomarcano.fixercurrency.network.FixerService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val TAG = "FixerRepository"
object FixerRepository: CurrencyRepository {
    private const val FIXER_BASE_URL = "http://data.fixer.io/api/"
    private val fixerService: FixerService
    private val fixer = MutableLiveData<FixerResponse>()
    val job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

    init {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        fixerService = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(FIXER_BASE_URL)
            .build()
            .create(FixerService::class.java)
    }

    fun getFixerResponse(date:String):MutableLiveData<FixerResponse>{
        coroutineScope.launch {
            val getPropertyDeferred = fixerService.getHistoricalExchangeAsync(date)
            try {
                val propertyResult = getPropertyDeferred.await()
                fixer.value = propertyResult
            } catch (e: Exception) {
                Log.d(TAG, "getFixerResponse: " + e.message)
            }
        }
        return fixer
    }

    override suspend fun getSymbolList(): List<Symbol> {
        val symbolList: MutableList<Symbol> = mutableListOf()
        try {
            val getPropertyDeferred = fixerService.getSymbolListAsync()
            val propertyResult = getPropertyDeferred.await()
            propertyResult.symbols.forEach{
                val symbol = Symbol(it.key,it.value)
                symbolList.add(symbol)
            }

        } catch (e: Exception) {
            Log.d(TAG, "getFixerResponse: " + e.message)
        }
        return symbolList
    }

    override suspend fun getLastRates(symbol: String): List<RatesX> {
        val ratesList: MutableList<RatesX> = mutableListOf()
        try {
            val getPropertyDeferred = fixerService.getLatestRatesAsync(base = symbol)
            val propertyResult = getPropertyDeferred.await()
            propertyResult.rates.forEach {
                val rate = RatesX(it.key, it.value)
                ratesList.add(rate)
            }

        } catch (e: Exception) {
            Log.d(TAG, "getFixerResponse: " + e.message)
        }
        return ratesList
    }
}