package com.example.carapplication

import android.os.AsyncTask


class CarWorkPresenterImpl : ICarWorkPresenter {

    private var carTask: CarTask? = null


    override fun engineStart() {
        //если задача не запускалась или была отменена или была завершена
        if (carTask == null || carTask!!.status == AsyncTask.Status.FINISHED || carTask!!.isCancelled) {
            carTask = CarTask()
            carTask!!.execute()
        } else {
            carTask?.doToast()
        }
    }

    override fun engineTurnOff() {
        carTask?.cancel(false)
    }
}