package com.example.carapplication

import android.os.AsyncTask

class CarTask : AsyncTask<Void, Int, Void>() {

    private lateinit var carTaskCallback: CarTaskCallback

    fun setCarTaskCallback(carTaskCallback: CarTaskCallback){
        this.carTaskCallback = carTaskCallback
    }

    override fun onPreExecute() {
        super.onPreExecute()
        carTaskCallback.setCarStateText("Двигатель запущен")
    }

    override fun doInBackground(vararg p0: Void?): Void? {

        for (i in 0..9) {
            if (isCancelled) {
                return null
            }
            publishProgress(10-i)
            Thread.sleep(1000)
        }

        return null

    }

    override fun onProgressUpdate(vararg values: Int?) {// fun asd(vararg values: Int?) == fun asd(value: Int, vslue2: Int, )
        carTaskCallback.setCarTimeLeftText("Осталось ${values[0]} секунд")
    }

    override fun onPostExecute(result: Void?) {
        carTaskCallback.setCarStateText("Двигатель выключен")
        carTaskCallback.setCarTimeLeftText("нажмите start")
    }

    override fun onCancelled() {
        carTaskCallback.setCarStateText("Работа прервана")
        carTaskCallback.setCarTimeLeftText("нажмите start")
    }

}