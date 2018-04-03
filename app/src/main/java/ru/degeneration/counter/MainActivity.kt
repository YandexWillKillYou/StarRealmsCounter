package ru.degeneration.counter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.LinearLayout


class MainActivity : AppCompatActivity() {
    val game: Game = Game()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game.createMockCounters()
        bindMockCounters()
    }

    fun bindCounter(counter: Counter){
        val button = Button(this)
        CounterViewController(counter, button,this)
        (resources.getLayout(R.layout.activity_main) as LinearLayout).addView(button)
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
