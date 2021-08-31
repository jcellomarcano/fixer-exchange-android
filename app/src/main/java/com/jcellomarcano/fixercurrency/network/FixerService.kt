package com.jcellomarcano.fixercurrency.network

import com.jcellomarcano.fixercurrency.model.FixerResponse
import com.jcellomarcano.fixercurrency.model.RatesResponse
import com.jcellomarcano.fixercurrency.model.SymbolResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val ACCESS_KEY = "37d8575eafa141ba926a3fe874a74e8b"

interface FixerService {
    @GET("latest")
    fun getLatestRatesAsync(@Query("access_key") key:String = ACCESS_KEY,
                            @Query("base") base: String):
            Deferred<RatesResponse>

    @GET("{date}")
    fun getHistoricalExchangeAsync(@Path("date") date:String,
                                   @Query("access_key") key: String = ACCESS_KEY
    ):
            Deferred<FixerResponse>

    @GET("symbols")
    fun getSymbolListAsync(
        @Query("access_key") key: String = ACCESS_KEY
    ):
            Deferred<SymbolResponse>
}