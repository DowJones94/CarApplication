package com.example.carapplication

import android.os.AsyncTask


class CarWorkPresenterImpl : ICarWorkPresenter {

    private lateinit var carCallback: CarTaskCallback

    fun setTaskCallback(carCallback: CarTaskCallback){
        this.carCallback = carCallback
    }


    override fun engineStart() {
        //если задача не запускалась или была отменена или была завершена
        if (App.instance.carTask == null || App.instance.carTask!!.status == AsyncTask.Status.FINISHED || App.instance.carTask!!.isCancelled) {
            App.instance.carTask = CarTask()
            App.instance.carTask?.setCarTaskCallback(carCallback)
            App.instance.carTask!!.execute()
        } else {
            App.instance.carTask?.doToast()
        }
    }

    override fun engineTurnOff() {
        App.instance.carTask?.cancel(false)
    }
}