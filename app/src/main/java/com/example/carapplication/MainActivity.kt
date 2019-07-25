package com.example.carapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity :  AppCompatActivity(), CarTaskCallback{

    private var toast: Toast? = null
    private val KEY_CARWORK_FRAGMENT: String = "CAR_WORK"
    private var carWorkFragment: CarWorkFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment: Fragment? = supportFragmentManager.findFragmentByTag(KEY_CARWORK_FRAGMENT)

        if (fragment != null) {
            carWorkFragment = fragment as CarWorkFragment
        } else {
            carWorkFragment = CarWorkFragment()
            supportFragmentManager.beginTransaction().add(carWorkFragment!!, KEY_CARWORK_FRAGMENT).commit()
        }

        carWorkFragment!!.carWorkPresenterImpl.setTaskCallback(this)

        carStartButton.setOnClickListener{carWorkFragment!!.carWorkPresenterImpl.engineStart()}
        carStopButton.setOnClickListener{carWorkFragment!!.carWorkPresenterImpl.engineTurnOff()}

    }


    @SuppressLint("ShowToast")
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