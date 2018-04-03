package ru.degeneration.counter

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.widget.Button

/**
 * Created by МасловАМ on 30.03.2018
 */

class CounterViewController(val counter: Counter, val button: Button, val context: Context) : View.OnTouchListener {
    private var xDown = 0f
    private var yDown = 0f
    var change : Change? = null

    init{
        button.setOnTouchListener(this)
        refreshButtonText()
    }
    override fun onTouch(v: View, event: MotionEvent): Boolean {

        when(event.action){
            MotionEvent.ACTION_DOWN   -> {createNewChange(event)}
            MotionEvent.ACTION_MOVE   -> {updateChange(event)}
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {applyChange(event)}

        }
        return true
    }

    private fun calculateChange(event: MotionEvent): Int{
        return ((event.x - xDown) / 20f).toInt()
    }
    fun createNewChange(event: MotionEvent){
        change = Change(counter)
        xDown = event.x
        yDown = event.y
    }
    fun updateChange(event: MotionEvent){
        change?.value = calculateChange(event)
    }
    fun applyChange(event: MotionEvent){
        change?.apply()
        refreshButtonText()
    }
    fun refreshButtonText(){
        button.text = String.format(context.getString(R.string.button_text, counter.value,counter.name))
    }
}
