package ru.degeneration.counter.ru.degeneration.counter.model

/**
 * Created by МасловАМ on 30.03.2018
 */
class Change(val counter: Counter){
    var value = 0
    fun apply(){
        counter.value = counter.value + value
    }
}