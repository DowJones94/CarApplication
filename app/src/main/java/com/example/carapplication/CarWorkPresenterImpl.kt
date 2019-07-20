package com.example.carapplication

import android.content.Context
import android.os.AsyncTask
import android.widget.TextView
import android.widget.Toast


class CarWorkPresenterImpl(private val textCarState: TextView?,
                           private val textCarTimeLeft: TextView?,
                           context: Context) : ICarWorkPresenter {

    private var carTask: CarTask? = null
    private val toast = Toast.makeText(context, "Двигатель уже запущен", Toast.LENGTH_SHORT)

    override fun engineStart() {
        //если задача не запускалась или была отменена или была завершена
        if (carTask == null || carTask!!.status == AsyncTask.Status.FINISHED || carTask!!.isCancelled) {
            carTask = CarTask(textCarState, textCarTimeLeft)
            carTask!!.execute()
        } else {
            toast.show()
        }
    }

    override fun engineTurnOff() {
        carTask?.cancel(false)
    }
}