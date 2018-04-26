package ru.degeneration.counter

class Game{
    val counters = ArrayList<Counter>()

    fun createMockCounters(){
        val nik = Counter("Никита")
        val art = Counter("Артем")
        nik.value = 75
        art.value = 75
        counters.add(art)
        counters.add(nik)

    }
}