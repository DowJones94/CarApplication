package com.example.carapplication

import android.os.AsyncTask


class CarWorkPresenterImpl : ICarWorkPresenter {

    private var carTask: CarTask? = null
    private lateinit var carCallback: CarTaskCallback

    fun setTaskCallback(carCallback: CarTaskCallback){
        this.carCallback = carCallback
        carTask?.setCarTaskCallback(carCallback)
    }


    override fun engineStart() {
        //если задача не запускалась или была отменена или была завершена
        if (carTask == null || carTask!!.status == AsyncTask.Status.FINISHED || carTask!!.isCancelled) {
            carTask = CarTask()
            carTask?.setCarTaskCallback(carCallback)
            carTask!!.execute()
        } else {
            carCallback.doToast()
        }
    }

    override fun engineTurnOff() {
        carTask?.cancel(false)
    }
}