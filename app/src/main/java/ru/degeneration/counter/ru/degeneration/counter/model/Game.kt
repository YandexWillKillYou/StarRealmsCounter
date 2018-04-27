package ru.degeneration.counter.ru.degeneration.counter.model

class Game{
    val counters = ArrayList<Counter>()

    fun createMockCounters(){
        val and = Counter("Андрей")
        val art = Counter("Артем")
        val ann = Counter("Аня")
        val geo = Counter("Гоша")
        and.value = 75
        art.value = 75
        ann.value = 75
        geo.value = 75
        counters.add(art)
        counters.add(ann)
        counters.add(and)
        counters.add(geo)
    }
}