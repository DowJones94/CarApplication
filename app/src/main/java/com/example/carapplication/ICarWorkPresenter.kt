package com.example.carapplication

interface ICarWorkPresenter {

    /** Используется для реализации фоновой работы запуска двигателя */
    fun engineStart()

    /** Используется для прерывания фоновой работы запуска двигателя */
    fun engineTurnOff()

}