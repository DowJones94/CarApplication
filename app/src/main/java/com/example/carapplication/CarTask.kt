package com.example.carapplication

import android.os.AsyncTask
import android.widget.TextView

class CarTask(val textCarState: TextView?, val textCarTimeLeft: TextView?) : AsyncTask<Void, Int, Void>() {

    override fun onPreExecute() {
        super.onPreExecute()
        textCarState?.text = "Двигатель запущен"
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
        textCarTimeLeft?.text = "Осталось ${values[0]} секунд"
    }

    override fun onPostExecute(result: Void?) {
        textCarState?.text = "Двигатель выключен"
        textCarTimeLeft?.text = "нажмите start"
    }

    override fun onCancelled() {
        textCarState?.text = "Двигатель выключен"
        textCarTimeLeft?.text = "нажмите start"
    }

}