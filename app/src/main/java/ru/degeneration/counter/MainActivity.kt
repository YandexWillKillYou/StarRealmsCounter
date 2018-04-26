package ru.degeneration.counter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PowerManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout


class MainActivity : AppCompatActivity() {
    private var game: Game = Game()
    private val screenWakeLocker: PowerManager.WakeLock by lazy {
                (getSystemService(Context.POWER_SERVICE) as PowerManager)
                .newWakeLock(PowerManager.FULL_WAKE_LOCK, "")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        game.createMockCounters()
        bindMockCounters()
    }

    override fun onResume(){
        super.onResume()
        screenWakeLocker.acquire()
    }

    override fun onPause() {
        super.onPause()
        screenWakeLocker.release()
    }

    fun bindCounter(counter: Counter){
        val button = Button(this)
        val params :LinearLayout.LayoutParams= LinearLayout.LayoutParams(//todo move it into xml
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f
        )
        button.layoutParams=params
        CounterViewController(counter, button,this)
        (findViewById<LinearLayout>(R.id.CountersLayout)).addView(button)
    }

    private fun bindMockCounters(){
        for(counter in game.counters){
            bindCounter(counter)
        }
    }

    fun createCounter(view: View) {
        val intent = Intent(this@MainActivity, CreateCounterActivity::class.java)
        startActivity(intent)
    }

}
