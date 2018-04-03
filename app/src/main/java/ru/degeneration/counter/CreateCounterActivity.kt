package ru.degeneration.counter

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class CreateCounterActivity : AppCompatActivity() {
    var colorOfNewCounter : Color = Color()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_counter)

    }

}
