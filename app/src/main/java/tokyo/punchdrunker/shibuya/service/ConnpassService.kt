package tokyo.punchdrunker.shibuya.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import tokyo.punchdrunker.shibuya.response.ListEventsResponse

/**
 * Created by punchdrunker on 2017/09/30.
 */
interface ConnpassService {
    @GET(PATH + "/event")
    fun listEvents(@Query("series_id") seriesId: Int, @Query("count") count: Int): Call<ListEventsResponse>

    companion object {
        private const val PATH = "/api/v1"
    }
}
