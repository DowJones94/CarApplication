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

        App.instance.carTask.setCarTaskCallback(this)
        textCarState.text = App.instance.carTask.stateText
        textCarTimeLeft.text = App.instance.carTask.timeLeft


        val carWorkPresenter: ICarWorkPresenter = CarWorkPresenterImpl()
        carStartButton.setOnClickListener{carWorkPresenter.engineStart()}
        carStopButton.setOnClickListener{carWorkPresenter.engineTurnOff()}
    }

}

interface CarTaskCallback{

    fun doToast()

    fun setCarStateText(stateText: String)

    fun setCarTimeLeftText(timeLeftText: String)
}