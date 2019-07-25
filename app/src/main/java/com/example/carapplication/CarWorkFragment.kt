package com.example.carapplication

import android.os.Bundle
import androidx.fragment.app.Fragment

class CarWorkFragment : Fragment() {

    var carWorkPresenterImpl = CarWorkPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
}