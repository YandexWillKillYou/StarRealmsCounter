package ru.degeneration.counter.ru.degeneration.counter.model

class Game{
    var defaultHP=75
    val counters = ArrayList<Counter>()

    fun registerCounter(counter: Counter){
        counter.value=defaultHP
        counters.add(counter)
    }

    fun createMockCounters(){
        val nik = Counter("Никита")
        val art = Counter("Артем")
        nik.value = defaultHP
        art.value = defaultHP
        counters.add(art)
        counters.add(nik)
    }
}