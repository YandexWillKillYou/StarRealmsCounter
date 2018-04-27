package ru.degeneration.counter.ru.degeneration.counter.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_create_counter.*
import ru.degeneration.counter.R

class CreateCounterActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    val hueBar by lazy {findViewById<SeekBar>(R.id.HueBar)}
    val saturationBar by lazy {findViewById<SeekBar>(R.id.SaturationBar)}
    val valueBar by lazy {findViewById<SeekBar>(R.id.ValueBar)}
    val commitButton by lazy {findViewById<Button>(R.id.CommitNewCounterButton)}

    var colorOfNewCounter : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_counter)
        hueBar.setOnSeekBarChangeListener(this)
        saturationBar.setOnSeekBarChangeListener(this)
        valueBar.setOnSeekBarChangeListener(this)
        commitButton.setOnClickListener(this)
        setRandomDefaultColor()
    }

    override fun onClick(view: View){
        val commitButton = findViewById<Button>(R.id.CommitNewCounterButton)
        if(commitButton == view){
            val name = findViewById<EditText>(R.id.Name).text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra("name", name)
            resultIntent.putExtra("color", colorOfNewCounter)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        updateColorOfButton()
    }
    private fun updateColorOfButton(){
        val hue = hueBar.progress.toFloat()
        val saturation = saturationBar.progress/256f
        val value = valueBar.progress/256f
        colorOfNewCounter = Color.HSVToColor(floatArrayOf(hue, saturation, value))
        commitButton.background.setColorFilter(colorOfNewCounter, PorterDuff.Mode.MULTIPLY)
    }
    private fun setRandomDefaultColor(){
        val minHue=0
        val maxHue=360
        val minSaturation=100
        val maxSaturation=255
        val minValue=100
        val maxValue=255
        val rndHue = intervalRandom(minHue, maxHue)
        val rndSaturation = intervalRandom(minSaturation, maxSaturation)
        val rndValue = intervalRandom(minValue, maxValue)
        HueBar.progress = rndHue
        SaturationBar.progress = rndSaturation
        ValueBar.progress = rndValue
        findViewById<LinearLayout>(R.id.CreateCounterLayout).invalidate()
    }
    fun intervalRandom(min:Int, max:Int): Int {
        return (min+(max-min)*Math.random()).toInt()
    }
    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        //no action required
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        //no action required
    }

}
