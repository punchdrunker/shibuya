package tokyo.punchdrunker.shibuya

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gildor.coroutines.retrofit.await
import tokyo.punchdrunker.shibuya.entity.ConnpassEvent
import tokyo.punchdrunker.shibuya.service.ConnpassService


class MainActivity : AppCompatActivity() {
    private val connpassService = Retrofit.Builder()
            .baseUrl("https://connpass.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<ConnpassService>(ConnpassService::class.java)

    private val listController = ListEventController()
    private var eventList = arrayListOf<ConnpassEvent>()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list_event_recycler_view.adapter = listController.adapter
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onStart() {
        super.onStart()

        launch(UI) { getEvents() }
    }

    suspend private fun getEvents() {
        eventList = arrayListOf<ConnpassEvent>()

        val response = connpassService.listEvents(1256, 100).await()
        response.events.forEach { event ->
            eventList.add(event)
        }
        updateData()
    }

    private fun updateData() {
        listController.setData(eventList)
    }
}
