package ru.degeneration.counter.ru.degeneration.counter.activities


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.PowerManager
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import ru.degeneration.counter.CounterViewController
import ru.degeneration.counter.R
import ru.degeneration.counter.ru.degeneration.counter.model.Counter
import ru.degeneration.counter.ru.degeneration.counter.model.Game


class MainActivity : AppCompatActivity() {
    private var game: Game = Game()
    private val screenWakeLocker: PowerManager.WakeLock by lazy {
        (getSystemService(Context.POWER_SERVICE) as PowerManager)
                .newWakeLock(PowerManager.FULL_WAKE_LOCK, "")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        screenWakeLocker.acquire()
    }

    override fun onPause() {
        super.onPause()
        screenWakeLocker.release()
    }

    fun bindCounter(counter: Counter, color: Int) {
        val button = Button(this)
        button.background.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(//todo move it into xml
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f
        )
        button.layoutParams = params
        CounterViewController(counter, button, this)
        findViewById<LinearLayout>(R.id.CountersLayout).addView(button)
        findViewById<ConstraintLayout>(R.id.MainLayout).invalidate()
    }



    fun addCounter(view: View) {
        val intent = Intent(this, CreateCounterActivity::class.java)
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0 && data != null && resultCode == Activity.RESULT_OK) {
            val name = data.getStringExtra("name")
            val color = data.getIntExtra("color", 0xA17790)
            val counter = Counter(name)
            game.registerCounter(counter)
            bindCounter(counter, color)
        } else {
        }
    }

}
