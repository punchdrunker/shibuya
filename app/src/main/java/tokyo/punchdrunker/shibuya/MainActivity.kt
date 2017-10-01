package tokyo.punchdrunker.shibuya

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gildor.coroutines.retrofit.await
import timber.log.Timber
import tokyo.punchdrunker.shibuya.service.ConnpassService


class MainActivity : AppCompatActivity() {
    val connpassService = Retrofit.Builder()
            .baseUrl("https://connpass.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<ConnpassService>(ConnpassService::class.java)

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

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onStart() {
        super.onStart()

        launch { getEvents() }
    }

    // using kotlin-coroutines-retrofit
    suspend fun getEvents() {
        val response = connpassService.listEvents(1256).await()
        response.events.forEach { event ->
            Timber.d(event.title)
        }
    }
}
