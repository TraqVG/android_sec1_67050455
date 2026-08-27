const val LAB_NAME = "Classes and Objects"

fun String.hasSpaces(): Boolean = any { it.isWhitespace() }

fun Aquarium.isTooWarm(temperatureCelsius: Int): Boolean = temperatureCelsius > 30

fun demoPairsCollectionsAndExtensions() {
    val bookAuthor = "Kotlin in Action" to "Dmitry Jemerov & Svetlana Isakova"
    val fish = listOf("shark", "plecostomus", "goldfish")
    val filtered = fish.filter { it.contains('a') }

    println("$LAB_NAME: $bookAuthor")
    println("Fish containing 'a': $filtered")
    println("Has spaces: ${LAB_NAME.hasSpaces()}")
    println("Aquarium too warm at 31C: ${Aquarium().isTooWarm(31)}")
}
