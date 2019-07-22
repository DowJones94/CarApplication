package com.example.carapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :  AppCompatActivity(), CarTaskCallback{

    private var toast: Toast? = null

    override fun doToast() {
        if (toast == null) {
            toast = Toast.makeText(this, "Двигатель уже запущен", Toast.LENGTH_SHORT)
        }
        toast!!.show()
    }

    override fun setCarStateText(stateText: String) {
        textCarState?.text =stateText
    }

    override fun setCarTimeLeftText(timeLeftText: String) {
        textCarTimeLeft?.text =timeLeftText
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.instance.carWorkPresenterImpl.setTaskCallback(this)

        carStartButton.setOnClickListener{App.instance.carWorkPresenterImpl.engineStart()}
        carStopButton.setOnClickListener{App.instance.carWorkPresenterImpl.engineTurnOff()}
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textCarState.text = savedInstanceState?.getString("KEY_CAR_STATE", "Двигатель выключен")
        textCarTimeLeft.text = savedInstanceState?.getString("KEY_CAR_TIME_LEFT", "Нажмите start")
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("KEY_CAR_STATE", textCarState.text.toString())
        outState.putString("KEY_CAR_TIME_LEFT", textCarTimeLeft.text.toString())
    }
}

interface CarTaskCallback{

    fun doToast()

    fun setCarStateText(stateText: String)

    fun setCarTimeLeftText(timeLeftText: String)
}