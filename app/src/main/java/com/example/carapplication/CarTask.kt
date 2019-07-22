package com.example.carapplication

import android.os.AsyncTask

class CarTask : AsyncTask<Void, Int, Void>() {

    var stateText: String = "Двигатель не запущен"
        private set
    var timeLeft: String = "нажмите start"
        private set

    private lateinit var carTaskCallback: CarTaskCallback

    fun setCarTaskCallback(carTaskCallback: CarTaskCallback){
        this.carTaskCallback = carTaskCallback
    }

    fun doToast(){
        carTaskCallback.doToast()
    }

    override fun onPreExecute() {
        super.onPreExecute()
        carTaskCallback.setCarStateText("Двигатель запущен")
        stateText = "Двигатель запущен"
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
        timeLeft = "Осталось ${values[0]} секунд"
    }

    override fun onPostExecute(result: Void?) {
        carTaskCallback.setCarStateText("Двигатель выключен")
        stateText = "Двигатель выключен"
        carTaskCallback.setCarTimeLeftText("нажмите start")
        timeLeft = "нажмите start"
    }

    override fun onCancelled() {
        carTaskCallback.setCarStateText("Работа прервана")
        stateText = "Работа прервана"
        carTaskCallback.setCarTimeLeftText("нажмите start")
        timeLeft = "нажмите start"
    }

}