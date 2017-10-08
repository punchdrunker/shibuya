package tokyo.punchdrunker.shibuya.service

import kotlinx.coroutines.experimental.runBlocking
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gildor.coroutines.retrofit.await

class ConnmassServiceTest {

    private var server = MockWebServer()

    @After
    @Throws(Exception::class)
    fun tearDown() = server.shutdown()

    @Test
    fun getEvents() {
        server = MockWebServer()

        val response = createMockResponse()
                .setBody(MOCKED_EVENTS_RESPOINSE)
        server.enqueue(response)
        val url = server.url("/")
        val service = createService(url)

        val actualResponse = runBlocking {
            service.listEvents(1256, 100).await()
        }
        Assert.assertThat(actualResponse.results_available, `is`(2))
        Assert.assertThat(actualResponse.results_start, `is`(1))
        Assert.assertThat(actualResponse.results_returned, `is`(2))

        val events = actualResponse.events
        Assert.assertThat(events.count(), `is`(2))
        Assert.assertThat(events[0].event_url, `is`("https://shibuya-apk.connpass.com/event/68094/"))
        Assert.assertThat(events[0].event_id, `is`(68094))
        Assert.assertThat(events[0].catch, `is`("\u6e0b\u8c37\u3092\u4e2d\u5fc3\u306b\u6d3b\u52d5\u3059\u308bAndroid\u30a2\u30d7\u30ea\u958b\u767a\u8005\u30b3\u30df\u30e5\u30cb\u30c6\u30a3"))
    }

    private fun createMockResponse() = MockResponse()
            .addHeader("Content-Type", "application/json")
            .setResponseCode(200)

    private fun createService(url: HttpUrl) = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<ConnpassService>(ConnpassService::class.java)

    companion object {
        private const val MOCKED_EVENTS_RESPOINSE = """
{"results_returned": 2,
 "events": [
   {"event_url": "https://shibuya-apk.connpass.com/event/68094/",
    "event_type": "participation",
    "owner_nickname": "punchdrunker",
    "series": {"url": "https://shibuya-apk.connpass.com/", "id": 1256, "title": "Shibuya.apk"},
    "updated_at": "2017-10-06T15:34:35+09:00",
    "lat": "35.665520200000",
    "started_at": "2017-10-27T19:30:00+09:00",
    "hash_tag": "shibuya_apk",
    "title": "shibuya.apk #19",
    "event_id": 68094,
    "lon": "139.702665000000",
    "waiting": 0,
    "limit": 98,
    "owner_id": 29591,
    "owner_display_name": "punchdrunker",
    "description": "<h1>\u958b\u50ac\u6982\u8981</h1>\n",
    "catch": "\u6e0b\u8c37\u3092\u4e2d\u5fc3\u306b\u6d3b\u52d5\u3059\u308bAndroid\u30a2\u30d7\u30ea\u958b\u767a\u8005\u30b3\u30df\u30e5\u30cb\u30c6\u30a3",
    "accepted": 68,
    "ended_at": "2017-10-27T22:00:00+09:00",
    "place": "\u30b9\u30de\u30fc\u30c8\u30cb\u30e5\u30fc\u30b9\u682a\u5f0f\u4f1a\u793e"},
   {"event_url": "https://shibuya-apk.connpass.com/event/64610/",
    "event_type": "participation",
     "owner_nickname": "punchdrunker",
     "series": {"url": "https://shibuya-apk.connpass.com/", "id": 1256, "title": "Shibuya.apk"},
     "updated_at": "2017-09-15T19:18:51+09:00",
     "lat": "35.658030700000",
     "started_at": "2017-09-15T19:30:00+09:00",
     "hash_tag": "shibuya_apk",
     "title": "shibuya.apk #18",
     "event_id": 64610,
     "lon": "139.708864200000",
     "waiting": 0,
     "limit": 97,
     "owner_id": 29591,
     "owner_display_name": "punchdrunker",
     "description": "<h1>\u958b\u50ac\u6982\u8981</h1>\n<p>shibuya.apk\u306f\u6e0b\u8c37\u3092\u4e2d\u5fc3\u306b\u6d3b\u52d5\u3059\u308bAndroid\u30a2\u30d7\u30ea\u958b\u767a\u30b3\u30df\u30e5\u30cb\u30c6\u30a3\u3067\u3059\u3002<br>\n\u4eca\u56de\u306718\u56de\u76ee\u306b\u306a\u308a\u307e\u3059\u3002\n\u30a8\u30f3\u30b8\u30cb\u30a2\u540c\u58eb\u306e\u30b3\u30df\u30e5\u30cb\u30b1\u30fc\u30b7\u30e7\u30f3\u624b\u6bb5\u3068\u3057\u3066\u662f\u975e\u3054\u5fdc\u52df\u304f\u3060\u3055\u3044\uff01  </p>", "address": "\u6771\u4eac\u90fd\u6e0b\u8c37\u533a\u67711-2-20(\u4f4f\u53cb\u4e0d\u52d5\u7523\u6e0b\u8c37\u30d5\u30a1\u30fc\u30b9\u30c8\u30bf\u30ef\u30fc7F)",
     "catch": "\u6e0b\u8c37\u3092\u4e2d\u5fc3\u306b\u6d3b\u52d5\u3059\u308bAndroid\u30a2\u30d7\u30ea\u958b\u767a\u8005\u30b3\u30df\u30e5\u30cb\u30c6\u30a3",
     "accepted": 79,
     "ended_at": "2017-09-15T22:00:00+09:00",
     "place": "\u682a\u5f0f\u4f1a\u793e\u30df\u30af\u30b7\u30a3 \u30b3\u30e9\u30dc\u30ec\u30fc\u30b7\u30e7\u30f3\u30eb\u30fc\u30e0"}
    ],
  "results_start": 1,
  "results_available": 2}
    """
    }
}