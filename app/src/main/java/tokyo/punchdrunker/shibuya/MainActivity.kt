package tokyo.punchdrunker.shibuya

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tokyo.punchdrunker.shibuya.service.ConnpassService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import timber.log.Timber.DebugTree
import tokyo.punchdrunker.shibuya.response.ListEventsResponse


class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onStart() {
        super.onStart()

        launch { getEvents() }
    }

    suspend fun getEvents() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://connpass.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create<ConnpassService>(ConnpassService::class.java)

        service.listEvents(1256).enqueue(object : Callback<ListEventsResponse> {
            override fun onResponse(call: Call<ListEventsResponse>?, response: Response<ListEventsResponse>?) {
                if (response != null && response.isSuccessful) {
                    val body = response.body()
                    body?.events?.forEach { event ->
                        Timber.d(event.title)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "parse error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ListEventsResponse>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
