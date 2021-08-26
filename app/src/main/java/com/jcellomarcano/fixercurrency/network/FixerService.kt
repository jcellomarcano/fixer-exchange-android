package com.jcellomarcano.fixercurrency.network

import com.jcellomarcano.fixercurrency.model.FixerResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val ACCESS_KEY = "37d8575eafa141ba926a3fe874a74e8b"

interface FixerService {
    @GET("latest")
    fun getLatestExchangesAsync(@Query("access_key") key:String = ACCESS_KEY):
            Deferred<FixerResponse>

    @GET("{date}")
    fun getHistoricalExchangeAsync(@Path("date") date:String,
                                   @Query("access_key") key: String = ACCESS_KEY
    ):
            Deferred<FixerResponse>
}