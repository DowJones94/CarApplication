package com.example.carapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :  AppCompatActivity(), CarTaskCallback{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textCarTimeLeft.text = App.instance.fakeCarTask.i.toString()
        App.instance.fakeCarTask.setCarTaskCallback(this)

        val carWorkPresenter: ICarWorkPresenter = CarWorkPresenterImpl(textCarState, textCarTimeLeft, this)
        carStartButton.setOnClickListener{carWorkPresenter.engineStart()}

        carStopButton.setOnClickListener{
            /* carWorkPresenter.engineTurnOff() */
            App.instance.fakeCarTask.increment()
        }
    }

    override fun doSomething(activityText: String) {
        textCarTimeLeft.text = activityText
    }
}

interface CarTaskCallback{
    fun doSomething(activityText: String)
}

class FakeCarTask {
    private lateinit var carTaskCallback: CarTaskCallback
    var i: Int = 0

    fun setCarTaskCallback(carTaskCallback: CarTaskCallback){
        this.carTaskCallback = carTaskCallback
    }

    fun increment() {
        carTaskCallback.doSomething("${++i}")
    }
}