package ru.degeneration.counter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private val counters = ArrayList<Counter>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createMockCounters()
        bindMockCounters()
    }

    private fun createMockCounters(){
        val nik = Counter("Никита")
        val art = Counter("Артем")
        nik.value = 75
        art.value = 75

        counters.add(nik)
        counters.add(art)
    }

    private fun bindMockCounters(){
        CounterView(counters[0], findViewById(R.id.button0), applicationContext)
        CounterView(counters[1], findViewById(R.id.button1), applicationContext)
    }

}
